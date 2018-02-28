package ch.voulgarakis.icsc2018.recruitment.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ch.voulgarakis.icsc2018.recruitment.model.Applicant;
import ch.voulgarakis.icsc2018.recruitment.model.Application;
import ch.voulgarakis.icsc2018.recruitment.model.Skill;
import ch.voulgarakis.icsc2018.recruitment.model.Vacancy;

@RestController
public class CRUDStateController {
    @Autowired
    private RestTemplate rest;

    @RequestMapping(value = "/applicant", method = RequestMethod.GET)
    public ResponseEntity<List<Applicant>> listAllApplicants() {
        return rest.exchange("http://applicant-service/applicant", HttpMethod.GET, HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<Applicant>>() {
                });
    }

    @RequestMapping(value = "/application", method = RequestMethod.GET)
    public ResponseEntity<List<Application>> listAllApplications() {
        return rest.exchange("http://application-service/application", HttpMethod.GET, HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<Application>>() {
                });
    }

    @RequestMapping(value = "/vacancy", method = RequestMethod.GET)
    public ResponseEntity<List<Vacancy>> listAllVacancies() {
        return rest.exchange("http://vacancy-service/vacancy", HttpMethod.GET, HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<Vacancy>>() {
                });
    }

    @RequestMapping(value = "/skill", method = RequestMethod.GET)
    public ResponseEntity<List<Skill>> listAllSkills() {
        return rest.exchange("http://skill-service/skill", HttpMethod.GET, HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<Skill>>() {
                });
    }
}
