package com.cloud.computing.edziennik.repository;

import com.cloud.computing.edziennik.entity.Lecturer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends CrudRepository<Lecturer, Integer> {

    Lecturer findById(int id);

    Lecturer findByPeselAndPassword(String pesel, String password);
}
