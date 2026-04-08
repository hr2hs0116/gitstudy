package com.example.demo.model;

import java.time.LocalDateTime;

/**
 * 게시글 엔티티 (posts 테이블 매핑)
 */
public class Post {
    /** 게시글 고유 ID (PK) */
    private Long id;
    /** 게시글 제목 */
    private String title;
    /** 게시글 내용 */
    private String content;
    /** 생성 시각 */
    private LocalDateTime createdAt;
    /** 수정 시각 */
    private LocalDateTime updatedAt;
    private int viewCount;

    public Post() {}

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
    public int getViewCount() { return viewCount; }
    public void setViewCount(int viewCount) { this.viewCount = viewCount; }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", viewCount=" + viewCount +
                '}';
    }
}