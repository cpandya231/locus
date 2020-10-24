package com.locus.entities;

import java.util.HashSet;
import java.util.Set;

public class Resource {
    private String id;
    private String name;

    private Set<ActionType> actionTypes= new HashSet<>();

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{\"Resource\":{"
                + "\"id\":\"" + id + "\""
                + ", \"name\":\"" + name + "\""
                + ", \"actionTypes\":" + actionTypes
                + "}}";
    }
}
