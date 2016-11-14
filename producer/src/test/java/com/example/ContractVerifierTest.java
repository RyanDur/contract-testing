package com.example;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import com.jayway.restassured.response.ResponseOptions;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static org.assertj.core.api.Java6Assertions.assertThat;

@SpringBootTest
public class ContractVerifierTest extends MvcTest {

    @Test
    public void validate_shouldMarkClientAsFraud() throws Exception {
        // given:
        MockMvcRequestSpecification request = given()
                .header("Content-Type", "application/vnd.fraud.v1+json")
                .body("{\"clientId\":\"1234567890\",\"loanAmount\":99999}");

        // when:
        ResponseOptions response = given().spec(request)
                .put("/fraudcheck");

        // then:
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.header("Content-Type")).isEqualTo("application/vnd.fraud.v1+json;charset=UTF-8");

        // and:
        DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
        assertThatJson(parsedJson).field("fraudCheckStatus").isEqualTo("FRAUD");
        assertThatJson(parsedJson).field("rejectionReason").isEqualTo("Amount too high");
    }
}
