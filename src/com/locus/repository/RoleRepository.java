package com.locus.repository;

import com.locus.entities.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleRepository {
    private List<Role> roleList = new ArrayList<>();


    public void addRole(Role role) {
        roleList.add(role);
    }

    public List<Role> getRoles() {
        return roleList;
    }

    public Optional<Role> findByName(String roleName) {
        return roleList.stream().filter(role -> role.getName().equalsIgnoreCase(roleName)).findFirst();
    }
}
