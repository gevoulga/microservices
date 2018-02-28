package ch.voulgarakis.icsc2018.recruitment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class Application {
    @Id
    @GeneratedValue
    private Long id;

    private double fitRatio; // How good, our application is!

    private long applicant; // Our applicant id
    private long vacancy; // Our vacancy id

    protected Application() {
        // Empty constructor
    }

    public Application(long applicant, long vacancy, double fitRatio) {
        super();
        this.applicant = applicant;
        this.vacancy = vacancy;
        this.fitRatio = fitRatio;
    }

    public Long getId() {
        return id;
    }

    public long getApplicant() {
        return applicant;
    }

    public long getVacancy() {
        return vacancy;
    }

    public double getFitRatio() {
        return fitRatio;
    }

    @Override
    public String toString() {
        // return name + ":" + skillSet + (id == null ? "" : ":" + id);
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
    }
}
