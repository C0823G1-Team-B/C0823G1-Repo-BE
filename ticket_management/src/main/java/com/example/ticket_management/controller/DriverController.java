package com.example.ticket_management.controller;

import com.example.ticket_management.model.Driver;
import com.example.ticket_management.service.IDriverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/driver")
public class DriverController {
    @Autowired
    private IDriverService iDriverService;

    @GetMapping("/create")
    public String formDriver(Model model) {
        model.addAttribute("driver", new Driver());
        return "formCreateDriver";
    }

    @PostMapping("/create")
    public String create(@Valid Driver driver, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "formCreateDriver";
        }
        this.iDriverService.save(driver);
        return "redirect:/admin/driver/list";
    }

    @GetMapping("/list")
    public String showListDriver(Model model,
                                 @RequestParam(
                                         value = "page",
                                         required = false,
                                         defaultValue = "0") int page,
                                 @RequestParam(
                                         value = "search",
                                         required = false,
                                         defaultValue = "") String name) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Driver> drivers = this.iDriverService.find(pageable, name);
        model.addAttribute("drivers", drivers);
        System.out.println(drivers);
        return "listDriver";
    }



    @PostMapping("/remove")
    public String remove(@RequestParam("idDelete") Integer idDelete) {
        iDriverService.deleteById(idDelete);
        return "redirect:/admin/driver/list";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("drivers", this.iDriverService.findById(id).get());
        return "/formEditDriver";
    }

    @PostMapping("/edit")
    public String edit(Driver driver) {
        this.iDriverService.save(driver);
        return "redirect:/admin/driver/list";
    }

}
