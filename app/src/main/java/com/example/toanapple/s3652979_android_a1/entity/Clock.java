package com.example.toanapple.s3652979_android_a1.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Clock {

    @PrimaryKey(autoGenerate = true)
    private int id;


    private int day;
    private int week;
    private int month;
    private long daytime;
    private long weektime;
    private long monthtime;


    public Clock() {
    }

    public Clock(int day, int week, int month, long daytime, long weektime, long monthtime) {
        this.day = day;
        this.week = week;
        this.month = month;
        this.daytime = daytime;
        this.weektime = weektime;
        this.monthtime = monthtime;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public long getDaytime() {
        return daytime;
    }

    public void setDaytime(long daytime) {
        this.daytime = daytime;
    }

    public long getWeektime() {
        return weektime;
    }

    public void setWeektime(long weektime) {
        this.weektime = weektime;
    }

    public long getMonthtime() {
        return monthtime;
    }

    public void setMonthtime(long monthtime) {
        this.monthtime = monthtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
