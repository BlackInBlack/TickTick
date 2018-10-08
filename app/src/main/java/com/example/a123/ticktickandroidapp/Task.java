package com.example.a123.ticktickandroidapp;

import java.util.Date;

public class Task {
    private String taskName;
    private String taskDescription;
    private boolean isComplete;
    private Date taskDate;
    private byte taskPriority;

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public byte getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(byte taskPriority) {
        this.taskPriority = taskPriority;
    }
}
