package com.cloud.computing.edziennik.repository;

import com.cloud.computing.edziennik.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student findById(int id);

    Student findByPeselAndPassword(String pesel, String password);
}
