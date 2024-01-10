package com.example.ticket_management.controller.restful;

import com.example.ticket_management.dto.CustomerDTO;
import com.example.ticket_management.dto.ICarRouteIndividualDTO;
import com.example.ticket_management.dto.ITicketDTO1;
import com.example.ticket_management.service.ICarRouteIndividualService;
import com.example.ticket_management.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/ws")
public class RESTCarRouteIndividualController {
    @Autowired
    private ICarRouteIndividualService iCarRouteIndividualService;

    @Autowired
    private ITicketService iTicketService;

    @GetMapping("/revenue")
    public ResponseEntity<Iterable<ICarRouteIndividualDTO>> getRevenue(){
        Iterable<ICarRouteIndividualDTO> iCarRouteIndividualDTOS = iCarRouteIndividualService.findAllByRevenue();
        return new ResponseEntity<>(iCarRouteIndividualDTOS,HttpStatus.OK);
    }

    @PostMapping("/addticket")
    public ResponseEntity<?> addTicket(@RequestBody CustomerDTO customerDTO, BindingResult bindingResult, @RequestBody Integer id){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/listCRI")
    public ResponseEntity<CRIListResponse> listCRI(@RequestParam("page") Integer page, @PageableDefault(value = 6) Pageable pageable){
        Page<ICarRouteIndividualDTO> iCarRouteIndividualDTOS = iCarRouteIndividualService.findAllDTO(pageable);
        Integer CRITotalPage = iCarRouteIndividualDTOS.getTotalPages();
        CRIListResponse criTotalPage = new CRIListResponse(iCarRouteIndividualDTOS,CRITotalPage);
        return new ResponseEntity<>(criTotalPage, HttpStatus.OK);
    }
    private static class CRIListResponse {
        private final Page<ICarRouteIndividualDTO> iCarRouteIndividualDTOS;
        private final Integer totalPage;

        public CRIListResponse(Page<ICarRouteIndividualDTO> iCarRouteIndividualDTOS, Integer totalPage) {
            this.iCarRouteIndividualDTOS = iCarRouteIndividualDTOS;
            this.totalPage = totalPage;
        }

        public Page<ICarRouteIndividualDTO> getiCarRouteIndividualDTOS() {
            return iCarRouteIndividualDTOS;
        }

        public Integer getTotalPage() {
            return totalPage;
        }
    }

    @GetMapping("/listTicket")
    public ResponseEntity<TicketListResponse> listTicketDTO(@RequestParam("idCRI") Integer idCRI,
                                                          @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                          @PageableDefault(value = 6) Pageable pageable){
        Page<ITicketDTO1> iTicketDTOS = iTicketService.findAllByIdCRI(idCRI,pageable);
        Integer totalPage = iTicketDTOS.getTotalPages();
        TicketListResponse ticketListResponse = new TicketListResponse(iTicketDTOS,totalPage);
        return new ResponseEntity<>(ticketListResponse,HttpStatus.OK);
    }
    private static class TicketListResponse {
        private final Page<ITicketDTO1> iTicketDTOS;
        private final Integer totalPage;

        public TicketListResponse(Page<ITicketDTO1> iTicketDTOS, Integer totalPage) {
            this.iTicketDTOS = iTicketDTOS;
            this.totalPage = totalPage;
        }

        public Page<ITicketDTO1> getITicketDTOS() {
            return iTicketDTOS;
        }

        public Integer getTotalPage() {
            return totalPage;
        }
    }
}
