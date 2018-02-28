package ch.voulgarakis.icsc2018;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import ch.voulgarakis.icsc2018.recruitment.controller.impl.CRUDSkillController;
import ch.voulgarakis.icsc2018.recruitment.controller.interfaces.SkillController;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableDiscoveryClient
// @EnableFeignClients(clients = SkillFeign.class)
public class SkillServer {
    @LoadBalanced // Explicitly request the load-balanced template with Ribbon built-in
    @Bean // NO LONGER auto-created by Spring Cloud (see below)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SkillController skillController() {
        return new CRUDSkillController();
    }

    public static void main(String[] args) {
        // Will configure using accounts-server.yml
        System.setProperty("spring.config.name", "microservice");
        System.setProperty("spring.cloud.bootstrap.name", "skill-service-bootstrap");

        SpringApplication.run(SkillServer.class, args);
    }
}