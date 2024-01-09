package com.example.ticket_management.controller;

import com.example.ticket_management.dto.CustomerDTO;
import com.example.ticket_management.model.Customer;
import com.example.ticket_management.model.Payment;
import com.example.ticket_management.model.Ticket;
import com.example.ticket_management.model.TicketCart;
import com.example.ticket_management.service.ICustomerService;
import com.example.ticket_management.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/public")
@Controller
public class EmailController {
    @Autowired
    ICustomerService customerService;
    @Autowired
    IPaymentService paymentService;

    @GetMapping("/email_check")
    public String checkMail(CustomerDTO customerDTO, @SessionAttribute("ticketCart") TicketCart ticketCart) {
        Customer customer = customerService.findByEmail(customerDTO.getEmail()).orElse(null);
        List<Ticket> ticketList = new ArrayList<>(ticketCart.ticketList.values());
        Long totalPrice = 0L;
        for (Ticket ticket : ticketList) {
            totalPrice += ticket.getPrice();
        }
        if (customer == null) {
//            customerService.save(new Customer(customerDTO.getEmail(), customerDTO.getName(), customerDTO.getPhoneNumber(), ticketList));
            sendConfirmationEmail();
            return "ticket";
        } else {
            Payment currentPayment = paymentService.save(new Payment(ticketList));
            return "redirect:/public/checkout?amount=" + totalPrice + "payment_id=" + currentPayment.getId();
        }
    }
}
