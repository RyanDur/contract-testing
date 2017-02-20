package com.example.contracts;

import com.example.controllers.FraudDetectionController;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

public class FraudEndpointBase {

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new FraudDetectionController());
    }
}
