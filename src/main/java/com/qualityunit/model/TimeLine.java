package com.qualityunit.model;

import java.time.LocalDate;

public class TimeLine extends RequestLine {
    private LocalDate date;
    private int time;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
