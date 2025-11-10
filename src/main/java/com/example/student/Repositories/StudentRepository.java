package com.example.student.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.student.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
 // JpaRepository provides built-in methods:
    // - findAll()
    // - findById(id)
    // - save(entity)
    // - deleteById(id)
    // - existsById(id)
    
    @Query("SELECT s from Student s where LOWER(REPLACE(s.major, ' ', '')) LIKE CONCAT('%', LOWER(REPLACE(:major, ' ', '')), '%')")
    List<Student> findStudentByMajor(@Param("major") String major);
}
