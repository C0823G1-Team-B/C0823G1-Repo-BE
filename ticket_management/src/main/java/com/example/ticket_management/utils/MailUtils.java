package com.example.ticket_management.utils;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class MailUtils {
    @Autowired
    JavaMailSender mailSender;

    public void mailPaymentStatus(Integer paymentId) {
        String hashedPaymentId = BCryptUtils.encryptPassword(String.valueOf(paymentId));
        MimeMessage message = mailSender.createMimeMessage();
        String QRCodeCheck = "http://localhost:8080/api/check_qr_code?ticket_id=" + hashedPaymentId;

        try {
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("pansuzu@gmail.com"));
            message.setSubject("Mã QR vé xe nhà xe XYZ");
            String mailBody = "<h2>Cảm ơn quý khách đã đặt vé xe. Vui lòng đưa mã QR dưới đây cho nhân viên xoát vé</h2>" +
                    "QR Code <br>" +
                    "<img src=\"https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=" + QRCodeCheck;
            message.setContent(mailBody, "text/html");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        mailSender.send(message);
    }

    @GetMapping("/check_qr_code")
    public ResponseEntity<String> checkQRCode(@RequestParam("ticket_id") String hashedTicketId) {
        if (BCryptUtils.checkPassword("1", hashedTicketId)) {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        return new ResponseEntity<>("Sai ma ticket", HttpStatus.NOT_ACCEPTABLE);
    }

    public void mailToConfirmCustomerEmail(Long totalPrice, Integer paymentId, String customerEmail) {
        MimeMessage message = mailSender.createMimeMessage();
        String confirmLink = "http://localhost:8080/public/checkout?amount=" + totalPrice + "&payment_id=" + paymentId;
        System.out.println(confirmLink);

        try {
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customerEmail));
            message.setSubject("Xác nhận email thanh toán");
            String mailBody = "<h2>Quý khách vui lòng click vào đường link dưới đây để tiếp tục thanh toán</h2>" +
                    "<a src=\"" + confirmLink + "\">" + confirmLink + "</a>";
            message.setContent(mailBody, "text/html");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        mailSender.send(message);
    }
}
