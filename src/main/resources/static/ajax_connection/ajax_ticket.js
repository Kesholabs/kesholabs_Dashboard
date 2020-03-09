$(document).ready(function () {
    console.log("TICKET PAGE")
    $.ajax({
        type: "GET",
        url: "http://34.83.183.132:3111/api/v1/admin/generatedTicket",
        beforeSend: function(){
            $(".spinner").html("<img>");
            // $("#generatedTickets").hide();
            // $("#ungeneratedTickets").hide();
            // $("#mpesa").hide();
            // $("#visa").hide();
            // $("#failedMpesa").hide();
            // $("#failedVisa").hide();
            // $("#delivered").hide();
            // $("#undelivered").hide();
        },
        success: function (data, textStatus) {
            $(".spinner").hide();
            console.log("Status " + textStatus + " DATA ", data)
            if (textStatus === "success") {
                const tickets = data.message;
                let count = 0;

                let genticketCount = 0;
                let ungenticketCount = 0;

                let mpesaCount = 0;
                let visaCount = 0;

                let failedMpesaCount = 0;
                let failedVisaCount = 0;

                let deliveredCount = 0;
                let undeliveredCount = 0;

                tickets.forEach((tic) => {
                    console.log(count++)
                    console.log("ticket ", tic)

                    //GENERATED TICKETS
                    if (tic.paymentStatus) {
                        console.log("generated Ticket", genticketCount++)
                    }else{
                        console.log("ungenerated Ticket", ungenticketCount++)
                    }

                    //PAYMENTS
                    if (tic.paymentStatus && tic.transType === "STK") {
                        console.log("MPESA PAYMENT", mpesaCount++)
                    } else if (tic.paymentStatus && tic.transType === "CARD") {
                        console.log("VISA PAYMENT ", visaCount++)
                    }

                    //FAILED PAYMENTS

                    if (!tic.paymentStatus && tic.transType === "STK") {
                        console.log("FAILED MPESA PAYMENT", failedMpesaCount++)
                    } else if (!tic.paymentStatus && tic.transType === "CARD") {
                        console.log("FAILED CARD PAYMENT", failedVisaCount++)
                    }

                    //DELIVERED

                    if (tic.isDelivered) {
                        console.log("TICKET DELIVERED", deliveredCount++)
                    } else {
                        console.log("TICKET UNDELIVERED", undeliveredCount++)
                    }
                })


                $("#generatedTickets").text(genticketCount);
                $("#ungeneratedTickets").text(ungenticketCount);
                $("#mpesa").text(mpesaCount);
                $("#visa").text(visaCount);
                $("#failedMpesa").text(failedMpesaCount);
                $("#failedVisa").text(failedVisaCount);
                $("#delivered").text(deliveredCount);
                $("#undelivered").text(undeliveredCount);
            }
        }
    });
});