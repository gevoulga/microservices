package ch.voulgarakis.icsc2018.recruitment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.voulgarakis.icsc2018.recruitment.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, String> {
    // https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-creating-database-queries-from-method-names/
    // Return skill by name
    public Skill findByName(String name);

    public boolean existsByName(String name);

    // https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-creating-database-queries-with-the-query-annotation/
    // custom query example and return a stream
    // @Query("select c from Skills c where c.name like 'skillNameBeginning%'")
    // Stream<Skill> skillsBeginningWith(@Param("skillNameBeginning") String skillNameBeginning);
}
