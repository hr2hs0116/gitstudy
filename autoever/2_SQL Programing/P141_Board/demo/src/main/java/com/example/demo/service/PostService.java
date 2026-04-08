package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 게시글 비즈니스 로직 담당
 * Controller와 Repository 사이에서 데이터 검증 등 처리
 */
@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 전체 게시글 목록 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // ID로 게시글 단건 조회
    public Post getPostById(Long id) {
        return postRepository.findById(id);
    }

    // 새 게시글 생성
    public void createPost(Post post) {
        postRepository.save(post);
    }

    // 게시글 수정
    public void updatePost(Long id, Post post) {
        postRepository.update(id, post);
    }

    // 게시글 삭제
    public void deletePost(Long id) {
        postRepository.delete(id);
    }
} 