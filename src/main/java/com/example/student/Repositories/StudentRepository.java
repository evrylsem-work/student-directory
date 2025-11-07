package com.example.student.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
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
}
