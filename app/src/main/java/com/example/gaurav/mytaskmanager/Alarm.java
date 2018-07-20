package com.example.gaurav.mytaskmanager;

public class Alarm {
    String task;
    String alarmTime;

    public Alarm(String task, String alarmTime) {
        this.task = task;
        this.alarmTime = alarmTime;
    }

    public Alarm() {

    }

    public String getTask() {
        return task;
    }

    public String  getalarmTime() {
        return alarmTime;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setalarmTime(String  alarmTime) {
        this.alarmTime = alarmTime;
    }
}
