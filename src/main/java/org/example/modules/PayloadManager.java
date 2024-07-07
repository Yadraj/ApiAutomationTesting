package org.example.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.payloads.request.Booking;
import org.example.payloads.request.Bookingdates;
import org.example.payloads.response.BookingResponse;
import org.testng.annotations.Test;

public class PayloadManager {

    static ObjectMapper mapper = new ObjectMapper();

    @Test
    public String createPayload() throws JsonProcessingException {

        Booking booking = new Booking();

        booking.setFirstname("yadraj");
        booking.setLastname("shinde");
        booking.setAdditionalneeds("lunch");
        booking.setDepositpaid(true);
        booking.setTotalprice(100);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");

        booking.setBookingdates(bookingdates);



        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        System.out.println(json);
        return json;

    }


    public static BookingResponse JsonToObject(String response) throws JsonProcessingException {

        BookingResponse bookingResponse =  mapper.readValue(response,BookingResponse.class);
        System.out.println(bookingResponse);
        return bookingResponse;

    }

}




