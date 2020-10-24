package com.locus.repository;

import com.locus.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private List<User> userList = new ArrayList<>();

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public Optional<User> findByName(String userName) {
        return userList.stream().filter(user -> user.getName().equalsIgnoreCase(userName)).findFirst();
    }

    public void removeUserRoles(User user, String roleName) {

        userList.removeIf(user1 -> user1.getName().equalsIgnoreCase(user.getName()));
        user.removeRoles(roleName);
        userList.add(user);
    }
}
