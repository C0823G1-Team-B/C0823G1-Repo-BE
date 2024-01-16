package com.example.ticket_management.controller;

import com.example.ticket_management.config.VNPayConfig;
import com.example.ticket_management.dto.CustomerDTO;
import com.example.ticket_management.model.Customer;
import com.example.ticket_management.model.Payment;
import com.example.ticket_management.model.Ticket;
import com.example.ticket_management.model.TicketCart;
import com.example.ticket_management.service.ICustomerService;
import com.example.ticket_management.service.IPaymentService;
import com.example.ticket_management.service.ITicketService;
import com.example.ticket_management.utils.MailService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/public")
@Controller
public class EmailController {
    @Autowired
    ICustomerService customerService;
    @Autowired
    IPaymentService paymentService;
    @Autowired
    MailService mailService;
    @Autowired
    ITicketService ticketService;

    @PostMapping("/confirm_email")
    public String confirmMail(@RequestParam("total_price") Long totalPrice,
                              @RequestParam("payment_id") Integer paymentId,
                              @RequestParam("email") String email,
                              Model model) {
        Customer confirmCustomer = customerService.findByEmail(email).orElse(null);
        if (confirmCustomer != null) {
            confirmCustomer.setDelete(false);
            customerService.save(confirmCustomer);
        } else {
            model.addAttribute("result", "Email này không tồn tại!");
            return "/checkout-result";
        }
        Payment payment = paymentService.findById(paymentId).orElse(null);
        if (payment != null) {
            switch (payment.getStatus()) {
                case 0:
                    return "redirect:/public/checkout?amount=" + totalPrice + "&payment_id=" + paymentId;
                case 1:
                    model.addAttribute("result", "Giao dịch này đã thành công!");
                    return "checkout-result";
                case 2:
                    model.addAttribute("result", "Giao dịch này đã thất bại!");
                    return "checkout-result";
            }
        }
        model.addAttribute("result", "Giao dịch không tồn tại!");
        return "checkout-result";
    }

    @GetMapping("/gmail_check")
    public String gmailCheck(@AuthenticationPrincipal @Nullable OAuth2User principal,
                             @SessionAttribute TicketCart ticketCart) {
        if (principal == null) {
            return "redirect:http://localhost:8080/oauth2/authorization/google";
        }
        String email = principal.getAttribute("email");
        Customer customer = customerService.findByEmail(email).orElse(null);
        if (customer == null) {
            Customer newCustomer = new Customer();
            newCustomer.setName(principal.getAttribute("name"));
            newCustomer.setEmail(email);
            customerService.save(newCustomer);
            customer = newCustomer;
        }
        List<Ticket> ticketList = new ArrayList<>(ticketCart.ticketList.values());
        Long totalPrice = 0L;
        for (Ticket ticket : ticketList) {
            totalPrice += ticket.getPrice();
        }
        Payment payment = paymentService.createPayment(ticketList, customer);

        return "redirect:/public/checkout?amount=" + totalPrice + "&payment_id=" + payment.getId();
    }
}
