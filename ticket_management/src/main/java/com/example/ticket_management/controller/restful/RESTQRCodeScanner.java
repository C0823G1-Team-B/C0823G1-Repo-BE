package com.example.ticket_management.controller.restful;

import com.example.ticket_management.model.Payment;
import com.example.ticket_management.model.Ticket;
import com.example.ticket_management.service.IPaymentService;
import com.example.ticket_management.utils.BCryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class RESTQRCodeScanner {
    @Autowired
    IPaymentService paymentService;

    @GetMapping("/check_qr_code")
    public ResponseEntity<List<Ticket>> checkQRCode(@RequestParam("payment_id") Integer paymentId,
                                                    @RequestParam("pass_code") String hashedPassCode) {
        Payment payment = paymentService.findById(paymentId).orElse(null);
        if (payment != null) {
            switch (payment.getStatus()) {
                case 0:
                case 2:
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                case 1:
                    if (BCryptUtils.checkPassword(payment.getPassCode(), hashedPassCode)) {
                        List<Ticket> tickets = payment.getTickets();
                        return new ResponseEntity<>(tickets, HttpStatus.OK);
                    }
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
