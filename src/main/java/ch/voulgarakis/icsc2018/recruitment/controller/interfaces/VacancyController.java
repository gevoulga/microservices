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

import ch.voulgarakis.icsc2018.recruitment.model.Vacancy;

@RequestMapping("/vacancy")
public interface VacancyController {

    // -------------------Retrieve All Vacancies---------------------------------------------

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Vacancy>> listAllVacancies(HttpServletRequest request);

    // -------------------Retrieve Single Vacancy------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Vacancy> getVacancy(@PathVariable("id") long id);

    @RequestMapping(params = "name", method = RequestMethod.GET)
    public ResponseEntity<Vacancy> getVacancy(@RequestParam("name") String name);

    // -------------------Create Single Vacancies---------------------------------------------

    @RequestMapping(value = "", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<Vacancy> createVacancy(HttpServletRequest request, @RequestBody Vacancy Vacancy);

    // -------------------Update Vacancies-------------------------------------------

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<Vacancy> createVacancies(HttpServletRequest request, @RequestBody List<Vacancy> Vacancys);

    // ------------------- Update Single Vacancy ------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Vacancy> updateVacancy(@PathVariable("id") long id, @RequestBody Vacancy Vacancy);

    // ------------------- Delete Single Vacancy-----------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Vacancy> deleteVacancy(@PathVariable("id") long id);

    // ------------------- Delete All Vacancies-----------------------------

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<Vacancy> deleteAllVacancies();
}
