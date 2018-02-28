package ch.voulgarakis.recruitment.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import ch.voulgarakis.icsc2018.recruitment.model.Applicant;
import ch.voulgarakis.icsc2018.recruitment.model.Application;
import ch.voulgarakis.icsc2018.recruitment.model.Skill;
import ch.voulgarakis.icsc2018.recruitment.model.Vacancy;
import ch.voulgarakis.icsc2018.recruitment.utils.SkillAndWeight;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@ContextConfiguration(classes = TestConfig.class) // Setup test with TestConfig
// @ActiveProfiles("rest")
// @Transactional
public class TestMicroservices {
    // Startup the different microservices
    // static {
    // // Eureka
    // Properties propsEureka = new Properties();
    // propsEureka.setProperty("spring.config.name", "eureka");
    // propsEureka.setProperty("spring.cloud.bootstrap.name", "eureka-bootstrap");
    // SpringApplicationBuilder builder1 = new SpringApplicationBuilder(EurekaServer.class);
    // builder1.properties(propsEureka).run(new String[] { "" });
    //
    // // Recruitment Microservice
    // Properties propsRecruitment = new Properties();
    // propsRecruitment.setProperty("spring.config.name", "microservice");
    // propsRecruitment.setProperty("spring.cloud.bootstrap.name", "microservice-bootstrap");
    // SpringApplicationBuilder builder2 = new SpringApplicationBuilder(RecruitmentServer.class);
    // builder2.properties(propsRecruitment).run(new String[] { "" });
    //
    // // Skill Microservice
    // Properties propsSkills = new Properties();
    // propsSkills.setProperty("spring.config.name", "microservice");
    // propsSkills.setProperty("spring.cloud.bootstrap.name", "skill-service-bootstrap");
    // new SpringApplicationBuilder(SkillServer.class).properties(propsSkills).run(new String[] { "" });
    //
    // // Applicant Microservice
    // Properties propsApplicant = new Properties();
    // propsApplicant.setProperty("spring.config.name", "microservice");
    // propsApplicant.setProperty("spring.cloud.bootstrap.name", "applicant-service-bootstrap");
    // new SpringApplicationBuilder(ApplicantServer.class).properties(propsApplicant).run(new String[] { "" });
    // }

    Logger logger = LoggerFactory.getLogger(TestMicroservices.class);
    String url = "http://localhost:8766/recruitment-service";

    @Autowired
    private RestTemplate rest;
    private static RestTemplate restForDouble;
    static {
        restForDouble = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        // messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
        messageConverters.add(new ByteArrayHttpMessageConverter());
        // messageConverters.add(new ResourceHttpMessageConverter());
        // messageConverters.add(new SourceHttpMessageConverter<Source>());
        // messageConverters.add(new AllEncompassingFormHttpMessageConverter());
        // messageConverters.add(new TypeConstrainedMappingJackson2HttpMessageConverter(Skill.class));
        restForDouble.setMessageConverters(messageConverters);
    }

    @Test
    public void test2CreateApplicantsAndVacancies() {

        ///////////
        // Johnny be Good! https://youtu.be/ZFo8-JqzSCM
        // Who is also bad and ugly! https://youtu.be/h1PfrmCGFnk
        ///////////
        ResponseEntity<Applicant> postResponse1 = rest.postForEntity(url + "/recruitment/applicant",
                new HttpEntity<>(new Applicant("Johnny", new SkillAndWeight(new Skill("Good"), 0.8d),
                        new SkillAndWeight(new Skill("Bad"), 0.2d), new SkillAndWeight(new Skill("Ugly"), 0.4d))),
                Applicant.class);
        logger.info("\n\tpostResponse1: {}", postResponse1);
        assertTrue("1st POST should have returned Created", postResponse1.getStatusCode().equals(HttpStatus.CREATED));

        ///////////
        // Claus -> maybe he can apply for the santa position?
        ///////////
        ResponseEntity<Applicant> postResponse2 = rest.postForEntity(
                url + "/recruitment/applicant", new HttpEntity<>(new Applicant("Claus",
                        new SkillAndWeight(new Skill("Giftbearer"), 1d), new SkillAndWeight(new Skill("Aviator"), 1d))),
                Applicant.class);
        logger.info("\n\tpostResponse2: {}", postResponse2);
        assertTrue("2nd POST should have returned Created", postResponse2.getStatusCode().equals(HttpStatus.CREATED));

        ///////////
        // Lion King Scar... A total scum, ugly as fuck and a lazy bastard!
        ///////////
        ResponseEntity<Applicant> postResponse3 = rest.postForEntity(url + "/recruitment/applicant",
                new HttpEntity<>(new Applicant("Scar", new SkillAndWeight(new Skill("Bad"), 1d),
                        new SkillAndWeight(new Skill("Lazy"), 1d), new SkillAndWeight(new Skill("Ugly"), 1d))),
                Applicant.class);
        logger.info("\n\tpostResponse3: {}", postResponse3);
        assertTrue("3rd POST should have returned Created", postResponse3.getStatusCode().equals(HttpStatus.CREATED));

        ///////////
        // The Santa vacancy... We want somebody bearing gifts and can fly!
        ///////////
        ResponseEntity<Vacancy> postResponse4 = rest.postForEntity(url + "/recruitment/vacancy",
                new HttpEntity<>(new Vacancy("Santa", new SkillAndWeight(new Skill("Giftbearer"), 0.9d),
                        new SkillAndWeight(new Skill("Aviator"), 0.9d))),
                Vacancy.class);
        logger.info("\n\tpostResponse4: {}", postResponse4);
        assertTrue("4th POST should have returned Created", postResponse4.getStatusCode().equals(HttpStatus.CREATED));

        ///////////
        // The job of a good guy, is to be a good guy!
        ///////////
        ResponseEntity<Vacancy> postResponse5 = rest.postForEntity(url + "/recruitment/vacancy",
                new HttpEntity<>(new Vacancy("BeAGoodGuy", new SkillAndWeight(new Skill("Good"), 0.9d))),
                Vacancy.class);
        logger.info("\n\tpostResponse5: {}", postResponse5);
        assertTrue("5th POST should have returned Created", postResponse5.getStatusCode().equals(HttpStatus.CREATED));

        ///////////
        // GET from all the CRUD DBs the number of their entries
        ///////////
        logger.info("Added Johnny, Claus, Santa, Scar, BeAGoodGuy...");
        String ret = "";
        // Now display the Skill repository
        ret += "\n" + String.format("Skill repository contents: %d",
                rest.getForEntity(url + "/skill", List.class).getBody().size());
        for (Object skill : rest.getForEntity(url + "/skill", List.class).getBody())
            ret += "\n" + String.format("\t%s", skill);
        ret += "\n" + String.format("----------------");
        // Now display the Application repository
        ret += "\n" + String.format("Application repository contents: %d",
                rest.getForEntity(url + "/applicant", List.class).getBody().size());
        for (Object app : rest.getForEntity(url + "/applicant", List.class).getBody())
            ret += "\n" + String.format("\t%s", app);
        ret += "\n" + String.format("----------------");
        // Now display the Vacancy repository
        ret += "\n" + String.format("Vacancy repository contents: %d",
                rest.getForEntity(url + "/vacancy", List.class).getBody().size());
        for (Object vac : rest.getForEntity(url + "/vacancy", List.class).getBody())
            ret += "\n" + String.format("\t%s", vac);
        ret += "\n" + String.format("----------------");
        logger.info(ret);
        assertTrue("Skill Repository ended up with more entries... Check for duplicates...",
                rest.getForEntity(url + "/skill", List.class).getBody().size() == 6);
        assertTrue("Applicant Repository ended up with more entries... Check for duplicates...",
                rest.getForEntity(url + "/applicant", List.class).getBody().size() == 3);
        assertTrue("Vacancy Repository ended up with more entries... Check for duplicates...",
                rest.getForEntity(url + "/vacancy", List.class).getBody().size() == 2);

    }

    @Test
    public void test3ApplyClausForSanta() {
        // Claus can apply to become santa?
        ResponseEntity<Double> getResponse = restForDouble.exchange(
                url + "/recruitment/apply?applicantName=Claus&vacancyName=Santa", HttpMethod.PUT, null, Double.class);
        Double fit = getResponse.getBody();
        logger.info("\n\tgetResponse: {}", getResponse);
        assertTrue("Claus should have gotten the job of Santa.", fit >= 0.9);

        ///////////
        // GET from all the CRUD DBs the number of their entries
        ///////////
        logger.info("Added Johnny, Claus, Santa, Scar, BeAGoodGuy...");
        String ret = "";
        // Now display the Skill repository
        ret += "\n" + String.format("Skill repository contents: %d",
                rest.getForEntity(url + "/skill", List.class).getBody().size());
        for (Object skill : rest.getForEntity(url + "/skill", List.class).getBody())
            ret += "\n" + String.format("\t%s", skill);
        ret += "\n" + String.format("----------------");
        // Now display the Application repository
        ret += "\n" + String.format("Application repository contents: %d",
                rest.getForEntity(url + "/applicant", List.class).getBody().size());
        for (Object app : rest.getForEntity(url + "/applicant", List.class).getBody())
            ret += "\n" + String.format("\t%s", app);
        ret += "\n" + String.format("----------------");
        // Now display the Vacancy repository
        ret += "\n" + String.format("Vacancy repository contents: %d",
                rest.getForEntity(url + "/vacancy", List.class).getBody().size());
        for (Object vac : rest.getForEntity(url + "/vacancy", List.class).getBody())
            ret += "\n" + String.format("\t%s", vac);
        ret += "\n" + String.format("----------------");
        logger.info(ret);
        assertTrue("Skill Repository ended up with more entries... Check for duplicates...",
                rest.getForEntity(url + "/skill", List.class).getBody().size() == 6);
        assertTrue("Applicant Repository ended up with more entries... Check for duplicates...",
                rest.getForEntity(url + "/applicant", List.class).getBody().size() == 3);
        assertTrue("Vacancy Repository ended up with more entries... Check for duplicates...",
                rest.getForEntity(url + "/vacancy", List.class).getBody().size() == 2);

    }

    // @Test
    public void test4Cleanup() {
        ///////////
        // Send a 1st DELETE
        ///////////
        ResponseEntity<Application> deleteResponse1 = rest.exchange(url + "/application", HttpMethod.DELETE,
                HttpEntity.EMPTY, Application.class);
        logger.info("\n\tdeleteResponse1: {}", deleteResponse1);
        assertTrue("1st DELETE should have returned NoContent",
                deleteResponse1.getStatusCode().equals(HttpStatus.NO_CONTENT));

        ///////////
        // Send a 2nd DELETE
        ///////////
        ResponseEntity<Applicant> deleteResponse2 = rest.exchange(url + "/applicant", HttpMethod.DELETE,
                HttpEntity.EMPTY, Applicant.class);
        logger.info("\n\tdeleteResponse2: {}", deleteResponse2);
        assertTrue("1st DELETE should have returned NoContent",
                deleteResponse2.getStatusCode().equals(HttpStatus.NO_CONTENT));

        ///////////
        // Send a 3rd DELETE
        ///////////
        ResponseEntity<Vacancy> deleteResponse3 = rest.exchange(url + "/vacancy", HttpMethod.DELETE, HttpEntity.EMPTY,
                Vacancy.class);
        logger.info("\n\tdeleteResponse3: {}", deleteResponse3);
        assertTrue("1st DELETE should have returned NoContent",
                deleteResponse3.getStatusCode().equals(HttpStatus.NO_CONTENT));

        ///////////
        // Send a 4th DELETE
        ///////////
        ResponseEntity<Skill> deleteResponse4 = rest.exchange(url + "/skill", HttpMethod.DELETE, HttpEntity.EMPTY,
                Skill.class);
        logger.info("\n\tdeleteResponse1: {}", deleteResponse4);
        assertTrue("1st DELETE should have returned NoContent",
                deleteResponse4.getStatusCode().equals(HttpStatus.NO_CONTENT));

        // Check the test
        assertTrue("Application Repository ended up with more entries... Check for duplicates...",
                rest.getForEntity(url + "/application", List.class).getBody().size() == 0);
        assertTrue("Applicant Repository ended up with more entries... Check for duplicates...",
                rest.getForEntity(url + "/applicant", List.class).getBody().size() == 0);
        assertTrue("Vacancy Repository ended up with more entries... Check for duplicates...",
                rest.getForEntity(url + "/vacancy", List.class).getBody().size() == 0);
        assertTrue("Skill Repository ended up with more entries... Check for duplicates...",
                rest.getForEntity(url + "/skill", List.class).getBody().size() == 0);

    }
}
