package com.example.student.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.Dto.StudentDto;
import com.example.student.Entity.Student;
import com.example.student.Repositories.StudentRepository;
import org.modelmapper.ModelMapper;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    
    @Autowired
    public StudentServiceImplementation(StudentRepository studentRepo, ModelMapper modMapper) {
        this.studentRepository = studentRepo;
        this.modelMapper = modMapper;
    }
    
    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(student -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList());
    }
    
    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found."));
        return modelMapper.map(student, StudentDto.class);
    }
    
    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        student = studentRepository.save(student);
        studentDto = modelMapper.map(student, StudentDto.class);
        return studentDto;
    }
    
    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        Student student = studentRepository.findById(studentDto.getId()).orElseThrow(() -> new IllegalArgumentException());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setAge(studentDto.getAge());
        student.setEmail(studentDto.getEmail());
        student.setMajor(studentDto.getMajor());
        student.setGpa(studentDto.getGpa());
        student = studentRepository.save(student);
        studentDto = modelMapper.map(student, StudentDto.class);
        return studentDto;
    }
    
    @Override
    public StudentDto deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
        studentRepository.delete(student);
        return studentDto;
    }
    
    @Override
    public boolean existsById(Long id) {
        return studentRepository.existsById(id);
    }
    
    @Override
    public long count() {
        return studentRepository.count();
    }
    
    @Override
    public List<StudentDto> getStudentByMajor(String major) {
        List<Student> students = studentRepository.findStudentByMajor(major.trim().replaceAll("\\s+", " "));
        return students.stream().map(student -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList());
    }
}
