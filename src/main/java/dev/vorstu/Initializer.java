package dev.vorstu;

import dev.vorstu.entities.Password;
import dev.vorstu.entities.Role;
import dev.vorstu.entities.StudentEntity;
import dev.vorstu.entities.UserEntity;
import dev.vorstu.repositories.StudentRepository;
import dev.vorstu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    public void initial() {
        UserEntity student = userRepository.save(new UserEntity(
                null,
                "student",
                Role.STUDENT,
                new Password("0000"),
                true
        ));

        UserEntity studentAdmin = userRepository.save(new UserEntity(
                null,
                "studentAdmin",
                Role.ADMIN,
                new Password("73568"),
                true
        ));

        studentRepository.save(new StudentEntity(1L, "Aleksandr", "Kolomensky", "VM", studentAdmin));
        studentRepository.save(new StudentEntity(2L, "Alexey", "Kolomensky", "AM", student));
        studentRepository.save(new StudentEntity(3L, "Yarik", "Lyubchenko", "AM", student));
        studentRepository.save(new StudentEntity(4L, "Petr", "Kalyagin", "AM", student));
        studentRepository.save(new StudentEntity(5L, "Sergey", "Korovin", "VM", student));
        studentRepository.save(new StudentEntity(6L, "Dima", "Ognerubov", "VM", student));
        studentRepository.save(new StudentEntity(7L, "Irina", "Kim", "VM", student));
        studentRepository.save(new StudentEntity(8L, "Arina", "Ivanova", "AM", student));
        studentRepository.save(new StudentEntity(9L, "Schakboz", "Nerusskiy", "VM", student));
        studentRepository.save(new StudentEntity(10L, "Skuchno", "Uzhe", "AM", student));
        studentRepository.save(new StudentEntity(11L, "Nikita", "Chumachenko", "AM", student));
    }
}
