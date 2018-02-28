package ch.voulgarakis.icsc2018.recruitment.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.voulgarakis.icsc2018.recruitment.utils.SkillAndWeight;

@Entity
// Circular JSON references will be replaced with property: "name" instead of the whole JSON string
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
public class Applicant {
    @Id
    @GeneratedValue
    private Long id;

    private String name; // The name of the applicant

    @ElementCollection
    private List<String> skillSet; // The competences of the application

    @ElementCollection
    private List<Double> skillStrength; // The strength of each skill

    @ElementCollection
    private List<Long> applications = new ArrayList<>(); // // The applications of this applicant

    protected Applicant() {
        // Empty constructor
        skillSet = new ArrayList<>();
        skillStrength = new ArrayList<>();
    }

    public Applicant(String name, SkillAndWeight... skillsAndStrength) {
        this.name = name;
        this.skillSet = Stream.of(skillsAndStrength).parallel().map(e -> e.getSkill().getId())
                .collect(Collectors.toList());
        this.skillStrength = Stream.of(skillsAndStrength).parallel().map(e -> e.getWeight())
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public List<String> getSkillSet() {
        return skillSet;
    }

    public List<Double> getSkillStrength() {
        return skillStrength;
    }

    public List<Long> getApplications() {
        return applications;
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
