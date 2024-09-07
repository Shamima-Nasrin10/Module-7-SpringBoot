package com.shamima.SCMSystem.entity;

import jakarta.persistence.Access;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private int id;
    private String username;
    private String email;
    private String password;
    private String role;
}
