package com.kesholabs.mpesadashboard.dao;

import com.kesholabs.mpesadashboard.models.response.ValidatedTicketsModel;

import java.util.List;

public interface TicketDaoImpl {
    List<ValidatedTicketsModel> fetchAllTickets();
    int noOfTickets();

}
