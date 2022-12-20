package com.jgmp2022.jgmp2022.dao;

import com.jgmp2022.jgmp2022.model.user.User;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAO {

    @Getter
    List<User> users;

    public User getUser(String name) {
        return users.stream().filter(e -> e.getName().equalsIgnoreCase(name)).findAny().orElse(null);
    }

    public boolean addUse(String user) {
        return users.add(new User(user));
    }

    public void deleteUser(String user) {
        users.stream().filter(e -> e.getName().equalsIgnoreCase(user)).forEach(e -> users.remove(e));
    }

    public void updateUser(String user) {
        users.stream().filter(e -> e.getName().equalsIgnoreCase(user)).findFirst().ifPresent(e -> e.setName(user));
    }
}
