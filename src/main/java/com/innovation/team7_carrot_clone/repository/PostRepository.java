package com.innovation.team7_carrot_clone.repository;


import com.innovation.team7_carrot_clone.model.Post;
import com.innovation.team7_carrot_clone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();
    <T> List<T> findAllBy(Class<T> type);

    Optional<Post> findById(Long postId);
}
