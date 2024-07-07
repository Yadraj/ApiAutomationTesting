package org.example.crud;

import org.example.Base.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.example.endpoints.ApiConstants;
import org.example.modules.PayloadManager;
import org.example.payloads.response.BookingResponse;
import org.testng.annotations.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TC_CreatingBooking extends BaseTest {

    @Test(groups = {"staging,P0"})
    @Owner("yadraj")
    @Severity(SeverityLevel.CRITICAL)
    @Description("verify negative test case")

    public void postReq() throws JsonProcessingException, io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException {



        requestSpecification.basePath(ApiConstants.CREATE_BOOKING);
        response = requestSpecification.when().body(payloadManager.createPayload()).post();
        validatableResponse = response.then().log().all();
        System.out.println("after log");
        validatableResponse.statusCode(200);

        // Another method to verify response:

        //jsonPath = JsonPath.from(response.asString());
        //String bookingId = jsonPath.getString("bookingId");

        //2nd method to verify response:

       BookingResponse bookingResponse =  PayloadManager.JsonToObject(response.asString());
       assertThat(bookingResponse.getBookingid()).isNotNull();





    }
}
