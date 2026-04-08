package com.example.demo.model;

import java.time.LocalDateTime;

/**
 * 게시글 엔티티 클래스
 * posts 테이블의 각 행(row)에 해당
 */
public class Post {
    private Long id;                // 게시글 고유 ID (PK)
    private String title;           // 게시글 제목
    private String content;         // 게시글 내용
    private LocalDateTime createdAt;// 생성 시각
    private LocalDateTime updatedAt;// 수정 시각

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
} 