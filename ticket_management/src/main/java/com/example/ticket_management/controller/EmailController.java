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
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/email_check")
    public String checkMailExistInDatabase(@ModelAttribute("ctmDTO") CustomerDTO customerDTO,
                                           @SessionAttribute("ticketCart") TicketCart ticketCart,
                                           RedirectAttributes redirectAttributes) {
        Customer customer = customerService.findByEmail(customerDTO.getEmail()).orElse(null);
        List<Ticket> ticketList = new ArrayList<>(ticketCart.ticketList.values());
        Payment currentPayment = new Payment();
        paymentService.save(currentPayment);
        currentPayment.setTickets(ticketList);
        currentPayment.setPassCode(VNPayConfig.getRandomNumber(8));
        currentPayment.setStatus(0);
        paymentService.save(currentPayment);
        for (Ticket t : currentPayment.getTickets()) {
            t.setPrice(100L);
        }
        paymentService.save(currentPayment);
        for (Ticket t : currentPayment.getTickets()) {
            ticketService.save(t);
        }
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
        return "redirect:/public/checkout?amount=" + totalPrice + "&payment_id=" + currentPayment.getId();
    }

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
}
