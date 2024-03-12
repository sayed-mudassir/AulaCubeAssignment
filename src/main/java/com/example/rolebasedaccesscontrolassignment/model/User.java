package com.example.rolebasedaccesscontrolassignment.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id; //auto generated id

    @Column(nullable = false , unique = true)
    String userName; // must have an email as username and it also should be unique
    @Column(nullable = false , unique = true)
    String phoneNumber; // must have a phone number, and it also should be unique
    @Column(nullable = false)
    String password; // it must have a password
    String role;
}
