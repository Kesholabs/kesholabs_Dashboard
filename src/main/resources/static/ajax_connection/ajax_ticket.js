$(document).ready(function () {
    console.log("TICKET PAGE")
    $.ajax({
        type: "GET",
        url: "http://34.83.183.132:3111/api/v1/admin/generatedTicket",
        beforeSend: function () {
            $("#loader").html("<img src=/static/dist/img/loader.gif>");
            $("#loader1").html("<img src=/static/dist/img/loader.gif>");
            $("#loader2").html("<img src=/static/dist/img/loader.gif>");
            $("#loader3").html("<img src=/static/dist/img/loader.gif>");
            $("#loader4").html("<img src=/static/dist/img/loader.gif>");
            $("#loader5").html("<img src=/static/dist/img/loader.gif>");
            $("#loader6").html("<img src=/static/dist/img/loader.gif>");
            $("#loader7").html("<img src=/static/dist/img/loader.gif>");
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
            console.log("Status " + textStatus + " DATA ", data)
            if (textStatus === "success") {
                $("#loader").hide();
                $("#loader1").hide();
                $("#loader2").hide();
                $("#loader3").hide();
                $("#loader4").hide();
                $("#loader5").hide();
                $("#loader6").hide();
                $("#loader7").hide();
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
                    // console.log(count++)
                    // console.log("ticket ", tic)

                    //GENERATED TICKETS
                    if (tic.paymentStatus) {
                        console.log("generated Ticket", genticketCount++)
                    } else {
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

    $("#example1").DataTable({
        'responsive': true,
        "processing": true,
        "serverSide": true,
        "paging": true,
        "searching": { "regex": true },
        "pagingType": "full_numbers_no_ellipses",
        "ajax": {
            "url": "http://34.83.183.132:3111/api/v1/admin/generatedTicket",
            "type": "GET",
            "dataSrc": function (data) {
                return data.message;
            }
        },
        "columns": [
            {"data": "deliverToPhone"},
            {"data": "amount"},
            {"data": "paymentMode"},
            {"data": "paymentStatus"},
            {"data": "isDelivered"},
            {"data": "msg"},
            {"data": "pdfLocation"},
            {"data": "dateCreated"}
        ],
        'scrollCollapse': true,
        'ordering': true,
        "order": [0, "asc"],
        "lengthMenu": [[5, 10, 20, -1], [5, 10, 20, "All"]],
        "pageLength": 10,
        dom: 'Bfrtip',
        buttons: [
            'copyHtml5',
            'excelHtml5',
            'csvHtml5',
            'pdfHtml5',
            'print'
        ],
        "columnDefs": [
            {
                "targets": [1],
                "visible": false,
                "searchable": false
            },
            {
                "targets": [2],
                "visible": false
            }
        ],
    });
});