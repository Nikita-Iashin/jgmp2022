package com.global.metoring.lesson7;

import com.global.metoring.lesson7.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Lesson7ApplicationTests {

    @Autowired
    private UserController userController;

    @Test
    void contextLoads() {
    }

}
