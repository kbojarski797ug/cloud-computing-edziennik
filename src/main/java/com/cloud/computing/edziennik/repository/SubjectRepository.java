package com.cloud.computing.edziennik.repository;

import com.cloud.computing.edziennik.entity.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Integer> {

    Subject findById(int id);

    List<Subject> findByStudents_id(int id);

    List<Subject> findByLecturer_id(int id);
}