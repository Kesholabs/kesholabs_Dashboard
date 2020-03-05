package com.kesholabs.mpesadashboard.models.response;

import lombok.Data;

@Data
public class ValidatedTicketsModel {
        boolean paymentStatus;
        boolean isDelivered;
        String paymentMode;
        String eventID;
        String amount;
        String deliverToPhone;
        String transType;
        String msg;
        String pdfLocation;
        String dateCreated;
}
