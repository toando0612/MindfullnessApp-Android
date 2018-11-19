package com.example.toanapple.s3652979_android_a1.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.toanapple.s3652979_android_a1.database.ClockDatabase;
import com.example.toanapple.s3652979_android_a1.entity.Clock;

import java.util.List;

public class ClockRepository {
    private ClockDatabase clockDatabase;

    public ClockRepository(Context context) {
        clockDatabase = Room.databaseBuilder(context, ClockDatabase.class, "clocks").allowMainThreadQueries().build();

    }

    public void saveClock(Clock clock){clockDatabase.clockDao().saveClock(clock);}

    public LiveData<List<Clock>> getAllClock(){
        return clockDatabase.clockDao().getAllClock();
    }
}
