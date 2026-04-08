package com.example.demo.controller;

import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/posts/{postId}/comments")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/comments/{id}")
    public Comment getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @PostMapping("/posts/{postId}/comments")
    public int createPost(@PathVariable Long postId, @RequestBody Comment comment) {
        comment.setPostId(postId);
        return commentService.createComment(comment);
    }

    @PutMapping("/comments/{id}")
    public int updatePost(@PathVariable Long id, @RequestBody Comment comment) {
        comment.setId(id);
        return commentService.updateComment(comment);
    }

    @DeleteMapping("/comments/{id}")
    public int deletePost(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }

}
