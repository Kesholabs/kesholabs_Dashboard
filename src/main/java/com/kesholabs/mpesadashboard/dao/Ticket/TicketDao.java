package com.kesholabs.mpesadashboard.dao.Ticket;


import com.kesholabs.mpesadashboard.models.Ticket.TicketModel;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@FeignClient("http://localhost:3111")
public interface TicketDao {
    @GetMapping(value = "/api/v1/admin/ticket/{id}")
    TicketModel getTicket();

    @GetMapping(value = "/api/v1/admin/generatedTicket")
    List<TicketModel> getTickets();

    void getDeliveredTickets();

}
