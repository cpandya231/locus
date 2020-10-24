package com.locus.entities;

import java.util.ArrayList;
import java.util.List;

public class Role {
    private String name;
    private String id;
    private List<ActionType> actionTypes = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ActionType> getActionTypes() {
        return actionTypes;
    }

    public void setActionTypes(List<ActionType> actionTypes) {
        this.actionTypes = actionTypes;
    }

    public void addActionType(ActionType actionType) {
        actionTypes.add(actionType);
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "{\"Role\":{"
                + "\"name\":\"" + name + "\""
                + ", \"actionTypes\":" + actionTypes
                + ", \"id\":\"" + id + "\""
                + "}}";
    }
}
