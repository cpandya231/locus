package com.locus.service;

import com.locus.entities.ActionType;
import com.locus.entities.Resource;
import com.locus.entities.Role;
import com.locus.entities.User;
import com.locus.factory.RepositoryFactory;
import com.locus.repository.ResourceRepository;
import com.locus.repository.RoleRepository;

import java.util.*;
import java.util.stream.Collectors;

public class ResourceService {
    RoleRepository roleRepository = RepositoryFactory.getRoleRepository();
    ResourceRepository resourceRepository = RepositoryFactory.getResourceRepository();
    UserService userService = new UserService();
    Scanner scanner = new Scanner(System.in);

    public void createResource() {
        System.out.println("Enter resource name: ");
        String resourceName = scanner.next();
        System.out.println("Available action types:");
        List<Role> roles = roleRepository.getRoles();
        Set<String> actionTypes =
                roles.stream().flatMap(role -> role.getActionTypes().stream())
                        .map(ActionType::getName)
                        .collect(Collectors.toSet());
        System.out.println(actionTypes);
        System.out.println("Enter action type for resource (Separate by comma if more than 1): ");
        String[] selectedActionTypeList = scanner.next().split(",");
        Set<String> actionItemsSelectedSet = Arrays.stream(selectedActionTypeList).collect(Collectors.toSet());

        if (actionTypes.containsAll(actionItemsSelectedSet)) {
            Resource resource = new Resource();
            resource.setId(UUID.randomUUID().toString());
            resource.setName(resourceName);
            resource.setActionTypes(actionItemsSelectedSet.stream().map(ActionType::new).collect(Collectors.toSet()));
            resourceRepository.addResource(resource);
        } else {
            System.out.println("Action type selected is not associated with any roles, consider creating new role.");
        }

    }

    public void getResources() {

        System.out.println(resourceRepository.getResources());

    }


    public void checkIfUserHasPermissionForResource() {
        System.out.println("Enter resource id: ");
        String resourceId = scanner.next();
        Optional<Resource> resourceOptional = resourceRepository.findById(resourceId);
        if (resourceOptional.isPresent()) {
            Resource resource = resourceOptional.get();
            System.out.println("Enter userId: ");
            String userId = scanner.next();
            Optional<User> userOptional = userService.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (isAuthorizedUser(resource, user)) {
                    System.out.printf("User %s is authorized to access the resource %s", user.getName(), resource.getName());
                } else {
                    System.out.printf("User %s is not authorized to access the resource %s", user.getName(), resource.getName());
                }
            } else {
                System.out.println("User not found");
            }
        } else {
            System.out.println("Resource not found");
        }


    }

    private static boolean isAuthorizedUser(Resource resource, User user) {

        List<String> actionTypes =
                user.getUserRoles().stream().flatMap(role -> role.getActionTypes().stream())
                        .map(ActionType::getName)
                        .collect(Collectors.toList());

        return resource.getActionTypes().stream().map(ActionType::getName).allMatch(actionTypes::contains);

    }
}
