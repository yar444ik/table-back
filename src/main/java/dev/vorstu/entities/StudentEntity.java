package dev.vorstu.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "students")
public class StudentEntity {

    public StudentEntity(String name, String surname, String group) {
        this.name = name;
        this.surname = surname;
        this.group = group;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    @Column(name = "group_of_students")
    private String group;
}
