package ch.voulgarakis.icsc2018.recruitment.controller.interfaces;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.voulgarakis.icsc2018.recruitment.model.Applicant;
import ch.voulgarakis.icsc2018.recruitment.model.Application;
import ch.voulgarakis.icsc2018.recruitment.model.Vacancy;

@RequestMapping("/recruitment")
public interface RecruitmentController {
    @RequestMapping(value = "/applicant/{name}", method = RequestMethod.GET)
    public ResponseEntity<Applicant> loadApplicant(@PathVariable("name") String name);

    @RequestMapping(value = "/applicant", method = RequestMethod.POST)
    public ResponseEntity<Applicant> saveApplicant(@RequestBody Applicant applicant);

    @RequestMapping(value = "/vacancy/{name}", method = RequestMethod.GET)
    public ResponseEntity<Vacancy> loadVacancy(@PathVariable("name") String name);

    @RequestMapping(value = "/vacancy", method = RequestMethod.POST)
    public ResponseEntity<Vacancy> saveVacancy(@RequestBody Vacancy vacancy);

    @RequestMapping(value = "/apply", method = RequestMethod.PUT)
    public ResponseEntity<Double> apply(@RequestBody Application applicantVacancy);

    @RequestMapping(value = "/apply", method = RequestMethod.PUT, params = { "applicantId", "vacancyId" })
    public ResponseEntity<Double> apply(@PathParam("applicantId") long applicantId,
            @PathParam("vacancyId") long vacancyId);

    @RequestMapping(value = "/apply", method = RequestMethod.PUT, params = { "applicantName", "vacancyName" })
    public ResponseEntity<Double> apply(@PathParam("applicantName") String applicantName,
            @PathParam("vacancyName") String vacancyName);
}
