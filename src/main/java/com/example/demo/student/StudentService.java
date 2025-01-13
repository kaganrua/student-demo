package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
       return studentRepository.findAll();

    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("Student with email " + student.getEmail() + " already exists");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.findById(id).ifPresentOrElse(student -> studentRepository.deleteById(id), () -> {throw new IllegalStateException("Student with id " + id + " does not exist");} );
    }

    @Transactional
    public void updateStudent(Long studentId,  String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalStateException("Student with id " + studentId + " does not exist")
        );

        if(name != null &&
                !name.isEmpty() &&
            !Objects.equals(name, student.getName())) {
            student.setName(name);
        }

        if(email != null &&
        !email.isEmpty() &&
        !Objects.equals(email, student.getEmail())) {
            student.setEmail(email);
        }

    }
}
