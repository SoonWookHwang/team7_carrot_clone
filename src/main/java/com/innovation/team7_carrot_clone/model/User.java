package com.innovation.team7_carrot_clone.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity(name = "Users")
@Entity
public class User extends Timestamped {
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
//    @Column(name = "user_id")
    @Id
    private Long id;
    @Column(
            nullable = false
    )
    private String username;

    @Column(nullable = false
    )
    private String userPhoneNum;
    @Column(
            nullable = false
    )
    private String password;

    private String imageUrl;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Post> postList = new ArrayList<>();

    public User() {
    }

    public User(String username, String password, String userPhoneNum) {
        this.username = username;
        this.password = password;
        this.userPhoneNum = userPhoneNum;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUserPhoneNum() {
        return this.userPhoneNum;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void mapToContents(Post post) {
        postList.add(post);
    }

    public void mapToUser(String userImageURL) {
        this.imageUrl = userImageURL;
    }
}