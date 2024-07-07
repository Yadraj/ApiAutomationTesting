package org.example.misc;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class misc001 {

    @Test
    public void postRequest() {

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        RestAssured

                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType("application/json")
                .basePath("/auth")

                .when().body(payload).post()

                .then().log().all().statusCode(200);

    }


}
