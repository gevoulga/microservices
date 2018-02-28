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

    // @Bean
    // @Order(0)
    // public ApplicationRunner eureka() {
    // return args -> {
    // // Eureka
    // Properties propsEureka = new Properties();
    // propsEureka.setProperty("spring.config.name", "eureka");
    // propsEureka.setProperty("spring.cloud.bootstrap.name", "eureka-bootstrap");
    // SpringApplicationBuilder builder1 = new SpringApplicationBuilder(EurekaServer.class);
    // builder1.properties(propsEureka).run(new String[] { "" });
    // };
    // }
    //
    // @Bean
    // @Order(1)
    // public ApplicationRunner recruitment() {
    // return args -> {
    // // Recruitment Microservice
    // Properties propsRecruitment = new Properties();
    // propsRecruitment.setProperty("spring.config.name", "microservice");
    // propsRecruitment.setProperty("spring.cloud.bootstrap.name", "microservice-bootstrap");
    // SpringApplicationBuilder builder2 = new SpringApplicationBuilder(RecruitmentServer.class);
    // builder2.properties(propsRecruitment).run(new String[] { "" });
    // };
    // }
    //
    // @Bean
    // @Order(2)
    // public ApplicationRunner skill() {
    // return args -> {
    // // Skill Microservice
    // Properties propsSkills = new Properties();
    // propsSkills.setProperty("spring.config.name", "microservice");
    // propsSkills.setProperty("spring.cloud.bootstrap.name", "skill-service-bootstrap");
    // new SpringApplicationBuilder(SkillServer.class).properties(propsSkills).run(new String[] { "" });
    // };
    // }
    //
    // @Bean
    // @Order(3)
    // public ApplicationRunner applicant() {
    // return args -> {
    // // Applicant Microservice
    // Properties propsApplicant = new Properties();
    // propsApplicant.setProperty("spring.config.name", "microservice");
    // propsApplicant.setProperty("spring.cloud.bootstrap.name", "applicant-service-bootstrap");
    // new SpringApplicationBuilder(ApplicantServer.class).properties(propsApplicant).run(new String[] { "" });
    // };
    // }
    //
    // @Bean
    // @Order(4)
    // public ApplicationRunner vacancy() {
    // return args -> {
    // // Vacancy Microservice
    // Properties propsVacancy = new Properties();
    // propsVacancy.setProperty("spring.config.name", "microservice");
    // propsVacancy.setProperty("spring.cloud.bootstrap.name", "vacancy-service-bootstrap");
    // new SpringApplicationBuilder(VacancyServer.class).properties(propsVacancy).run(new String[] { "" });
    // };
    // }
    //
    // @Bean
    // @Order(5)
    // public ApplicationRunner application() {
    // return args -> {
    // // Application Microservice
    // Properties propsApplication = new Properties();
    // propsApplication.setProperty("spring.config.name", "microservice");
    // propsApplication.setProperty("spring.cloud.bootstrap.name", "application-service-bootstrap");
    // new SpringApplicationBuilder(ApplicationServer.class).properties(propsApplication).run(new String[] { "" });
    // };
    // }
}