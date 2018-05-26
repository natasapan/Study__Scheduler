package com.deitel.study__scheduler;

import java.util.ArrayList;

/**
 * Created by natas on 4/19/2018.
 */

public class Subject {
    private String name;
    private long date;
    private ArrayList<String> subject_tasks;

    Subject(String name, long date, ArrayList<String> subject_tasks){

        this.name = name;
        this.date = date;
        this.subject_tasks = subject_tasks;

    }


    public String getName() {
        return name;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getSubject_tasks() {
        return subject_tasks;
    }

    public void setSubject_tasks(ArrayList<String> subject_tasks) {
        this.subject_tasks = subject_tasks;
    }




}
