package com.example.contracts.fraud;

import com.example.contracts.FraudEndpointBase;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import com.jayway.restassured.response.ResponseOptions;
import org.junit.Test;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

public class EndpointTest extends FraudEndpointBase {

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
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).isEqualTo("application/vnd.fraud.v1+json;charset=UTF-8");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("rejectionReason").isEqualTo("Amount too high");
		assertThatJson(parsedJson).field("fraudCheckStatus").isEqualTo("FRAUD");
}

}
