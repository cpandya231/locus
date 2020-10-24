package com.locus.service;

import com.locus.entities.User;
import com.locus.factory.RepositoryFactory;
import com.locus.repository.UserRepository;

import java.util.Optional;
import java.util.Scanner;

import static com.locus.util.Constants.ANSI_GREEN;
import static com.locus.util.Constants.ANSI_RESET;

public class UserService {
    UserRepository userRepository = RepositoryFactory.getUserRepository();
    Scanner scanner = new Scanner(System.in);

    public void addUser() {

        System.out.println("Enter user's name:");
        String name = scanner.next();
        User user = new User();
        user.setName(name);
        userRepository.addUser(user);
        System.out.printf(ANSI_GREEN + "User %s added successfully%n" + ANSI_RESET, name);


    }

    public void getUsers() {
        System.out.println("Enter user's name:");
        String name = scanner.next();
        Optional<User> userOptional = userRepository.findByName(name);
        userOptional.ifPresentOrElse(System.out::println, () -> System.out.println("User not found"));
    }

    public Optional<User> findByName(String userName) {
        return userRepository.findByName(userName);
    }

    public void removeUserRoles(User user, String roleName) {
        userRepository.removeUserRoles(user, roleName);
    }
}
