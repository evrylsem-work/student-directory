package com.example.student.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.Dto.StudentDto;
import com.example.student.Services.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students") // Base URL: localhost:8080/students
@RequiredArgsConstructor
public class StudentApi {
    @Autowired
    private final StudentService studentService;
    
    public StudentApi(StudentService studentService) {
        this.studentService = studentService;
    }

    // URL: GET localhost:8080/students
    // Should return: List of all students with HTTP 200 OK
    // Hint: Use @GetMapping with no path
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAll() {
        return new ResponseEntity<List<StudentDto>>(studentService.getAllStudents(), HttpStatus.OK);
    }

    // TODO 2: Create GET endpoint to retrieve a student by ID;
    // URL: GET localhost:8080/students/{id}
    // Should return: Single student with HTTP 200 OK
    // Hint: Use @GetMapping("/{id}") and @PathVariable
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long id) {
        boolean flag = studentService.existsById(id);
        if (flag)
            System.out.println("Student no. #" + id + " is found.");
        else
            System.out.println("Student no. #" + id + " is NOT found.");
        return new ResponseEntity<StudentDto>(studentService.getStudentById(id), HttpStatus.OK);
    }

    // TODO 3: Create POST endpoint to add a new student
    // URL: POST localhost:8080/students
    // Should accept: JSON student data in request body
    // Should return: Created student with HTTP 201 CREATED
    // Hint: Use @PostMapping and @RequestBody
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto student) {
        return new ResponseEntity<StudentDto>(studentService.saveStudent(student), HttpStatus.CREATED);
    }
    
    // TODO 4: Create PUT endpoint to update a student
    // URL: PUT localhost:8080/students/{id}
    // Should accept: Student ID in path and JSON data in body
    // Should return: Updated student with HTTP 200 OK
    // Hint: Use @PutMapping("/{id}"), @PathVariable, and @RequestBody
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("id") Long id, @RequestBody StudentDto student) {
        student.setId(id);
        return new ResponseEntity<StudentDto>(studentService.updateStudent(student), HttpStatus.OK);
    }

    // TODO 5: Create DELETE endpoint to remove a student
    // URL: DELETE localhost:8080/students/{id}
    // Should return: Deleted student data with HTTP 200 OK
    // Hint: Use @DeleteMapping("/{id}") and @PathVariable
    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable("id") Long id) {
        return new ResponseEntity<StudentDto>(studentService.deleteStudent(id), HttpStatus.OK);
    }
    
    @GetMapping("/test")
    public String test() {
        return "✅ Controller is LIVE!";
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<StudentDto>> searchStudentByMajor(@RequestParam("major") String major) {
        return new ResponseEntity<List<StudentDto>>(studentService.getStudentByMajor(major), HttpStatus.OK);
    }
}
