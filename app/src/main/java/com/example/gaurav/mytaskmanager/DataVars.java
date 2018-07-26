package com.example.gaurav.mytaskmanager;

public class DataVars {
    int id;
    private  String taskName,date,time;

    public static final String TABLE_NAME = "tasks";

    public static final String COLOUMN_ID = "id";
    public static final String COLOUMN_TASK = "task";
    public static final String COLOUMN_DATE = "date";
    public static final String COLOUMN_TIME = "time";

    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+COLOUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT"
            +","+COLOUMN_TASK+" TEXT"+", "+COLOUMN_DATE+" TEXT"+", "+COLOUMN_TIME+" TEXT"+")";


    public  DataVars(){

    }

    public DataVars(int id, String taskName, String date, String time) {
        this.id = id;
        this.taskName = taskName;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
