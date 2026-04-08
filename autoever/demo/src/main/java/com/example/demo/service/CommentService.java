package com.example.demo.service;

import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.PostMapper;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public List<Comment> getCommentsByPostId(long postId) {
        return commentMapper.findByPostId(postId);
    }

    public Comment getCommentById(Long id) {
        return commentMapper.findById(id);
    }

    public int createComment(Comment comment) {
        return commentMapper.insert(comment);
    }

    public int updateComment(Comment comment) {
        return commentMapper.update(comment);
    }

    public int deleteComment(Long id) {
        return commentMapper.delete(id);
    }
}
