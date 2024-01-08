package com.example.ticket_management.model;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.*;

public class TicketCart {
    public Map<Integer,Ticket> ticketList = new HashMap<>();

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

    public List<String> getNumberSeat2(){
        List<String> strList = new ArrayList<>();
        Set<Integer> tickets = ticketList.keySet();
        String string = "";
        for (Integer integer:tickets){
            if (ticketList.get(integer).getNumberSeat() < 10){
                string = "0" + ticketList.get(integer).getNumberSeat();
                strList.add(string);
            }
            else {
                string = String.valueOf(ticketList.get(integer).getNumberSeat());
                strList.add(string);
            }
        }
        return strList;
    }
}
