package com.example.toanapple.s3652979_android_a1.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.toanapple.s3652979_android_a1.dao.ClockDao;
import com.example.toanapple.s3652979_android_a1.entity.Clock;


@Database(entities = {Clock.class}, version = 1)
public abstract class ClockDatabase extends RoomDatabase {
    public abstract ClockDao clockDao();
}
