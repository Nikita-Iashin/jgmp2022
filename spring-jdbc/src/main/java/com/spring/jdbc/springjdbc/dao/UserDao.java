package com.spring.jdbc.springjdbc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void findUserTask1() {
        jdbcTemplate.execute("select  * from User where (select count from Friendship where timeBecomeFriends > 01-01-2025) " +
                "and (select userId from Likes were count(userId) > 100000 )");
    }

}