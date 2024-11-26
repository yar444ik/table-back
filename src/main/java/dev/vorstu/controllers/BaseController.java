package dev.vorstu.controllers;

import dev.vorstu.dto.Student;
import dev.vorstu.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/base")
public class BaseController {
    @Autowired
    private final StudentRepository studentRepository;

    public BaseController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //private final List<Student> students = new ArrayList<>();

//    @GetMapping("/students/filter")
//    public Student getStudentById(@RequestParam(value = "group") String group) {
//        return students.stream()
//                .filter(student -> student.getGroup().equals(group))
//                .findFirst()
//                .orElse(null);
//    }

    @PostMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student createStudent(@RequestBody Student newStudent) {
        return addStudent(newStudent);
    }

    private Student addStudent(Student student) {
        return studentRepository.save(student);
    }

//    @GetMapping(value ="/students")
//    public Page<Student> getAllStudents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
//        try {
//            return studentRepository.findAll(PageRequest.of(page, size));
//        } catch (IllegalArgumentException e) {
//            System.out.println("Error creating pageable: " + e.getMessage());
//            return Page.empty();
//        }
//    }
    @GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

//    public Page<Student> findAll(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        return studentRepository.findAll(pageable);
//    }

    @PutMapping(value = "/students/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student changeStudent(@RequestBody Student changingStudent) {

        return updateStudent(changingStudent);
    }

    private Student updateStudent(Student student) {
        if(student.getId() == null) {
            throw new RuntimeException("Student id is null");
        }

        Student changingStudent = studentRepository.findById(student.getId())
                .orElseThrow(() -> new RuntimeException("student with id: " + student.getId() + "was not found" ));
        changingStudent.setName(student.getName());
        changingStudent.setSurname(student.getSurname());
        changingStudent.setGroup(student.getGroup());
        return studentRepository.save(changingStudent);
    }

    @DeleteMapping(value = "/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long deleteStudent(@PathVariable("id") Long id) {
        return removeStudent(id);
    }

    private Long removeStudent(Long id) {
        studentRepository.deleteById(id);
        return id;
    }

//    @GetMapping(value = "/students/{id}")
//    public Student getStudent(@PathVariable("id") Long id) {
//        return students.stream()
//                .filter(x -> x.getId().equals(id))
//                .findFirst()
//                .orElseThrow(()-> new RuntimeException("Student with id: " + id + " not found"));
//    }
}