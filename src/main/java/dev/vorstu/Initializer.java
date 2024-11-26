package dev.vorstu;

import dev.vorstu.dto.Student;
import dev.vorstu.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer {
    @Autowired
    private StudentRepository studentRepository;

    public void initial() {
        studentRepository.save(new Student(1L, "Aleksandr Kolomensky", "VM", "+71"));
        studentRepository.save(new Student(2L, "Alexey Kolomensky", "AM", "+72"));
        studentRepository.save(new Student(3L, "Yarik", "IM", "+73"));
    }
}
