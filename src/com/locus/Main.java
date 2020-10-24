package com.locus;

import com.locus.service.ResourceService;
import com.locus.service.RoleService;
import com.locus.service.UserService;

import java.util.Scanner;


public class Main {


    static Scanner scanner = new Scanner(System.in);
    static UserService userService = new UserService();
    static RoleService roleService = new RoleService();
    static ResourceService resourceService = new ResourceService();

    public static void main(String[] args) {


        while (true) {
            System.out.println("Enter your choice:");
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
                    userService.addUser();
                    break;
                case 2:
                    userService.getUsers();
                    break;
                case 3:
                    roleService.addRole();
                    break;
                case 4:
                    roleService.getRoles();
                    break;
                case 5:
                    roleService.assignRole();
                    break;
                case 6:
                    roleService.removeRole();
                    break;
                case 7:
                    resourceService.createResource();
                    break;
                case 8:
                    resourceService.getResources();
                    break;
                case 9:
                    resourceService.checkIfUserHasPermissionForResource();
                    break;

            }
            System.out.println();
        }
    }


}
