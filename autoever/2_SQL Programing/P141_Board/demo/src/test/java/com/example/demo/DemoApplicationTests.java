package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void h2DatabaseConnectionTest() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            // 연결이 성공하면 conn이 null이 아님
            assertThat(conn).isNotNull();
            System.out.println("H2 DB 연결 성공!");
        }
    }
}
