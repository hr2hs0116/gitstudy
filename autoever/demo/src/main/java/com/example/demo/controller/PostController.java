package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 게시글 관련 REST API 컨트롤러
 */
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /** 게시글 전체 목록 조회 */
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    /** 게시글 단건 조회 */
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    /** 게시글 등록 */
    @PostMapping
    public int createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    /** 게시글 수정 */
    @PutMapping("/{id}")
    public int updatePost(@PathVariable Long id, @RequestBody Post post) {
        post.setId(id);
        return postService.updatePost(post);
    }

    /** 게시글 삭제 */
    @DeleteMapping("/{id}")
    public int deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

    /** 게시글 상세 조회 (조회수 증가) */
    @GetMapping("/{id}/raw")
    public Post getPostRawById(@PathVariable Long id) {
        return postService.getPostRawById(id);
    }
}