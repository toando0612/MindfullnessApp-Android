package com.example.toanapple.s3652979_android_a1.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.toanapple.s3652979_android_a1.entity.Clock;

import java.util.List;

@Dao
public interface ClockDao {

    @Insert
    void saveClock(Clock clock);

    @Query("SELECT * FROM clock ")
    LiveData<List<Clock>> getAllClock();

}
