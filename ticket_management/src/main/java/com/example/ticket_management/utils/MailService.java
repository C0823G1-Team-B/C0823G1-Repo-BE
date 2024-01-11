package com.example.ticket_management.utils;

import com.example.ticket_management.config.VNPayConfig;
import com.example.ticket_management.dto.CustomerDTO;
import com.example.ticket_management.model.Customer;
import com.example.ticket_management.model.Payment;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class MailService {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;

    public void mailTicketQRCode(Payment payment) {
        if (payment == null) {
            System.out.println("Can't find payment before sending mail!");
            return;
        }
        String hashedPaymentPassword = BCryptUtils.encryptPassword(String.valueOf(payment.getPassCode()));
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String QRCodeCheck = "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=http://localhost:8080/public/check_qr_code?payment_id=" + payment.getId() + "&pass_code=" + hashedPaymentPassword;
        System.out.println(QRCodeCheck);
        //Get customer email from payment
        Customer customer = payment.getTickets().get(0).getCustomers();
        try {
            helper.setTo(customer.getEmail());
            helper.setSubject("[Nhà xe Hiếu Hoa] Mã QR vé xe");
            Context context = new Context();
            context.setVariable("link",QRCodeCheck);
            String content = templateEngine.process("qrcode-email", context);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void mailToConfirmCustomerEmail(Long totalPrice, Integer paymentId, CustomerDTO customerDTO) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(customerDTO.getEmail());
            helper.setSubject("[Nhà xe Hiếu Hoa] Vui lòng xác nhận mail thanh toán");
            Context context = new Context();
            context.setVariable("username", customerDTO.getName());
            context.setVariable("link", "http://localhost:8080/public/confirm_email?total_price=" +
                                        totalPrice + "&payment_id=" + paymentId + "&email=" + customerDTO.getEmail());
            String content = templateEngine.process("confirm-email", context);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
