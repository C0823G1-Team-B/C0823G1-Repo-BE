package com.example.ticket_management.dto;

public class QuantityTicket {
    private Integer quantity;

    public QuantityTicket(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public QuantityTicket() {
    }
}
