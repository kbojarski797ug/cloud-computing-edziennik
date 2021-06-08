package com.cloud.computing.edziennik.repository;

import com.cloud.computing.edziennik.entity.StudentGrade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentGradeRespository extends CrudRepository<StudentGrade, Integer> {
}
