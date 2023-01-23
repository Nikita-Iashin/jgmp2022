package com.spring.jdbc.springjdbc.dao;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.sql.JDBCType;
import java.sql.Types;
import java.util.*;

@Component
public class ConSoleTools {

    private final JdbcTemplate jdbcTemplate;
    private Random ran;
    private Faker faker;

    public ConSoleTools(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        ran = new Random();
        faker = new Faker();
    }

    //It creates N random tables with random unique name (or names from dictionary)
    // and K random columns with type taken from Z random types
    public void createTables() {
        int N = ran.nextInt(100);
        int K = ran.nextInt(10);
        while (N > 0) {
            N--;
            jdbcTemplate.execute(String.format(
                    "create table %s (%s);", faker.funnyName().name(), generateColumns(K)));
        }
    }

    private String generateColumns(int quantity) {
        Map<String, String> results = new HashMap<>();
        for (int i = 0; i >= quantity; i++) {
            results.put(faker.funnyName().name(), getRandomJdbcType());
        }
        return parseMapToSqlColumnTypeFormat(results);
    }

    public String parseMapToSqlColumnTypeFormat(Map<String, String> columns) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> kvp : columns.entrySet()) {
            builder.append(kvp.getKey());
            builder.append(" ");
            builder.append(kvp.getValue());
            builder.append(",");
        }
        return builder.toString().trim();
    }

    private String getRandomJdbcType() {
        JDBCType[] values = JDBCType.values();
        int length = values.length;
        int randIndex = new Random().nextInt(length);
        return values[randIndex].name();
    }

    //It creates m random rows for the i-th table,
    // where m is an i-th element of M. M is an N-dimensional array predefined by a user of this tool.

    //It supports the table creation/populating via L concurrent connections
    // (from different threads or from a few instances of classes running simultaneously).
}
