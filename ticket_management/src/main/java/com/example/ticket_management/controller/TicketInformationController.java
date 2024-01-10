package com.example.ticket_management.controller;

import com.example.ticket_management.dto.ITicketDto;
import com.example.ticket_management.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search-ticket")
public class TicketInformationController {
    @Autowired
    private ITicketService iTicketService;

    @GetMapping
    public String showSearchPage() {
        return "/formSearchTicket";
    }

    @GetMapping("/search")
    public String searchTicket(@RequestParam(
                                        name = "search",
                                        required = false,
                                        defaultValue = "") String name,
                               @RequestParam(
                                       value = "page",
                                       required = false,
                                       defaultValue = "0") int page,
                               Model model) {
        try {
            Integer.parseInt(name);
            model.addAttribute("errorName","Vui lòng nhập đúng định dạng!");
        } catch (NumberFormatException e) {
            Pageable pageable = PageRequest.of(page, 5);
            Page<ITicketDto> tickets = iTicketService.findAllTicketInformationOfUser(pageable, name);
            model.addAttribute("searchValue",name);
            model.addAttribute("tickets",tickets);
        }
        model.addAttribute("searchValue",name);
        return "formSearchTicket";
    }
}
