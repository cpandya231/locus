package com.locus.repository;

import com.locus.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Optional<User> findById(String userId) {
        return userList.stream().filter(user -> user.getId().equalsIgnoreCase(userId)).findFirst();
    }
    public List<User> findByName(String userName) {
        return userList.stream().filter(user -> user.getName().equalsIgnoreCase(userName)).collect(Collectors.toList());
    }

    public void removeUserRoles(User user, String roleId) {

        userList.removeIf(userFromStream -> userFromStream.getName().equalsIgnoreCase(user.getName()));
        user.removeRoles(roleId);
        userList.add(user);
    }
}
