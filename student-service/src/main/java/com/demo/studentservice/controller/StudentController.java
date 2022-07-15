package com.demo.studentservice.controller;

import com.demo.studentservice.entity.Student;

import com.demo.studentservice.model.Subject;
import com.demo.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        List<Student> students = studentService.getAll();
        if(students.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable("id") int id) {
        Student student = studentService.getStudentById(id);
        if(student == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(student);
    }

    @PostMapping()
    public ResponseEntity<Student> save(@RequestBody Student student) {
        Student studentNew = studentService.save(student);
        return ResponseEntity.ok(studentNew);
    }

    @GetMapping("/subjects/{studentId}")
    public ResponseEntity<List<Subject>> getSubjects(@PathVariable("studentId") int studentId) {
        Student student = studentService.getStudentById(studentId);
        if(student == null)
            return ResponseEntity.notFound().build();
        List<Subject> subjects = studentService.getSubjects(studentId);
        return ResponseEntity.ok(subjects);
    }


    @PostMapping("/savesubject/{studentId}")
    public ResponseEntity<Subject> saveSubject(@PathVariable("studentId") int studentId, @RequestBody Subject subject) {
        if(studentService.getStudentById(studentId) == null)
            return ResponseEntity.notFound().build();
        Subject subjectNew = studentService.saveSubject(studentId, subject);
        return ResponseEntity.ok(subject);
    }



}
