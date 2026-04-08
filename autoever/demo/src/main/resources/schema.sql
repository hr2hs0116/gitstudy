CREATE TABLE posts (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '게시글 고유 ID',
                       title VARCHAR(255) NOT NULL COMMENT '게시글 제목',
                       content TEXT NOT NULL COMMENT '게시글 내용',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시각',
                       view_count INT DEFAULT 0 COMMENT '조회수'
);

CREATE TABLE comments (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '댓글 ID',
                       post_id BIGINT NOT NULL COMMENT '연결된 게시글 ID',
                       content TEXT NOT NULL COMMENT '댓글 내용',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '댓글 생성 시각',
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '댓글 수정 시각',
                       CONSTRAINT fk_post_id FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE
);


INSERT INTO posts (title, content) VALUES
                                       ('첫 번째 게시글', '안녕하세요! 첫 번째 게시글입니다.'),
                                       ('두 번째 게시글', '두 번째 게시글의 내용입니다.');


INSERT INTO comments (post_id, content) VALUES
                                       (1, '안녕하세요! 첫 번째 게시글에 대한 댓글입니다.'),
                                       (1, '첫번째 게시글에대한 두번째 댓글'),
                                       (2, '두번째 게시글에대한 첫번째 댓글');
