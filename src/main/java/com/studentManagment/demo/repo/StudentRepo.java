package com.studentManagment.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.studentManagment.demo.model.Student;

import jakarta.transaction.Transactional;

public interface StudentRepo extends JpaRepository<Student,Long> {

    List<Student> findByYearOfEnrollment(Integer yearOfEnrollment);
     @Query("SELECT s.department FROM Student s WHERE s.id = :id")
    String findDepartmentByStudentId(Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Student s WHERE s.yearOfEnrollment = :yearOfEnrollment")
    void deleteByYearOfEnrollment(Integer yearOfEnrollment);
    
}