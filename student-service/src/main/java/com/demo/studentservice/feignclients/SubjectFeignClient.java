package com.demo.studentservice.feignclients;

import com.demo.studentservice.model.Subject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@FeignClient(name = "car-service")
@RequestMapping("/car")
public interface SubjectFeignClient {

    @PostMapping()
    Subject save(@RequestBody Subject subject);

    @GetMapping("/byuser/{studentId}")
    List<Subject> getSubjects(@PathVariable("studentId") int studentId);
}
