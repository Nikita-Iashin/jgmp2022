package com.spring.jdbc.springjdbc.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.Date;

@Component
public class One {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private One(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void getUser(String name) {
        String getUserByNameString = String.format(
                "select * from User where User.name like '%s'", name);
        jdbcTemplate.execute(getUserByNameString);
    }

    public void getUserByStatement(String name) {

        SqlParameterSource in = new MapSqlParameterSource().addValue("name", name);
        new SimpleJdbcCall(jdbcTemplate).withFunctionName("findUser").executeFunction(String.class, in);

    }

    public void deleteUserByName(String name) {
        String deleteUserByName = String.format(
                "delete from User where name like '%s';", name);
        jdbcTemplate.execute(deleteUserByName);
    }

    public void updateUserName(String name) {
        String updateUserName = String.format(
                "update User set name = %s where name like '%s'", name);
        jdbcTemplate.execute(updateUserName);
    }

    public void createUser(String name, String surname, Date birthday) {
        String newUser = String.format(
                "insert into User (name, surname, birthdate) values ('%s', '%s', '%s');", name, surname, birthday);
        jdbcTemplate.execute(newUser);
    }

    public void selectUsersWithPosts() {
        String selectUsersWithPosts = "select User.name from User inner join Posts on User.id = Posts.userId;";
        jdbcTemplate.execute(selectUsersWithPosts);
    }


}
