package com.example.demo.backend.ProfileBackend;

public class TimeUsage {
    private String date;
    private double time;

    public TimeUsage(String date, double time)
    {
        this.date = date;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public double getTime() {
        return time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
