package com.tutorial.studentservice.service;

import com.tutorial.studentservice.entity.Student;
import com.tutorial.studentservice.feignclients.SubjectFeignClient;
import com.tutorial.studentservice.model.Subject;
import com.tutorial.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SubjectFeignClient subjectFeignClient;


    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student save(Student student) {
        Student studentNew = studentRepository.save(student);
        return studentNew;
    }

    public List<Subject> getSubjects(int studentId) {
        List<Subject> subjects = restTemplate.getForObject("http://subject-service/subject/bystudent/" + studentId, List.class);
        return subjects;
    }

    public Subject saveSubject(int studentId, Subject subject) {
        subject.setStudentId(studentId);
        Subject subjectNew = subjectFeignClient.save(subject);
        return subjectNew;
    }








}
