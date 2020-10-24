package com.locus;

import com.locus.entities.ActionType;
import com.locus.entities.Resource;
import com.locus.entities.Role;
import com.locus.entities.User;
import com.locus.repository.ResourceRepository;
import com.locus.repository.RoleRepository;
import com.locus.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    static Scanner scanner = new Scanner(System.in);
    static UserRepository userRepository = new UserRepository();
    static RoleRepository roleRepository = new RoleRepository();
    static ResourceRepository resourceRepository = new ResourceRepository();

    public static void main(String[] args) {


        while (true) {
            System.out.println(ANSI_RED + "Enter your choice:" + ANSI_RESET);
            System.out.println("Add new user: 1");
            System.out.println("Get user: 2");
            System.out.println("Add new role: 3");
            System.out.println("Get available roles: 4");
            System.out.println("Assign role to user: 5");
            System.out.println("Remove role from user: 6");
            System.out.println("Create Resource: 7");
            System.out.println("Get Resource: 8");
            System.out.println("Check if user has permission to access resource: 9");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    getUsers();
                    break;
                case 3:
                    addRole();
                    break;
                case 4:
                    System.out.println(getRoles());
                    break;
                case 5:
                    assignRole();
                    break;
                case 6:
                    removeRole();
                    break;
                case 7:
                    createResource();
                    break;
                case 8:
                    getResources();
                    break;
                case 9:
                    checkIfUserHasPermissionForResource();
                    break;

            }
            System.out.println();
        }
    }

    private static void addUser() {
        System.out.println("Enter user's name:");
        String name = scanner.next();
        User user = new User();
        user.setName(name);
        userRepository.addUser(user);
        System.out.printf(ANSI_GREEN + "User %s added successfully%n" + ANSI_RESET, name);
    }


    private static void getUsers() {
        System.out.println("Enter user's name:");
        String name = scanner.next();
        Optional<User> userOptional = userRepository.findByName(name);
        userOptional.ifPresentOrElse(System.out::println, () -> System.out.println("User not found"));
    }


    private static void addRole() {
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
        System.out.printf(ANSI_GREEN + "Role %s added successfully%n" + ANSI_RESET, roleName);
    }

    private static List<Role> getRoles() {
        return roleRepository.getRoles();
    }


    private static void assignRole() {
        System.out.println("Enter user name:");
        String userName = scanner.next();
        Optional<User> userOptional = userRepository.findByName(userName);
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


    private static void removeRole() {
        System.out.println("Enter user name:");
        String userName = scanner.next();
        Optional<User> userOptional = userRepository.findByName(userName);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.println("Enter role name to remove:");
            String roleName = scanner.next();

            if (user.getUserRoles().stream().anyMatch(role -> role.getName().equalsIgnoreCase(roleName))) {
                userRepository.removeUserRoles(user, roleName);
                System.out.printf("Role %s removed from %s", roleName, userName);
            } else {
                System.out.println("Role not found");
            }

        } else {
            System.out.println("User not found");
        }
    }

    private static void createResource() {
        System.out.println("Enter resource name: ");
        String resourceName = scanner.next();
        System.out.println("Available action types:");
        List<Role> roles = roleRepository.getRoles();
        List<String> actionTypes =
                roles.stream().flatMap(role -> role.getActionTypes().stream())
                        .map(ActionType::getName)
                        .collect(Collectors.toList());
        System.out.println(actionTypes);
        System.out.println("Enter action type for resource (Separate by comma if more than 1): ");
        String[] selectedActionTypeList = scanner.next().split(",");
        Set<String> actionItemsSelectedSet = Arrays.stream(selectedActionTypeList).collect(Collectors.toSet());

        if (actionTypes.containsAll(actionItemsSelectedSet)) {
            Resource resource = new Resource(resourceName);
            resource.setActionTypes(actionItemsSelectedSet.stream().map(ActionType::new).collect(Collectors.toSet()));
            resourceRepository.addResource(resource);
            System.out.printf("Resource %s added successfully", resourceName);
        } else {
            System.out.println("Action type selected is not associated with any roles, consider creating new role.");
        }

    }


    private static void getResources() {
        System.out.println("Enter resource name: ");
        String resourceName = scanner.next();
    }


    private static void checkIfUserHasPermissionForResource() {
        System.out.println("Enter resource name: ");
        String resourceName = scanner.next();
        Optional<Resource> resourceOptional = resourceRepository.findByResourceName(resourceName);
        if (resourceOptional.isPresent()) {
            Resource resource = resourceOptional.get();
            System.out.println("Enter username: ");
            String userName = scanner.next();
            Optional<User> userOptional = userRepository.findByName(userName);
            if (userOptional.isPresent()) {
                if (isAuthorizedUser(resource, userOptional.get())) {
                    System.out.printf("User %s is authorized to access the resource %s", userName, resourceName);
                } else {
                    System.out.printf("User %s is not authorized to access the resource %s", userName, resourceName);
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
