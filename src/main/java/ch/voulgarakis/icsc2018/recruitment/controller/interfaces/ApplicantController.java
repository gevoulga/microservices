package ch.voulgarakis.icsc2018.recruitment.controller.interfaces;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ch.voulgarakis.icsc2018.recruitment.model.Applicant;

@RequestMapping("/applicant")
public interface ApplicantController {

    // -------------------Retrieve All Applicants---------------------------------------------

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Applicant>> listAllApplicants(HttpServletRequest request);

    // -------------------Retrieve Single Applicant------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Applicant> getApplicant(@PathVariable("id") long id);

    @RequestMapping(value = "/", params = "name", method = RequestMethod.GET)
    public ResponseEntity<Applicant> getApplicant(@RequestParam("name") String name);

    // -------------------Create Single Applicants---------------------------------------------

    @RequestMapping(value = "", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<Applicant> createApplicant(HttpServletRequest request, @RequestBody Applicant applicant);

    // -------------------Update Applicants-------------------------------------------

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<Applicant> createApplicants(HttpServletRequest request,
            @RequestBody List<Applicant> applicants);

    // ------------------- Update Single Applicant ------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Applicant> updateApplicant(@PathVariable("id") long id, @RequestBody Applicant applicant);

    // ------------------- Delete Single Applicant-----------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Applicant> deleteApplicant(@PathVariable("id") long id);

    // ------------------- Delete All Applicants-----------------------------

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<Applicant> deleteAllApplicants();
}
