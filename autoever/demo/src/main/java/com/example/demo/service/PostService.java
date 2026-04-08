package com.example.demo.service;

import com.example.demo.mapper.PostMapper;
import com.example.demo.model.Post;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * 게시글 비즈니스 로직 서비스
 */
@Service
public class PostService {
    private final PostMapper postMapper;

    @Autowired
    public PostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    /** 게시글 전체 목록 조회 */
    public List<Post> getAllPosts() {
        return postMapper.findAll();
    }

    /** 게시글 단건 조회 */
    public Post getPostById(Long id) {
        postMapper.increaseViewCount(id);
        return postMapper.findById(id);
    }

    /** 게시글 단건 조회 (조회수 증가 없음) */
    public Post getPostRawById(Long id) {
        return postMapper.findById(id);
    }

    /** 게시글 등록 */
    public int createPost(Post post) {
        return postMapper.insert(post);
    }

    /** 게시글 수정 */
    public int updatePost(Post post) {
        return postMapper.update(post);
    }

    /** 게시글 삭제 */
    public int deletePost(Long id) {
        return postMapper.delete(id);
    }
}