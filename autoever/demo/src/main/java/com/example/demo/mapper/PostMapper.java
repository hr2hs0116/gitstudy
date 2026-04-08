package com.example.demo.mapper;

import com.example.demo.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 게시글 관련 DB 접근 매퍼 (MyBatis)
 */
@Mapper
public interface PostMapper {
    /** 게시글 전체 목록 조회 */
    List<Post> findAll();
    /** 게시글 단건 조회 */
    Post findById(@Param("id") Long id);
    /** 게시글 등록 */
    int insert(Post post);
    /** 게시글 수정 */
    int update(Post post);
    /** 게시글 삭제 */
    int delete(@Param("id") Long id);
    /** 게시글 조회수 증가 */
    int increaseViewCount(@Param("id") Long id);
}