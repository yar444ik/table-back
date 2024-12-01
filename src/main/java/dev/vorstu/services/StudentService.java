package dev.vorstu.services;

import dev.vorstu.dto.StudentDTO;
import dev.vorstu.entities.StudentEntity;
import dev.vorstu.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public StudentDTO updateStudent(StudentDTO studentData, Long id) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        toStudentEntity(studentData, student);
        studentRepository.save(student);
        StudentDTO studentDTO = toStudentDTO(student);
        return studentDTO;
    }

    public Page<StudentDTO> findAll(int page, int size, String sortField, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<StudentEntity> studentEntities = studentRepository.findAll(pageable);
        Page<StudentDTO> studentDtos = studentEntities.map(this::toStudentDTO);
        return  studentDtos;
    }

    public Long deleteStudentById(Long id) {
        studentRepository.deleteById(id);
        return id;
    }

    public StudentDTO saveStudent(StudentDTO newStudent) {
        StudentEntity student = toStudentEntity(newStudent);
        studentRepository.save(student);
        StudentDTO StudentDTO = toStudentDTO(student);
        return StudentDTO;
    }

    public  Page<StudentDTO> findByFilter(String filter, int page, int size, String sortField, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<StudentEntity> studentEntities = studentRepository.findByFilter(filter, pageable);
        Page<StudentDTO> studentDTOs = studentEntities.map(this::toStudentDTO);
        return studentDTOs;
    }

    private StudentEntity toStudentEntity(StudentDTO studentCreateDto) {
        var studentEntity  = new StudentEntity();
        studentEntity.setSurname(studentCreateDto.getSurname());
        studentEntity.setName(studentCreateDto.getName());
        studentEntity.setGroup(studentCreateDto.getGroup());
        return studentEntity;
    }

    private void toStudentEntity(StudentDTO studentUpdateDto, StudentEntity studentEntity) {
        studentEntity.setSurname(studentUpdateDto.getSurname());
        studentEntity.setName(studentUpdateDto.getName());
        studentEntity.setGroup(studentUpdateDto.getGroup());
    }

    private StudentDTO toStudentDTO(StudentEntity studentEntity) {
        var studentDTO = new StudentDTO();
        studentDTO.setId(studentEntity.getId());
        studentDTO.setName(studentEntity.getName());
        studentDTO.setSurname(studentEntity.getSurname());
        studentDTO.setGroup(studentEntity.getGroup());
        return  studentDTO;
    }
}
