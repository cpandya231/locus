package com.locus.service;

import com.locus.entities.ActionType;
import com.locus.entities.Role;
import com.locus.entities.User;
import com.locus.factory.RepositoryFactory;
import com.locus.repository.RoleRepository;

import java.util.*;

public class RoleService {
    RoleRepository roleRepository = RepositoryFactory.getRoleRepository();
    UserService userService = new UserService();
    Scanner scanner = new Scanner(System.in);

    public void addRole() {
        System.out.println("Enter role name:");
        String roleName = scanner.next();
        Role role = new Role();
        role.setId(UUID.randomUUID().toString());
        role.setName(roleName);
        System.out.println("Enter action types (Separate by comma if more than one):");
        String actionTypes = scanner.next();
        List<ActionType> actionTypeList = new ArrayList<>();
        for (String actionTypeStr : actionTypes.split(",")) {
            ActionType actionType = new ActionType(actionTypeStr.toLowerCase());
            actionTypeList.add(actionType);
        }
        role.setActionTypes(actionTypeList);
        roleRepository.addRole(role);

    }

    public void getRoles() {
        System.out.println(roleRepository.getRoles());
    }


    public void assignRole() {

        System.out.println("Enter role id:");
        String roleId = scanner.next();
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if (roleOptional.isPresent()) {
            System.out.println("Enter user id:");
            String userId = scanner.next();
            Optional<User> userOptional = userService.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.addRoles(roleOptional.get());
                System.out.printf("Role %s assigned to %s", roleOptional.get().getName(), user.getName());
            } else {
                System.out.println("User not found");
            }

        } else {
            System.out.println("Role not found");
        }
    }


    public void removeRole() {
        System.out.println("Enter userId:");
        String userId = scanner.next();
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.println("Enter role id to remove from user:");
            String roleId = scanner.next();

            if (user.getUserRoles().stream().anyMatch(role -> role.getId().equalsIgnoreCase(roleId))) {
                userService.removeUserRoles(user, roleId);
                System.out.printf("Role %s removed from %s", roleId, userId);
            } else {
                System.out.println("Role not found");
            }

        } else {
            System.out.println("User not found");
        }
    }


}
