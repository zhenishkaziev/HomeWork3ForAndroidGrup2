package com.example.homework3forandroidgrup2;

public class TaskMOdel {

    public TaskMOdel(String text, String description) {
        this.title = text;
        this.description = description;
    }

    String title, description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
