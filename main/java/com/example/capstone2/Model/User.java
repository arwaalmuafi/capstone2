package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name should not be empty")
    @Size(min = 4, message = "name should be longer then 3")
    @Column(columnDefinition = "varchar(20)")
    private String name;

    @NotEmpty(message = "email should not be empty")
    @Email
    @Column(columnDefinition = "varchar(20) unique ")
    private String email;

    @NotEmpty(message = "role should not be empty")
    @Pattern(regexp =  "^(gardener|user)$" ,message = "role should be gardener or user")
    @Column(columnDefinition = "varchar(9)")
    private String role;


    public User(){

    }

    public User(Integer id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotEmpty(message = "name should not be empty") @Size(min = 4, message = "name should be longer then 3") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "name should not be empty") @Size(min = 4, message = "name should be longer then 3") String name) {
        this.name = name;
    }

    public @NotEmpty(message = "email should not be empty") @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "email should not be empty") @Email String email) {
        this.email = email;
    }

    public @NotEmpty(message = "role should not be empty") @Pattern(regexp = "^(gardener|user)$", message = "role should be gardener or user") String getRole() {
        return role;
    }

    public void setRole(@NotEmpty(message = "role should not be empty") @Pattern(regexp = "^(gardener|user)$", message = "role should be gardener or user") String role) {
        this.role = role;
    }



}
