package com.example.ticket_management.controller.restful;

import com.example.ticket_management.dto.SearchDto;
import com.example.ticket_management.model.CarAndDriverDto;
import com.example.ticket_management.model.CarRouteIndiviDto;
import com.example.ticket_management.model.*;
import com.example.ticket_management.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/public")
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

    @PostMapping("/search")
    public ResponseEntity<List<SearchDto>> searchTicket(@RequestBody SearchDto searchDto) {
        CarRoute carRoute = iCarRouteService.findCarRouteByStartingPointAndEndingPoint(searchDto.getStartPoint(), searchDto.getEndPoint());
        String timeConvert;
        if (carRoute != null) {
            if (LocalDate.parse(searchDto.getStartTime()).isEqual(LocalDate.now())) {

                LocalTime currentTime = LocalTime.now();
                timeConvert = LocalDateTime.of(LocalDate.now(), currentTime)
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                System.out.println(timeConvert);

//            2024-01-06 19:52:00
            } else {
                timeConvert = LocalDate.parse(searchDto.getStartTime())
                        .atStartOfDay()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }
            List<CarRouteIndividual> listSearch = iCarRouteIndividualService.findCarouteByStartTimeAndIdRoute(timeConvert, carRoute.getId());
            System.out.println(listSearch.size());
            List<SearchDto> searchDtos = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String startTime, endTime;
            for (CarRouteIndividual carRouteIndividual : listSearch) {
                startTime = carRouteIndividual.getStartTime().format(formatter);
                endTime = carRouteIndividual.getEndTime().format(formatter);
                searchDtos.add(new SearchDto(carRouteIndividual.getId(),
                        carRouteIndividual.getCarRoute().getStartingPoint(),
                        carRouteIndividual.getCarRoute().getEndingPoint(),
                        startTime,
                        endTime,
                        carRouteIndividual.getPrice())
                );
            }

            return new ResponseEntity<>(searchDtos, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }
}
