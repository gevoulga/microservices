package ch.voulgarakis.icsc2018;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import ch.voulgarakis.icsc2018.chat.config.WebSocketConfig;
import ch.voulgarakis.icsc2018.recruitment.config.CustomPropertySourceLocator;
import ch.voulgarakis.icsc2018.recruitment.controller.MatchController;
import ch.voulgarakis.icsc2018.recruitment.controller.impl.CRUDStateController;
import ch.voulgarakis.icsc2018.recruitment.controller.impl.RecruitmentControllerImpl;
import ch.voulgarakis.icsc2018.recruitment.controller.interfaces.RecruitmentController;
import ch.voulgarakis.icsc2018.recruitment.events.WebsocketSessionTracker;
import ch.voulgarakis.icsc2018.recruitment.service.RecruitmentMicroservice;
import ch.voulgarakis.icsc2018.recruitment.service.RecruitmentService;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableDiscoveryClient
// @EnableFeignClients(clients = RecruitmentFeign.class)
@Import({ WebSocketConfig.class, WebsocketSessionTracker.class })
public class RecruitmentServer {
    @LoadBalanced // Explicitly request the load-balanced template with Ribbon built-in
    @Bean // NO LONGER auto-created by Spring Cloud (see below)
    public RestTemplate restTemplate() {
        RestTemplate rest = new RestTemplate();
        rest.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return HttpStatus.CONFLICT.equals(response.getStatusCode()) ? false : super.hasError(response);
            }
        });
        return rest;
    }

    @Bean
    public RecruitmentController recruitmentController() {
        return new RecruitmentControllerImpl();
    }

    @Bean
    public CRUDStateController cRUDStateController() {
        return new CRUDStateController();
    }

    @Bean
    public RecruitmentService recruitmentService() {
        return new RecruitmentMicroservice();
    }

    @Bean
    public MatchController matchController() {
        return new MatchController();
    }

    @Bean
    public PropertySourceLocator propertySourceLocator() {
        return new CustomPropertySourceLocator();
    }

    public static void main(String[] args) {
        // Will configure using accounts-server.yml
        System.setProperty("spring.config.name", "microservice");
        System.setProperty("spring.cloud.bootstrap.name", "microservice-bootstrap");

        SpringApplication.run(RecruitmentServer.class, args);
    }
}