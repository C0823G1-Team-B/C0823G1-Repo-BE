package com.example.ticket_management.controller.restful;

import com.example.ticket_management.model.*;
import com.example.ticket_management.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/public/ajax")
public class TicketRestController {
    @Autowired
    private IDriverService iDriverService;
    @Autowired
    private ICarService iCarService;
    @Autowired
    private ICarRouteIndividualService iCarRouteIndividualService;
    @Autowired
    private ITicketService iTicketService;
    @Autowired
    private ICarRouteService iCarRouteService;


    @PostMapping(value = "/create")
    public ResponseEntity<CarRouteIndividual> createRI(@RequestBody CarRouteIndiviDto carRouteIndividual) {

        String timeStartConvert = LocalDateTime.parse(carRouteIndividual.getStartDateTime()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String timeEndConvert = LocalDateTime.parse(carRouteIndividual.getEndDateTime()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("test");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime startTime = LocalDateTime.parse(carRouteIndividual.getStartDateTime(), formatter);
        LocalDateTime endTime = LocalDateTime.parse(carRouteIndividual.getEndDateTime(), formatter);
        Ticket ticket;

        CarRouteIndividual carRouteIndividual1 = new CarRouteIndividual(startTime,
                endTime,
                false,
                new Car(carRouteIndividual.getCar()),
                new Driver(carRouteIndividual.getDriver()),
                new CarRoute(carRouteIndividual.getRoute()),
                carRouteIndividual.getPrice());

        CarRouteIndividual carRouteIndividual2 = iCarRouteIndividualService.save(carRouteIndividual1);
        Optional<CarRoute> carRoute = iCarRouteService.findById(carRouteIndividual.getRoute());

        for (int i = 1; i <= 41; i++) {
            ticket = new Ticket();
            ticket.setPrice(carRouteIndividual1.getPrice());
            ticket.setNumberSeat(i);
            ticket.setCarRouteIndividual(carRouteIndividual2);
            iTicketService.save(ticket);
        }

        return new ResponseEntity<>(carRouteIndividual1, HttpStatus.CREATED);
    }

    @GetMapping("/startTime")
    public ResponseEntity<CarAndDriverDto> findDriverAndCarFree(String startTime) {
        String timeConvert = LocalDateTime.parse(startTime).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<Driver> driverList = iDriverService.findAllDriverFree(timeConvert);
        List<Car> carList = iCarService.findAllCarFree(timeConvert);
        System.out.println(startTime);
        System.out.println(driverList.size());
        System.out.println(carList.size());

        CarAndDriverDto carAndDriverDto = new CarAndDriverDto(driverList, carList);
        return new ResponseEntity<>(carAndDriverDto, HttpStatus.OK);
    }

    @GetMapping("/endTime")
    public ResponseEntity<CarAndDriverDto> findDriverAndCarFreeByTime(String startTime, String endTime) {
        String startTimeConvert = LocalDateTime.parse(startTime).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String endTimeConvert = LocalDateTime.parse(endTime).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        List<Driver> driverList = iDriverService.findAllDriverFreeByTime(startTimeConvert, endTimeConvert);
        List<Car> carList = iCarService.findAllCarFreeByTime(startTimeConvert, endTimeConvert);

        CarAndDriverDto carAndDriverDto = new CarAndDriverDto(driverList, carList);
        return new ResponseEntity<>(carAndDriverDto, HttpStatus.OK);
    }
}
