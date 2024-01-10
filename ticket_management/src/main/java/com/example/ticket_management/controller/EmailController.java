package com.example.ticket_management.controller;

import com.example.ticket_management.dto.CustomerDTO;
import com.example.ticket_management.model.Customer;
import com.example.ticket_management.model.Payment;
import com.example.ticket_management.model.Ticket;
import com.example.ticket_management.model.TicketCart;
import com.example.ticket_management.service.ICustomerService;
import com.example.ticket_management.service.IPaymentService;
import com.example.ticket_management.utils.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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


    @PostMapping("/email_check")
    public String checkMailExistInDatabase(@ModelAttribute("ctmDTO") CustomerDTO customerDTO,
                                           @SessionAttribute("ticketCart") TicketCart ticketCart,
                                           RedirectAttributes redirectAttributes) {
        Customer customer = customerService.findByEmail(customerDTO.getEmail()).orElse(null);
        List<Ticket> ticketList = new ArrayList<>(ticketCart.ticketList.values());
        Payment currentPayment = paymentService.save(new Payment(ticketList));
        Long totalPrice = 0L;
        for (Ticket ticket : ticketList) {
            totalPrice += ticket.getPrice();
        }

        if (customer == null) {
            Customer newCustomer = new Customer(customerDTO.getEmail(), customerDTO.getName(), customerDTO.getPhoneNumber(), ticketList);
            newCustomer.setDelete(true);
            customerService.save(newCustomer);
            mailService.mailToConfirmCustomerEmail(totalPrice, currentPayment.getId(), customerDTO);
            redirectAttributes.addFlashAttribute("ctmDTO", customerDTO);
            redirectAttributes.addFlashAttribute("message", "Quý khách vui lòng kiểm tra e-mail để tiếp tục thanh toán");
            return "redirect:/ticket/" + ticketList.get(0).getCarRouteIndividual().getId();
        }

        if (customer.isDelete()) {
            mailService.mailToConfirmCustomerEmail(totalPrice, currentPayment.getId(), customerDTO);
            redirectAttributes.addFlashAttribute("ctmDTO", customerDTO);
            redirectAttributes.addFlashAttribute("message", "Quý khách vui lòng kiểm tra e-mail để tiếp tục thanh toán");
            return "redirect:/ticket/" + ticketList.get(0).getCarRouteIndividual().getId();
        }
        return "redirect:/public/checkout?amount=" + totalPrice + "&payment_id=" + currentPayment.getId();
    }

    @PostMapping("/confirm_email")
    public String confirmMail(@RequestParam("total_price") Long totalPrice,
                              @RequestParam("payment_id") Integer paymentId,
                              @RequestParam("email") String email) {
        customerService.findByEmail(email).ifPresent(confirmedCustomer -> confirmedCustomer.setDelete(false));
        Payment payment = paymentService.findById(paymentId).orElse(null);
        if (payment != null) {
            if (!payment.isStatus()) {
                return "redirect:/public/checkout?amount=" + totalPrice + "&payment_id=" + paymentId;
            }
        }
        return "redirect:/";
    }
}
