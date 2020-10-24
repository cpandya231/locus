package com.locus.repository;

import com.locus.entities.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResourceRepository {
    private List<Resource> roleList = new ArrayList<>();

    public void addResource(Resource resource){
        roleList.add(resource);
    }

    public Optional<Resource> findByResourceName(String resourceName) {
        return roleList.stream().filter(resource -> resource.getName().equalsIgnoreCase(resourceName)).findFirst();
    }
}
