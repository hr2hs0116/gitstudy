package com.example.demo.mapper;

import com.example.demo.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<Comment> findByPostId(Long postId);
    Comment findById(Long id);
    int insert(Comment comment);
    int update(Comment comment);
    int delete(Long id);
}

