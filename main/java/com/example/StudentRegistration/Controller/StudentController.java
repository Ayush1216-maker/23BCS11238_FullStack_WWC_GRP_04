package com.example.StudentRegistration.Controller;

import com.example.StudentRegistration.model.studentModel;
import com.example.StudentRegistration.service.studentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@RequestMapping("/Registration")
public class StudentController {

    private final studentService studentService;

    public StudentController(studentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public ResponseEntity<String> registerStudent(@RequestBody studentModel s) {

        if (s.getName() == null || s.getName().isEmpty()
                || s.getCourse() == null || s.getCourse().isEmpty()) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Name and course must not be empty");
        }

        try {
            studentService.registerStudent(s);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Student registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Student with this ID already exists");
        }
    }

    @GetMapping
    public ResponseEntity<List<studentModel>> getAllStudent() {
        return ResponseEntity.ok(studentService.getallstudent());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable int id) {

        boolean isDeleted = studentService.deleteStudent(id);

        if (!isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Student with ID " + id + " not found");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Student with ID " + id + " deleted successfully");
    }


}
