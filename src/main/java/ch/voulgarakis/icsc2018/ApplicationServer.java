package ch.voulgarakis.icsc2018;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import ch.voulgarakis.icsc2018.recruitment.controller.impl.CRUDApplicationController;
import ch.voulgarakis.icsc2018.recruitment.controller.interfaces.ApplicationController;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableDiscoveryClient
// @EnableFeignClients(clients = ApplicantFeign.class)
public class ApplicationServer {
    @LoadBalanced // Explicitly request the load-balanced template with Ribbon built-in
    @Bean // NO LONGER auto-created by Spring Cloud (see below)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ApplicationController applicationController() {
        return new CRUDApplicationController();
    }

    public static void main(String[] args) {
        // Will configure using accounts-server.yml
        System.setProperty("spring.config.name", "microservice");
        System.setProperty("spring.cloud.bootstrap.name", "application-service-bootstrap");

        SpringApplication.run(ApplicationServer.class, args);
    }
}