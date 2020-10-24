package com.locus.entities;

import java.util.ArrayList;
import java.util.List;

public class Role {
    private String name;

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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Role: {");
        sb.append("name='").append(name).append('\'');
        sb.append(", actionTypes=").append(actionTypes);
        sb.append("\n}");
        return sb.toString();
    }
}
