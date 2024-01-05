package com.example.ticket_management.model;

import java.util.*;

public class TicketCart {
    private Map<Integer,Ticket> ticketList = new HashMap<>();

    public TicketCart() {
    }

    public TicketCart(Map<Integer, Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public boolean checkTicket(int id){
        Set<Integer> tickets = ticketList.keySet();
        for (Integer integer:tickets){
            if (ticketList.get(integer).getId() == id){
                return true;
            }
        }
        return false;
    }

    public void addTicket(Ticket ticket){
        if(!checkTicket(ticket.getId())){
            ticketList.put(ticket.getId(),ticket);
        }
    }

    public void removeTicket(Ticket ticket){
       ticketList.remove(ticket.getId());
    }
}
