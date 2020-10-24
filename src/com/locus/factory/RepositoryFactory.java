package com.locus.factory;

import com.locus.repository.ResourceRepository;
import com.locus.repository.RoleRepository;
import com.locus.repository.UserRepository;

public class RepositoryFactory {
    private static UserRepository userRepository;
    private static RoleRepository roleRepository;
    private static ResourceRepository resourceRepository;

    public static UserRepository getUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public static RoleRepository getRoleRepository() {
        if (roleRepository == null) {
            roleRepository = new RoleRepository();
        }
        return roleRepository;
    }

    public static ResourceRepository getResourceRepository() {
        if (resourceRepository == null) {
            resourceRepository = new ResourceRepository();
        }
        return resourceRepository;
    }
}
