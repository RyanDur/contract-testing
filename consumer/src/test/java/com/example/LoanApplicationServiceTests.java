package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/*
for more information on using stubs:
http://cloud.spring.io/spring-cloud-static/spring-cloud-contract/1.0.3.RELEASE/#_spring_cloud_contract_stub_runner
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(ids = {
        "com.example:producer-stubs:+:stubs:8080"
}, workOffline = true)
@DirtiesContext
public class LoanApplicationServiceTests {

    @Test
    public void test() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();

        FraudCheck fraudCheck = new FraudCheck();
        fraudCheck.setClientId("1234567890");
        fraudCheck.setLoanAmount(new BigDecimal(99999));

        httpHeaders.add("Content-Type", "application/vnd.fraud.v1+json");
        HttpEntity<FraudCheck> request = new HttpEntity<>(fraudCheck, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8080/fraudcheck",
                HttpMethod.PUT,
                request,
                String.class
        );

        String body = response.getBody();
        assertThat(body).isEqualTo("{\"fraudCheckStatus\":\"FRAUD\",\"rejectionReason\":\"Amount too high\"}");
    }
}
