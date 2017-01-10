package com.example;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.NONE)
//@AutoConfigureStubRunner(ids = {"com.example:http-server-dsl:+:stubs:8080"}, workOffline = true)
//@DirtiesContext
//public class LoanApplicationServiceTests {
//
//    @Test
//    public void test() {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders httpHeaders = new HttpHeaders();
//
//        FraudCheck fraudCheck = new FraudCheck();
//        fraudCheck.setClientId("1234567890");
//        fraudCheck.setLoanAmount(new BigDecimal(99999));
//
//        httpHeaders.add("Content-Type", "application/vnd.fraud.v1+json");
//        HttpEntity<FraudCheck> request = new HttpEntity<>(fraudCheck, httpHeaders);
//
//        ResponseEntity<String> response = restTemplate.exchange(
//                "http://localhost:8080/fraudcheck",
//                HttpMethod.PUT,
//                request,
//                String.class
//        );
//
//        String body = response.getBody();
//        assertThat(body).isEqualTo("{\"fraudCheckStatus\":\"FRAUD\",\"rejectionReason\":\"Amount too high\"}");
//    }
//}
