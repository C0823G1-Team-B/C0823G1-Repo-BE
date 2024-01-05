package com.example.ticket_management.dto;

import com.example.ticket_management.model.Ticket;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public class CustomerDTO {
    private Integer id;
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ!")
    private String email;
    @NotBlank(message = "Tên không được để trống")
    @Size(min = 5, max = 60, message = "Độ dài phải từ 5-60 kí tự")
    @Pattern(regexp = "^[\\p{L}\\s]+$", message = "Tên chỉ được nhập a-z hoặc A-Z")
    private String name;
    private String birthday;
    private boolean isDelete;
    private List<Ticket> ticket;

    public CustomerDTO() {
    }

    public CustomerDTO(Integer id, String email, String name, String birthday, boolean isDelete, List<Ticket> ticket) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.isDelete = isDelete;
        this.ticket = ticket;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public List<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(List<Ticket> ticket) {
        this.ticket = ticket;
    }
}
