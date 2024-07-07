package org.example.Base;

import org.example.Actions.AssertActions;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.endpoints.*;
import org.example.modules.PayloadManager;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public RequestSpecification requestSpecification;
    public  PayloadManager payloadManager;
    public AssertActions  actions;
    public Response response;
    public ValidatableResponse validatableResponse;
    public JsonPath jsonPath;



    @BeforeMethod
    public void setConfig() {

        System.out.println("I am able to run");
        payloadManager = new PayloadManager();
        actions = new AssertActions();
        requestSpecification = RestAssured.given()
                .baseUri(ApiConstants.Base_URL)
                .contentType(ContentType.JSON);

//        requestSpecification = new RequestSpecBuilder()
//                .setBaseUri(APIConstants.BASE_URL)
//                .addHeader("Content-Type", "application/json")
//                .build().log().all();

    }


}
