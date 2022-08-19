package com.innovation.team7_carrot_clone.model;

import javax.persistence.*;

@Entity(name = "Users")
public class User extends Timestamped {
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Id
    private Long user_id;
    @Column(
            nullable = false
    )
    private String username;
    @Column(
            nullable = false
    )
    private String password;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setId(Long user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUser_id() {
        return this.user_id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public User() {
    }
}