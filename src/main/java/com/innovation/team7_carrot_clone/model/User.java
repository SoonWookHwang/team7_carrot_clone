package com.innovation.team7_carrot_clone.model;

import com.innovation.team7_carrot_clone.dto.UserResponseDto;

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
            nullable = false ,unique = true
    )
    private String userPhoneNum;
    @Column(
            nullable = false , unique = true
    )
    private String userName;
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

    public User(String userPhoneNum,String userName, String password) {
        this.userPhoneNum = userPhoneNum;
        this.userName = userName;
        this.password = password;
    }

    public User(UserResponseDto userResponseDto) {
        this.imageUrl = userResponseDto.getImageUrl();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Long getId() {
        return this.id;
    }

    public String getUserPhoneNum() {
        return this.userPhoneNum;
    }

    public String getPassword() {
        return this.password;
    }

    public String getImageUrl() { return this.imageUrl; }

    public void mapToContents(Post post) {
        postList.add(post);
    }

    public void mapToUser(String userImageURL) {
        this.imageUrl = userImageURL;
    }
}