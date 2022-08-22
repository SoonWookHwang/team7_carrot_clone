//package com.innovation.team7_carrot_clone.repository;
//
//import com.innovation.team7_carrot_clone.model.Comment;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface CommentRepository extends JpaRepository<Post, Long> {
//    List<Comment> findAllByOrderByCreatedAtDesc(Long postId); // comment 리스트
//    Optional<Comment> findByCommentId(Long commentId);
//
//    int countByPostId(Long PostId);
//
//}