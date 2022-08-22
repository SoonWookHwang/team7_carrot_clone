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
    private String userPhoneNum;
    @Column(
            nullable = false //, unique = true
    )
    private String userName;
    @Column(
            nullable = false
    )
    private String password;


    public User(String userPhoneNum,String userName, String password) {
        this.userPhoneNum = userPhoneNum;
        this.userName = userName;
        this.password = password;
    }

    public void setId(Long user_id) {
        this.user_id = user_id;
    }

    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUser_id() {
        return this.user_id;
    }

    public String getUserPhoneNum() {
        return this.userPhoneNum;
    }

    public String getPassword() {
        return this.password;
    }

    public User() {
    }
}