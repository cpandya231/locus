package com.locus.repository;

import com.locus.entities.Resource;
import com.locus.entities.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResourceRepository {
    private List<Resource> resourceList = new ArrayList<>();

    public void addResource(Resource resource) {
        Optional<Resource> resourceOptional = findByName(resource.getName());
        if (resourceOptional.isPresent()) {
            System.out.printf("Resource %s is already added, resourceId: %s", resource.getName(), resourceOptional.get().getId());
        } else {
            resourceList.add(resource);
            System.out.printf("Resource %s added successfully , resourceId %s", resource.getName(), resource.getId());
        }

    }

    public List<Resource> getResources() {
        return resourceList;
    }

    public Optional<Resource> findById(String resourceId) {
        return resourceList.stream().filter(resource -> resource.getId().equalsIgnoreCase(resourceId)).findFirst();
    }

    public Optional<Resource> findByName(String resourceName) {
        return resourceList.stream().filter(resource -> resource.getName().equalsIgnoreCase(resourceName)).findFirst();
    }
}
