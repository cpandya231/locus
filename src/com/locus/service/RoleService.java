package com.locus.service;

import com.locus.entities.ActionType;
import com.locus.entities.Role;
import com.locus.entities.User;
import com.locus.factory.RepositoryFactory;
import com.locus.repository.RoleRepository;
import com.locus.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RoleService {
    RoleRepository roleRepository = RepositoryFactory.getRoleRepository();
    UserService userService = new UserService();
    Scanner scanner = new Scanner(System.in);

    public void addRole() {
        System.out.println("Enter role name:");
        String roleName = scanner.next();
        Role role = new Role();
        role.setName(roleName);
        System.out.println("Enter action types (Separate by comma if more than one):");
        String actionTypes = scanner.next();
        List<ActionType> actionTypeList = new ArrayList<>();
        for (String actionTypeStr : actionTypes.split(",")) {
            ActionType actionType = new ActionType(actionTypeStr);
            actionTypeList.add(actionType);
        }
        role.setActionTypes(actionTypeList);
        roleRepository.addRole(role);
        System.out.printf(Constants.ANSI_GREEN + "Role %s added successfully%n" + Constants.ANSI_RESET, roleName);
    }

    public void getRoles() {
        System.out.println(roleRepository.getRoles());
    }


    public void assignRole() {
        System.out.println("Enter user name:");
        String userName = scanner.next();
        Optional<User> userOptional = userService.findByName(userName);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.println("Enter role name:");
            String roleName = scanner.next();
            Optional<Role> roleOptional = roleRepository.findByName(roleName);
            if (roleOptional.isPresent()) {
                user.addRoles(roleOptional.get());
                System.out.printf("Role %s assigned to %s", roleName, userName);
            } else {
                System.out.println("Role not found");
            }

        } else {
            System.out.println("User not found");
        }
    }


    public void removeRole() {
        System.out.println("Enter user name:");
        String userName = scanner.next();
        Optional<User> userOptional = userService.findByName(userName);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.println("Enter role name to remove:");
            String roleName = scanner.next();

            if (user.getUserRoles().stream().anyMatch(role -> role.getName().equalsIgnoreCase(roleName))) {
                userService.removeUserRoles(user, roleName);
                System.out.printf("Role %s removed from %s", roleName, userName);
            } else {
                System.out.println("Role not found");
            }

        } else {
            System.out.println("User not found");
        }
    }


}
