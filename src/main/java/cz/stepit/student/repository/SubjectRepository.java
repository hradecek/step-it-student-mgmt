package cz.stepit.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cz.stepit.student.entity.Subject;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {

    Optional<Subject> findByName(String name);

    @Override
    List<Subject> findAll();
}
