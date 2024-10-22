package com.project.service;

import com.project.model.Student;
import com.project.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service  // This marks the class as a service component for Spring to manage
public class StudentService {

    private final StudentRepository studentRepository;

    // Constructor-based injection of the repository
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Method to retrieve a student by matricule
    public Student getStudentByMatricule(String matricule) {
        return studentRepository.findByMatricule(matricule)
                .orElseThrow(() -> new RuntimeException("Student not found with matricule: " + matricule));
    }
    public Student save(Student student) {
        return studentRepository.save(student); // This saves the updated student
    }



}
