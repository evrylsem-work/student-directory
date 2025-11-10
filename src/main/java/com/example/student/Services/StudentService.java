package com.example.student.Services;

import java.util.List;

import com.example.student.Dto.StudentDto;
import com.example.student.Entity.Student;

public interface StudentService {
    List<StudentDto> getAllStudents();
    StudentDto getStudentById(Long id);
    StudentDto saveStudent(StudentDto studentDto);
    StudentDto updateStudent(StudentDto studentDto);
    StudentDto deleteStudent(Long id);
    boolean existsById(Long id);
    long count();
    List<StudentDto> getStudentByMajor(String major);
}
