package com.locus.repository;

import com.locus.entities.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleRepository {
    private List<Role> roleList = new ArrayList<>();


    public void addRole(Role role) {
        Optional<Role> roleOptional = findByName(role.getName());
        if (roleOptional.isPresent()) {
            System.out.printf("Role %s is already added, roleId: %s", role.getName(), roleOptional.get().getId());
        } else {
            roleList.add(role);
            System.out.printf("Role %s added successfully , roleId %s", role.getName(), role.getId());
        }

    }

    public List<Role> getRoles() {
        return roleList;
    }

    public Optional<Role> findById(String roleId) {
        return roleList.stream().filter(role -> role.getId().equalsIgnoreCase(roleId)).findFirst();
    }

    public Optional<Role> findByName(String roleName) {
        return roleList.stream().filter(role -> role.getName().equalsIgnoreCase(roleName)).findFirst();
    }
}
