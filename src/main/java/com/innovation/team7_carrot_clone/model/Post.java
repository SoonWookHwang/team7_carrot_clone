package com.innovation.team7_carrot_clone.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Post extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long post_id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String price;

    @Column
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_user_post"))
    private User user;

    @Builder
    public Post(String title, String contents, String category, String price, String imageUrl){
        this.title = title;
        this.contents = contents;
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public void update(String title, String contents, String category, String price, String imageUrl){
        this.title = title;
        this.contents = contents;
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;    }

    public void mapToUser(User userFoundById) {
        this.user = userFoundById;
        userFoundById.mapToContents(this);
    }

    public void mapToPost(String postImageURL) {
        this.imageUrl = postImageURL;
    }
}
