package com.spring.jdbc.springjdbc;

import com.spring.jdbc.springjdbc.dao.jdbc.One;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringJdbcApplicationTests {
    @Autowired
    One firstTask;

    @Test
    public void testFirstTask() {
        firstTask.getUser("Nikita");
    }

    @Test
    public void testFirstTaskStatement() {
        firstTask.getUserByStatement("Nikita");
    }

}
