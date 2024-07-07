package org.example.Integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.path.json.JsonPath;
import org.apache.http.cookie.Cookie;
import org.example.Base.BaseTest;
import org.example.endpoints.ApiConstants;
import org.example.modules.PayloadManager;
import org.example.payloads.request.Booking;
import org.example.payloads.response.BookingResponse;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TC_Integration extends BaseTest {

    String ObjectbookingId;
    String jsonbookingId;
    String getToken;

    @Test(groups = {"P0"})

    public void testGetToken() throws JsonProcessingException {

        getToken = getToken();

    }

    @Test(groups = {"P0"})

    public void testCreateBooking() throws JsonProcessingException {

        requestSpecification.basePath(ApiConstants.CREATE_BOOKING);
        response = requestSpecification.when().body(payloadManager.createPayload()).post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        //Direct Extraction from jsonpath
        jsonPath = JsonPath.from(response.asString());
        jsonbookingId = jsonPath.getString("bookingid");


        //from booking reponse class
        BookingResponse bookingResponse =  PayloadManager.JsonToObject(response.asString());
        ObjectbookingId = bookingResponse.getBookingid().toString();


    }

    @Test(groups = {"P0"},dependsOnMethods = {"testCreateBooking"})

    public void testUpdateBooking() throws JsonProcessingException {

        requestSpecification.basePath(ApiConstants.UPDATE_BOOKING+"/"+ObjectbookingId)
                .cookie("token",getToken);

        response = requestSpecification.when().body(payloadManager.createPayloadPut()).put();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);


        Booking booking =  PayloadManager.JsonToObjectPut(response.asString());

        assertThat(booking.getFirstname()).isEqualTo("Raj");
        assertThat(booking.getLastname()).isNotEmpty();
        assertThat(booking.getBookingdates()).isNotNull();
        assertThat(booking.getDepositpaid()).isNotNull();


    }

    @Test(groups = {"P0"},dependsOnMethods = {"testUpdateBooking"})

    public void testDeleteBooking() {

        requestSpecification.basePath(ApiConstants.UPDATE_BOOKING+"/"+ObjectbookingId)
                .cookie("token",getToken);
        response = requestSpecification.when().delete();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);

    }

    @Test(groups = {"P0"},dependsOnMethods = {"testDeleteBooking"})

    public void testDeleteBookingByGET() {

        requestSpecification.basePath(ApiConstants.UPDATE_BOOKING+"/"+ObjectbookingId)
       .cookie("token",getToken);
        response = requestSpecification.when().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);

    }


}

