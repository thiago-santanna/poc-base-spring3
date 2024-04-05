package com.tss.webapps.projectbasepoc.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String whatsapp;
    private String password;

    public User(String name, String email, String encode) {
        this.name = name;
        this.email = email;
        this.password = encode;
    }
}
