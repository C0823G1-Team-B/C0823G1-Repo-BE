package com.example.ticket_management.controller;

import com.example.ticket_management.dto.CustomerDTO;
import com.example.ticket_management.model.Customer;
import com.example.ticket_management.model.Payment;
import com.example.ticket_management.model.Ticket;
import com.example.ticket_management.model.TicketCart;
import com.example.ticket_management.service.ICustomerService;
import com.example.ticket_management.service.IPaymentService;
import com.example.ticket_management.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @Autowired
    MailUtils mailUtils;


    @PostMapping("/email_check")
    public String checkMailExistInDatabase(CustomerDTO customerDTO,
                                           @SessionAttribute("ticketCart") TicketCart ticketCart,
                                           Model model) {
        Customer customer = customerService.findByEmail(customerDTO.getEmail()).orElse(null);
        List<Ticket> ticketList = new ArrayList<>(ticketCart.ticketList.values());
        Payment currentPayment = paymentService.save(new Payment(ticketList));
        Long totalPrice = 0L;
        for (Ticket ticket : ticketList) {
            totalPrice += ticket.getPrice();
        }
        if (customer == null) {
            mailUtils.mailToConfirmCustomerEmail(totalPrice, currentPayment.getId(), customerDTO.getEmail());

            model.addAttribute("message", "Quý khách vui lòng kiểm tra e-mail để tiếp tục thanh toán");
            return "ticket";
        } else {
            return "redirect:/public/checkout?amount=" + totalPrice + "&payment_id=" + currentPayment.getId();
        }
    }
    @GetMapping("/confirm_email")
    public void confirmMail(){
//        customerService.save(new Customer(customerDTO.getEmail(), customerDTO.getName(), customerDTO.getPhoneNumber(), ticketList));
    }
}
