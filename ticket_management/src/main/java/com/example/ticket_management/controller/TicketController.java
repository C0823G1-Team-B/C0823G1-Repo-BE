package com.example.ticket_management.controller;

import com.example.ticket_management.dto.CustomerDTO;
import com.example.ticket_management.dto.ICarRouteIndividualDTO;
import com.example.ticket_management.model.*;
import com.example.ticket_management.service.ICarRouteIndividualService;
import com.example.ticket_management.service.ICustomerService;
import com.example.ticket_management.service.IPaymentService;
import com.example.ticket_management.service.ITicketService;
import com.example.ticket_management.utils.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/ticket")
@SessionAttributes("ticketCart")
public class TicketController {
    @Autowired
    private ITicketService iTicketService;
    @Autowired
    private ICarRouteIndividualService iCarRouteIndividualService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IPaymentService paymentService;
    @Autowired
    MailService mailService;

    @ModelAttribute("ticketCart")
    public TicketCart getTicketCart() {
        return new TicketCart();
    }

    @GetMapping("/{idCRI}")
    public ModelAndView showTicket(@PathVariable Integer idCRI) {
        Optional<CarRouteIndividual> carRouteIndividual = iCarRouteIndividualService.findById(idCRI);
        if (!carRouteIndividual.isPresent()) {
            return new ModelAndView("error");
        }
        ICarRouteIndividualDTO iCarRouteIndividualDTO = iCarRouteIndividualService.findByIdDTO(idCRI);
        Iterable<Ticket> tickets = iTicketService.findAllByCarRouteIndividual(carRouteIndividual.get());
        List<Ticket> tickets1 = (List<Ticket>) tickets;
        ModelAndView modelAndView = new ModelAndView("ticket", "tickets", tickets1);
        modelAndView.addObject("ticketCart", new TicketCart());
        modelAndView.addObject("cri", iCarRouteIndividualDTO);
        modelAndView.addObject("df", DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
        modelAndView.addObject("ctmDTO", new CustomerDTO());
        return modelAndView;
    }

    @GetMapping("/selectticket/{id}")
    public ModelAndView selectTicket(@ModelAttribute("ticketCart") TicketCart ticketCart,
                                     @PathVariable Integer id,
                                     @RequestParam(value = "phoneNumber", required = false, defaultValue = "") String phoneNumber,
                                     @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                     @RequestParam(value = "email", required = false, defaultValue = "") String email) {
        Optional<Ticket> ticket = iTicketService.findById(id);
        if (!ticket.isPresent()) {
            return new ModelAndView("error");
        }
        ticketCart.addTicket(ticket.get());
        Iterable<Ticket> tickets = iTicketService.findAllByCarRouteIndividual(ticket.get().getCarRouteIndividual());
        List<Ticket> tickets1 = (List<Ticket>) tickets;
        ModelAndView modelAndView = new ModelAndView("ticket", "tickets", tickets1);
        modelAndView.addObject("ticketCart", ticketCart);
        modelAndView.addObject("df", DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
        CustomerDTO customerDTO = new CustomerDTO(email, name, phoneNumber);
        modelAndView.addObject("cri", iCarRouteIndividualService.findByIdDTO(ticket.get().getCarRouteIndividual().getId()));
        modelAndView.addObject("ctmDTO", customerDTO);
        return modelAndView;
    }

    @GetMapping("/noselectticket/{id}")
    public ModelAndView noSelectTicket(@ModelAttribute("ticketCart") TicketCart ticketCart, @PathVariable Integer id,
                                       @RequestParam(value = "phoneNumber", required = false, defaultValue = "") String phoneNumber,
                                       @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                       @RequestParam(value = "email", required = false, defaultValue = "") String email) {
        Optional<Ticket> ticket = iTicketService.findById(id);
        if (!ticket.isPresent()) {
            return new ModelAndView("error");
        }
        ticketCart.removeTicket(ticket.get());
        Iterable<Ticket> tickets = iTicketService.findAllByCarRouteIndividual(ticket.get().getCarRouteIndividual());
        List<Ticket> tickets1 = (List<Ticket>) tickets;
        ModelAndView modelAndView = new ModelAndView("ticket", "tickets", tickets1);
        modelAndView.addObject("ticketCart", ticketCart);
        CustomerDTO customerDTO = new CustomerDTO(email, name, phoneNumber);
        modelAndView.addObject("df", DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
        modelAndView.addObject("cri", iCarRouteIndividualService.findByIdDTO(ticket.get().getCarRouteIndividual().getId()));
        modelAndView.addObject("ctmDTO", customerDTO);
        return modelAndView;
    }

    @PostMapping("/payment/{idCRI}")
    public ModelAndView showPayment(@Validated @ModelAttribute("ctmDTO") CustomerDTO customerDTO,
                                    BindingResult bindingResult,
                                    @PathVariable Integer idCRI,
                                    @ModelAttribute("ticketCart") TicketCart ticketCart,
                                    RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            Optional<CarRouteIndividual> carRouteIndividual = iCarRouteIndividualService.findById(idCRI);
            if (!carRouteIndividual.isPresent()) {
                return new ModelAndView("error");
            }
            ICarRouteIndividualDTO iCarRouteIndividualDTO = iCarRouteIndividualService.findByIdDTO(idCRI);
            Iterable<Ticket> tickets = iTicketService.findAllByCarRouteIndividual(carRouteIndividual.get());
            List<Ticket> tickets1 = (List<Ticket>) tickets;
            ModelAndView modelAndView = new ModelAndView("ticket", "tickets", tickets1);
            modelAndView.addObject("ticketCart", ticketCart);
            modelAndView.addObject("cri", iCarRouteIndividualDTO);
            modelAndView.addObject("df", DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
            return modelAndView;
        }

        Map<Integer, Ticket> ticketMap = ticketCart.ticketList;
        Set<Integer> integers = ticketMap.keySet();
        for (Integer i : integers) {
            if (iTicketService.checkStatusTicket(i)) {
                Optional<CarRouteIndividual> carRouteIndividual = iCarRouteIndividualService.findById(idCRI);
                if (!carRouteIndividual.isPresent()) {
                    return new ModelAndView("error");
                }
                ICarRouteIndividualDTO iCarRouteIndividualDTO = iCarRouteIndividualService.findByIdDTO(idCRI);
                Iterable<Ticket> tickets = iTicketService.findAllByCarRouteIndividual(carRouteIndividual.get());
                List<Ticket> tickets1 = (List<Ticket>) tickets;
                ModelAndView modelAndView = new ModelAndView("ticket", "tickets", tickets1);
                modelAndView.addObject("ticketCart", new TicketCart());
                modelAndView.addObject("cri", iCarRouteIndividualDTO);
                modelAndView.addObject("df", DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
                modelAndView.addObject("message", "Đã có khách hàng khách khác chọn vé nhanh hơn! Xin mời bạn chọn lại!");
                return modelAndView;
            }
        }
        //Kiểm tra email của khách hàng trong database
        Customer customer = customerService.findByEmail(customerDTO.getEmail()).orElse(null);
        List<Ticket> ticketList = new ArrayList<>(ticketCart.ticketList.values());

        Long totalPrice = 0L;
        for (Ticket ticket : ticketList) {
            totalPrice += ticket.getPrice();
        }
        if (customer == null) {
            Customer newCustomer = new Customer(customerDTO.getEmail(), customerDTO.getName(), customerDTO.getPhoneNumber(), ticketList);
            newCustomer.setDelete(true);
            customerService.save(newCustomer);
            Payment currentPayment = paymentService.createPayment(ticketList, newCustomer);
            mailService.mailToConfirmCustomerEmail(totalPrice, currentPayment.getId(), customerDTO);
            ModelAndView modelAndView = new ModelAndView("redirect:/ticket/" + ticketList.get(0).getCarRouteIndividual().getId());
            redirectAttributes.addFlashAttribute("ctmDTO", customerDTO);
            redirectAttributes.addFlashAttribute("message", "Quý khách vui lòng kiểm tra e-mail để tiếp tục thanh toán");
            return modelAndView;
        }
        Payment currentPayment = paymentService.createPayment(ticketList, customer);
        return new ModelAndView("redirect:/public/checkout?amount=" + totalPrice + "&payment_id=" + currentPayment.getId());
    }
}
