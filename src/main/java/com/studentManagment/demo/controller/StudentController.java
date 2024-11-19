package com.studentManagment.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentManagment.demo.model.Student;
import com.studentManagment.demo.service.StudentService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping("/saveStudent")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
    return new ResponseEntity<Student>(studentService.saveStudent(student),HttpStatus.CREATED);
    }
    //GetAll Rest Api
    @GetMapping
    public List<Student> getAllStudents(){
    return studentService.getAllStudent();
    }

    //Get by Id Rest Api
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long studentID){
    return new ResponseEntity<Student>(studentService.getStudentById(studentID),HttpStatus.OK);
    }
    
    @GetMapping("{id}/department")
    public ResponseEntity<String> getDepartmentByStudentId(@PathVariable("id") Integer id) {
        return new ResponseEntity<String>(studentService.getDepartmentByStudentId(id), HttpStatus.OK);
    }

    @GetMapping("/year/{yearOfEnrollment}")
    public ResponseEntity<List<Student>> getStudentsByYearOfEnrollment(@PathVariable("yearOfEnrollment") Integer yearOfEnrollment) {
        return new ResponseEntity<List<Student>>(studentService.getStudentsByYearOfEnrollment(yearOfEnrollment), HttpStatus.OK);
    }
    

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable long id,@RequestBody Student student){
    return new ResponseEntity<Student>(studentService.updateStudent(student,id),HttpStatus.OK);
    }

    //Delete Rest Api
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable long id){
    //delete student from db
    studentService.deleteStudent(id);
    return new ResponseEntity<String>("Student deleted Successfully.",HttpStatus.OK);
    }


    @DeleteMapping("/year/{yearOfEnrollment}")
    public ResponseEntity<String> deleteStudentsByYearOfEnrollment(@PathVariable("yearOfEnrollment") Integer yearOfEnrollment) {
        studentService.deleteStudentsByYearOfEnrollment(yearOfEnrollment);
        return new ResponseEntity<String>("Students Deleted Successfully", HttpStatus.OK);
    }

}