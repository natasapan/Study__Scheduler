package com.project.study__scheduler;



/**
 * Created by natas on 4/19/2018.
 */

public class Task{
private String task;
private int id;
private String subject;
private String date;
private boolean isChecked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    Task(String task){
        this.task = task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }
}
