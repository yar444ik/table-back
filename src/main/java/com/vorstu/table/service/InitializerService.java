package com.vorstu.table.service;


import com.vorstu.table.dto.Student;
import com.vorstu.table.dto.auth.SignInRequest;
import com.vorstu.table.service.auth.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitializerService {

    private final StudentService studentService;
    private final JwtUserDetailsService userDetailsService;

    public void initial() {

        studentService.create(Student.builder()
                        .name("Aleksey")
                        .surname("Kolomenskiy")
                        .group("AV")
                .build()
        );
        studentService.create(Student.builder()
                        .name("Aleksandr")
                        .surname("Kolomenskiy")
                        .group("VM")
                .build()
        );
        studentService.create(Student.builder()
                .name("Yaroslav")
                .surname("Lyubchenko")
                .group("VM")
                .build()
        );
        studentService.create(Student.builder()
                .name("Kirill")
                .surname("Prosto")
                .group("AM")
                .build()
        );
        studentService.create(Student.builder()
                .name("Sergay")
                .surname("Korovin")
                .group("VM")
                .build()
        );
        studentService.create(Student.builder()
                .name("Petr")
                .surname("Kalyagin")
                .group("AM")
                .build()
        );

        userDetailsService.createUser(SignInRequest.builder()
                        .username("user1")
                        .pwd("1234")
                .build());
    }
}
