package dev.vorstu;

import dev.vorstu.entities.StudentEntity;
import dev.vorstu.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer {
    @Autowired
    private StudentRepository studentRepository;

    public void initial() {
        studentRepository.save(new StudentEntity(1L, "Aleksandr Kolomensky", "VM", "+71"));
        studentRepository.save(new StudentEntity(2L, "Alexey Kolomensky", "AM", "+72"));
        studentRepository.save(new StudentEntity(3L, "Yarik", "IM", "+73"));
    }
}
