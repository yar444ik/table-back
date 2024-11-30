package dev.vorstu.controllers;

import dev.vorstu.entities.StudentEntity;
import dev.vorstu.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/base")
public class StudentController {
    @Autowired
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentEntity createStudent(@RequestBody StudentEntity newStudent) {
        return addStudent(newStudent);
    }

    private StudentEntity addStudent(StudentEntity student) {
        return studentRepository.save(student);
    }

    @GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentEntity> getAllStudents() {

        return studentRepository.findAll();
    }


    @PutMapping(value = "/students/", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentEntity changeStudent(@RequestBody StudentEntity changingStudent) {

        return updateStudent(changingStudent);
    }

    private StudentEntity updateStudent(StudentEntity student) {
        if(student.getId() == null) {
            throw new RuntimeException("Student id is null");
        }

        StudentEntity changingStudent = studentRepository.findById(student.getId())
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

    @GetMapping("students")
    public Page<StudentDTO> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        return studentService.findAll(page, size, sortField, sortDirection);
    }

    @GetMapping(value = "students/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<StudentDto> searchStudents(
            @RequestParam("filter") String filter,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "sortField", defaultValue = "id") String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection)
    {
        return studentService.findByFilter(filter,page,size, sortField, sortDirection);
    }
}