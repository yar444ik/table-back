package dev.vorstu.controllers;

import dev.vorstu.dto.Student;
import dev.vorstu.repositories.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/base")
public class BaseController {
    @Autowired
    private final StudentRepository studentRepository;
    private Long counter = 0L;

    public BaseController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private Long generateId() { return counter++; }
    private final List<Student> students = new ArrayList<>();

    @PostConstruct
    public void init() {}

    @PostMapping(value = "students")
    public Student createStudent(@RequestBody Student newStudent) { return addStudent(newStudent); }

    private Student addStudent(Student student) {
        student.setId(generateId());
        students.add(student);
        return student;
    }

    @GetMapping("students")
    public List<Student> getAllStudents() {
        return students;
    }

    @PutMapping(value = "students")
    public Student changeStudent(@RequestBody Student changingStudent) {
        return updateStudent(changingStudent);
    }

    private Student updateStudent(Student student) {
        if(student.getId() == null) {
            throw new RuntimeException("Student id is null");
        }

        Student changingStudent = students.stream().filter(x -> x.getId().equals(student.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Student with id: " + student.getId() + " not found"));

        changingStudent.setId(student.getId());
        changingStudent.setName(student.getName());
        changingStudent.setSurname(student.getSurname());
        changingStudent.setGroup(student.getGroup());
        return changingStudent;
    }

    @DeleteMapping(value = "students/{id}")
    public Long deleteStudent(@PathVariable("id") Long id) {
        return removeStudent(id);
    }

    private Long removeStudent(Long id) {
        students.removeIf(x -> x.getId().equals(id));
        return id;
    }

    @GetMapping(value = "students/{id}")
    public Student getStudent(@PathVariable("id") Long id) {
        return students.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("Student with id: " + id + " not found"));
    }

    @GetMapping(value = "students/filter")
    public Student getStudentById(@RequestParam(value = "group") String group) {
        return students.stream()
                .filter(student -> student.getGroup().equals(group))
                .findFirst()
                .orElse(null);
    }

    public Page<Student> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable);
    }


}