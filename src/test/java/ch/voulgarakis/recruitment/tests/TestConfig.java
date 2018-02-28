package ch.voulgarakis.recruitment.tests;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class TestConfig {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate rest = new RestTemplate();
        // rest.setErrorHandler(new DefaultResponseErrorHandler() {
        // @Override
        // public boolean hasError(ClientHttpResponse response) throws IOException {
        // return false; // Never throw exception for any HTTP error code...!
        // }
        // });
        return rest;
    }
}