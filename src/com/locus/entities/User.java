package com.locus.entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
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

    public void removeRoles(String roleId) {
        userRoles.removeIf(role -> role.getId().equalsIgnoreCase(roleId));
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "{\"User\":{"
                + "\"id\":\"" + id + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"userRoles\":" + userRoles
                + "}}";
    }
}
