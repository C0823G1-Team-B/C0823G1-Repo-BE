package com.example.ticket_management.controller.restful;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import com.example.ticket_management.dto.CarRouteIndividualDTO;
import com.example.ticket_management.dto.CusDTO;
import com.example.ticket_management.dto.SearchDto;
import com.example.ticket_management.model.CarAndDriverDto;
import com.example.ticket_management.model.CarRouteIndiviDto;
import com.example.ticket_management.model.*;
import com.example.ticket_management.service.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
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
    @Autowired
    private ICustomerService iCustomerService;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;



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
    @PostMapping("/saveUpdate")
    public ResponseEntity<CarRouteIndividualDTO> createSaveUpdate(@RequestBody CarRouteIndividualDTO carRouteIndividualDTO) {
        CarRouteIndividual criCheck = iCarRouteIndividualService.findById(carRouteIndividualDTO.getIdCRI()).get();
        CarRouteIndividual carRouteIndividual = new CarRouteIndividual(
                carRouteIndividualDTO.getIdCRI(),
                carRouteIndividualDTO.getTimeStart(),
                carRouteIndividualDTO.getTimeEnd(),false,
                iCarService.findById(carRouteIndividualDTO.getIdCar()).get(),
                iDriverService.findById(carRouteIndividualDTO.getIdDriver()).get(),
                iCarRouteService.findById(carRouteIndividualDTO.getIdRoute()).get(),
                carRouteIndividualDTO.getPrice()
        );

        List<CusDTO> ticketList = iTicketService.findAllTicketByCRI(carRouteIndividual.getId());
        int check1 = carRouteIndividualDTO.getTimeStart().compareTo(criCheck.getStartTime());
        int check2 = carRouteIndividualDTO.getTimeEnd().compareTo(criCheck.getEndTime());
        boolean  check11 = check1 == 1;
        boolean check22 = check2 == 1;


        if (ticketList.isEmpty()){
            System.out.println("ko gui gmail ");
            iCarRouteIndividualService.save(carRouteIndividual);
            return new ResponseEntity<>(new CarRouteIndividualDTO(false),HttpStatus.OK);
        } else {
            iCarRouteIndividualService.save(carRouteIndividual);
            if ( (check11) || (check22) || (carRouteIndividualDTO.getIdRoute() != criCheck.getCarRoute().getId()) || (carRouteIndividualDTO.getIdCar() != criCheck.getCar().getId())){
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message);
                for (CusDTO temp : ticketList){
                    Customer customer = iCustomerService.findById(temp.getCustomerId()).get();
                    try{
                        helper.setTo(customer.getEmail());
                        helper.setSubject("[Nhà xe Hiếu Hoa] vui lòng xác nhận gmail thông báo");
                        Context context = new Context();
                        context.setVariable("username", customer.getName());
                        String space = "Vì 1 số lí do mà " + "Chuyến đi từ" + criCheck.getCarRoute().getStartingPoint() + "Đến" + criCheck.getCarRoute().getEndingPoint() + " vào lúc "
                                + criCheck.getStartTime() + " đến " + criCheck.getEndTime() + "có thể có sự thay đổi vào lúc" + criCheck.getStartTime() + " đến " +
                                criCheck.getEndTime() + "Nhân viên của chúng tôi sẽ liên hệ với bạn trong thời gian sớm nhất mong bạn bỏ qua";
                        context.setVariable("space",space);

                        String content = templateEngine.process("updateGmailForm.html", context);
                        helper.setText(content, true);
                        mailSender.send(message);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            }

            return new ResponseEntity<>(new CarRouteIndividualDTO(true),HttpStatus.OK);
        }

    }

    @GetMapping("/startTime")
    public ResponseEntity<CarAndDriverDto> findDriverAndCarFree(String startTime) {
        System.out.println(startTime);
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
    @GetMapping("/endTimeUp")
    public ResponseEntity<CarAndDriverDto> findDriverAndCarFreeByTimeUp(String startTime, String endTime) {
        String startTimeConvert = LocalDateTime.parse(startTime).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String endTimeConvert = LocalDateTime.parse(endTime).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        List<Driver> driverList = iDriverService.findAllDriverFreeByTimeUp(startTimeConvert, endTimeConvert);
        List<Car> carList = iCarService.findAllCarFreeByTimeUp(startTimeConvert, endTimeConvert);

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
            Integer ticketQuality;
            for (CarRouteIndividual carRouteIndividual : listSearch) {
                ticketQuality = iTicketService.findAllTicketByCiIdAndStatus(carRouteIndividual.getId(),0);
                startTime = carRouteIndividual.getStartTime().format(formatter);
                endTime = carRouteIndividual.getEndTime().format(formatter);
                searchDtos.add(new SearchDto(carRouteIndividual.getId(),
                        carRouteIndividual.getCarRoute().getStartingPoint(),
                        carRouteIndividual.getCarRoute().getEndingPoint(),
                        startTime,
                        endTime,
                        carRouteIndividual.getPrice(),ticketQuality)
                );
            }

            return new ResponseEntity<>(searchDtos, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("listCi")
    public ResponseEntity<List<CarRouteIndividualDTO>> listCarRouteIndividualDTO(){
        LocalTime currentTime = LocalTime.now();
       String timeConvert = LocalDateTime.of(LocalDate.now(), currentTime)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
       List<CarRouteIndividual> routeIndividualList = iCarRouteIndividualService.findAllIndividualByStartTime(timeConvert);
       List<CarRouteIndividualDTO> list = new ArrayList<>();
       String place;
       String car;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String startTime, endTime;
       for (CarRouteIndividual temp : routeIndividualList){
           startTime = temp.getStartTime().format(formatter);
           endTime = temp.getEndTime().format(formatter);
           place = temp.getCarRoute().getStartingPoint() + "Đến" + temp.getCarRoute().getEndingPoint();
           car = "Mã xe: "+ temp.getCar().getId() +", Biển số: " + temp.getCar().getLicensePlates() + ", Số ghế: " +temp.getCar().getTotalSeats();
           list.add(new CarRouteIndividualDTO(temp.getId(), startTime,
                   endTime,
                   place,
                   car,
                   temp.getDriver().getName(),
                   temp.getPrice()
                   ));
       }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("updateCRI")
    public ResponseEntity<CarRouteIndividualDTO> updateCRI(@RequestBody CarRouteIndividualDTO carRouteIndividualDTO){
        CarRouteIndividual carRouteIndividual = iCarRouteIndividualService.findById(carRouteIndividualDTO.getIdCRI()).get();
        System.out.println(carRouteIndividual.getStartTime());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
//        String startTime = carRouteIndividual.getStartTime().format(formatter);
//        String endTime = carRouteIndividual.getEndTime().format(formatter);
        String place = carRouteIndividual.getCarRoute().getStartingPoint() + " Đến" + carRouteIndividual.getCarRoute().getEndingPoint();
        String car = "Mã xe: "+ carRouteIndividual.getCar().getId() +", Biển số: " + carRouteIndividual.getCar().getLicensePlates() + ", Số ghế: " +carRouteIndividual.getCar().getTotalSeats();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
//        LocalDateTime startTime = LocalDateTime.parse(carRouteIndividual.getStartDateTime(), formatter);
//        LocalDateTime endTime = LocalDateTime.parse(carRouteIndividual.getEndDateTime(), formatter);

        iCarRouteIndividualService.updateDeleteById(carRouteIndividual.getId());
        List<CusDTO> ticketList = iTicketService.findAllTicketByCRI(carRouteIndividual.getId());
        return new ResponseEntity<>(new CarRouteIndividualDTO(
                carRouteIndividual.getId(),
                carRouteIndividual.getStartTime(),
                carRouteIndividual.getEndTime(),
                carRouteIndividual.getCarRoute().getId(),
                carRouteIndividual.getCar().getId(),
                carRouteIndividual.getDriver().getId(),
                carRouteIndividual.getPrice(),ticketList.isEmpty()
        ), HttpStatus.OK);
    }

}
