package com.locus.entities;

import java.util.HashSet;
import java.util.Set;

public class Resource {
    private String name;

    private Set<ActionType> actionTypes= new HashSet<>();

    public Resource(String resourceName) {
        name=resourceName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ActionType> getActionTypes() {
        return actionTypes;
    }

    public void setActionTypes(Set<ActionType> actionTypes) {
        this.actionTypes = actionTypes;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "name='" + name + '\'' +
                ", actionTypes=" + actionTypes +
                '}';
    }
}
