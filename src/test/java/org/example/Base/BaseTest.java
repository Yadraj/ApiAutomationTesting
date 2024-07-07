package org.example.Base;

import com.fasterxml.jackson.core.JsonProcessingException;
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



                @BeforeMethod(alwaysRun = true)
                public void setConfig() {

                    payloadManager = new PayloadManager();
                    actions = new AssertActions();
                    requestSpecification = RestAssured.given();
                    requestSpecification.baseUri(ApiConstants.Base_URL);
                    requestSpecification.contentType(ContentType.JSON);

//              requestSpecification = new RequestSpecBuilder()
//                .setBaseUri(APIConstants.BASE_URL)
//                .addHeader("Content-Type", "application/json")
//                .build().log().all();

                }


                public String getToken() throws JsonProcessingException {

                        requestSpecification = RestAssured.given().baseUri(ApiConstants.Base_URL).basePath("/auth")
                                .contentType(ContentType.JSON);
                        String payload = PayloadManager.setToken();
                        response=requestSpecification.body(payload).when().post();
                        validatableResponse=response.then();

                        jsonPath = new JsonPath(response.asString());
                        return jsonPath.getString("token");


                }




}
