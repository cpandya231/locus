package com.locus.entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<Role> userRoles = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addRoles(Role role) {
        userRoles.add(role);
    }

    public List<Role> getUserRoles() {
        return userRoles;
    }

    public void removeRoles(String roleName) {
        userRoles.removeIf(role -> role.getName().equalsIgnoreCase(roleName));
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userRoles=" + userRoles +
                '}';
    }
}
