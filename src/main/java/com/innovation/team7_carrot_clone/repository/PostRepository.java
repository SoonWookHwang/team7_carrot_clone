package com.innovation.team7_carrot_clone.repository;


import com.innovation.team7_carrot_clone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();
    <T> List<T> findAllBy(Class<T> type);

}
