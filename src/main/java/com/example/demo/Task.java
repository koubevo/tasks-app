package com.example.demo;

import java.util.Date;

public class Task {

    private String name;
    private String note;
    private String deadlineDate;
    private String deadlineTime;
    private String editedDate;
    private String addedDate;
    private String priority;
    private String taskId;
    private String done;
    private String userId;
    private String categoryId;
    public Task(String name, String note, String editedDate, String priority, String addedDate, String deadlineDate, String deadlineTime, String taskId, String userId, String categoryId, String done) {
        this.name = name;
        this.note = note;
        this.editedDate = editedDate;
        this.addedDate = addedDate;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
        this.priority = priority;
        this.taskId = taskId;
        this.done = done;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(String deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    public String getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(String editedDate) {
        this.editedDate = editedDate;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return getName();
    }
}
