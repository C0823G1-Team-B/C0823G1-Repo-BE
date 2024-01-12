package com.example.ticket_management.dto;

public class SearchDto {
    private Integer id;
    private String startPoint;
    private String endPoint;
    private String startTime;
    private String endTime;
    private Long price;
    private Integer ticket;

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public SearchDto(Integer id, String startPoint, String endPoint, String startTime, String endTime, Long price,Integer ticket) {
        this.id = id;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.ticket = ticket;
    }

    public SearchDto(String startPoint, String endPoint, String startTime) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startTime = startTime;
    }

    public SearchDto() {
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
