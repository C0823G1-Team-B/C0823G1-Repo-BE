package com.example.ticket_management.controller;

import com.example.ticket_management.utils.BCryptUtils;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RESTController {
    @Autowired
    JavaMailSender mailSender;

    @GetMapping("/mail")
    public ResponseEntity<String> mailPaymentStatus(@RequestParam("ticket_id") Long ticketId) {
        String hashedTicketId = BCryptUtils.encryptPassword(String.valueOf(ticketId));
        MimeMessage message = mailSender.createMimeMessage();
        String QRCodeCheck = "http://localhost:8080/api/check_qr_code?ticket_id="+hashedTicketId;

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
        return new ResponseEntity<>("Duoc roi nha", HttpStatus.OK);
    }

    @GetMapping("/check_qr_code")
    public ResponseEntity<String> checkQRCode(@RequestParam("ticket_id") String hashedTicketId) {
        if (BCryptUtils.checkPassword("1", hashedTicketId)) {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        return new ResponseEntity<>("Sai ma ticket", HttpStatus.NOT_ACCEPTABLE);
    }
}
