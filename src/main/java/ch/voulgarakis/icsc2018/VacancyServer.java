package ch.voulgarakis.icsc2018;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import ch.voulgarakis.icsc2018.recruitment.controller.impl.CRUDVacancyController;
import ch.voulgarakis.icsc2018.recruitment.controller.interfaces.VacancyController;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableDiscoveryClient
// @EnableFeignClients(clients = ApplicantFeign.class)
public class VacancyServer {
    @LoadBalanced // Explicitly request the load-balanced template with Ribbon built-in
    @Bean // NO LONGER auto-created by Spring Cloud (see below)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public VacancyController vacancyController() {
        return new CRUDVacancyController();
    }

    public static void main(String[] args) {
        // Will configure using accounts-server.yml
        System.setProperty("spring.config.name", "microservice");
        System.setProperty("spring.cloud.bootstrap.name", "vacancy-service-bootstrap");

        SpringApplication.run(VacancyServer.class, args);
    }
}