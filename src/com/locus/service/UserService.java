package com.locus.service;

import com.locus.entities.User;
import com.locus.factory.RepositoryFactory;
import com.locus.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class UserService {
    UserRepository userRepository = RepositoryFactory.getUserRepository();
    Scanner scanner = new Scanner(System.in);

    public void addUser() {

        System.out.println("Enter user's name:");
        String name = scanner.next();
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(name);
        userRepository.addUser(user);
        System.out.printf("User %s added successfully, User id %s", name, user.getId());


    }

    public void getUsers() {
        System.out.println("Enter user's name:");
        String name = scanner.next();
        List<User> userList = userRepository.findByName(name);
        if(null!=userList&& userList.size()>0){
            userList.forEach(System.out::println);
        }else {
            System.out.println("No user found");
        }

    }

    public Optional<User> findById(String userId) {
        return userRepository.findById(userId);
    }

    public void removeUserRoles(User user, String roleId) {
        userRepository.removeUserRoles(user, roleId);
    }
}
