package com.example.demo.repository;

import com.example.demo.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 게시글 DB 접근(조회/저장/수정/삭제) 담당
 * SQL을 직접 작성, JdbcTemplate으로 실행
 */
@Repository
public class PostRepository {
    private final JdbcTemplate jdbcTemplate;

    public PostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ResultSet → Post 객체로 변환하는 RowMapper
    private RowMapper<Post> postRowMapper = (rs, rowNum) -> {
        Post post = new Post();
        post.setId(rs.getLong("id"));
        post.setTitle(rs.getString("title"));
        post.setContent(rs.getString("content"));
        post.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        post.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return post;
    };

    // 전체 게시글 목록 조회
    public List<Post> findAll() {
        return jdbcTemplate.query("SELECT * FROM posts ORDER BY created_at DESC", postRowMapper);
    }

    // ID로 게시글 단건 조회
    public Post findById(Long id) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM posts WHERE id = ?", postRowMapper, id
        );
    }

    // 새 게시글 저장
    public int save(Post post) {
        return jdbcTemplate.update(
            "INSERT INTO posts (title, content, created_at, updated_at) VALUES (?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)",
            post.getTitle(), post.getContent()
        );
    }

    // 게시글 수정
    public int update(Long id, Post post) {
        return jdbcTemplate.update(
            "UPDATE posts SET title = ?, content = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?",
            post.getTitle(), post.getContent(), id
        );
    }

    // 게시글 삭제
    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM posts WHERE id = ?", id);
    }
} 