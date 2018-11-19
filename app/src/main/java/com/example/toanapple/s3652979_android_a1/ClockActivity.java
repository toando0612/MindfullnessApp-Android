package com.example.toanapple.s3652979_android_a1;

import android.arch.lifecycle.Observer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.toanapple.s3652979_android_a1.entity.Clock;
import com.example.toanapple.s3652979_android_a1.repository.ClockRepository;

import java.util.Calendar;
import java.util.List;

public class ClockActivity extends AppCompatActivity {
//    MyDBTools myDBTools;
    TextView tvclock, start, stop, daytime, weektime, monthtime;
    Handler handler;
    long mlsecond, starttime, dtimebuff, mtimebuff, wtimebuff, dupdatetime, mupdatetime, wupdatetime = 0L;
    int hours, minutes, seconds, miliseconds, dhours, dminutes, dseconds, whours, wminutes, wseconds, mhours, mminutes, mseconds, timer;
    int day,week,month;
    int daycheck, weekcheck, monthcheck;
    ClockRepository clockRepository;

    @Override
    protected void onResume() {
        super.onResume();
        readAndUpdateAndShow();  //read from database and show time in the Day,Week,Month Recording
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        handler = new Handler();
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        tvclock = findViewById(R.id.tvclock);
        stop.setEnabled(false);

        daytime = findViewById(R.id.daytime);
        weektime = findViewById(R.id.weektime);
        monthtime = findViewById(R.id.monthtime);

        clockRepository = new ClockRepository(getApplicationContext());

        readAndUpdateAndShow();  //read from database and show time in the Day,Week,Month Recording
    }

    public void onStartClick(View view) {                   //Click Start Button
        starttime = SystemClock.uptimeMillis(); //Start time by miliseconds
        handler.postDelayed(runnable,0);
        start.setEnabled(false);                    //cannot press start
        stop.setEnabled(true);                      //press stop is possible


    }       //

    public void post(){
//        ClockRepository clockRepository = new ClockRepository(getApplicationContext());

        Calendar currentCalendar = Calendar.getInstance();
        day = currentCalendar.get(Calendar.DAY_OF_YEAR);
        week = currentCalendar.get(Calendar.WEEK_OF_YEAR);      //get current day,week,month
        month = currentCalendar.get(Calendar.MONTH);
        dtimebuff = dupdatetime;
        wtimebuff = wupdatetime;
        mtimebuff = mupdatetime;

        Clock clock = new Clock(day,week,month, dtimebuff, wtimebuff, mtimebuff);
        clockRepository.saveClock(clock);
    }


    public void onStopClick(View view) {                //Click Stop Button

        post();

        handler.removeCallbacks(runnable);
        mlsecond = 0L ;
        starttime = 0L ;
//        updatetime = 0L ;
        seconds = 0 ;
        minutes = 0 ;
        hours = 0 ;
        miliseconds = 0 ;

        tvclock.setText("00:00:00:00");
        start.setEnabled(true);
        stop.setEnabled(false);
    }


    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mlsecond = SystemClock.uptimeMillis() - starttime;
            dupdatetime = dtimebuff + mlsecond;
            wupdatetime = wtimebuff + mlsecond;
            mupdatetime = mtimebuff + mlsecond;


            seconds = (int) (mlsecond  / 1000);
            hours = minutes / 60;
            minutes = minutes % 60;
            minutes = seconds / 60;
            seconds = seconds % 60;
            miliseconds = (int) (mlsecond % 1000);
            tvclock.setText(hours + ":"
                    + minutes + ":"
                    + String.format("%02d", seconds) + ":"
                    + String.format("%03d", miliseconds)
            );
            //
            dseconds = (int) (dupdatetime / 1000);
            dhours = dminutes / 60;
            dminutes = dminutes % 60;
            dminutes = dseconds / 60;
            dseconds = dseconds % 60;
            daytime.setText(dhours + ":"
                    + dminutes + ":"
                    + String.format("%02d", dseconds)
            );
            //
            wseconds = (int) (wupdatetime / 1000);
            whours = wminutes / 60;
            wminutes = wminutes % 60;
            wminutes = wseconds / 60;
            wseconds = wseconds % 60;
            weektime.setText(whours + ":"
                    + wminutes + ":"
                    + String.format("%02d", wseconds)
            );
            //
            mseconds = (int) (mupdatetime / 1000);
            mhours = mminutes / 60;
            mminutes = mminutes % 60;
            mminutes = mseconds / 60;
            mseconds = mseconds % 60;
            monthtime.setText(mhours + ":"
                    + mminutes + ":"
                    + String.format("%02d", mseconds)
            );

            handler.postDelayed(this, 0);


        }
    };

    public void  readAndUpdateAndShow(){
        ClockRepository clockRepository = new ClockRepository(getApplicationContext());
        clockRepository.getAllClock().observe(this, new Observer<List<Clock>>() {
            @Override
            public void onChanged(@Nullable List<Clock> clocks) {
                for (Clock clock : clocks
                        ) {
                    daycheck = clock.getDay();
                    weekcheck = clock.getWeek();
                    monthcheck = clock.getMonth();
                    dtimebuff = clock.getDaytime();
                    wtimebuff = clock.getWeektime();
                    mtimebuff = clock.getMonthtime();
                }

                Calendar currentCalendar = Calendar.getInstance();
                day = currentCalendar.get(Calendar.DAY_OF_YEAR);
                week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
                month = currentCalendar.get(Calendar.MONTH);
                if (month != monthcheck){
                    dtimebuff = wtimebuff = mtimebuff = 0L;
                }else if (week != weekcheck){
                    dtimebuff = wtimebuff = 0L;
                }
                else if (day != daycheck){
                    dtimebuff = 0L;
                }

                dseconds = (int) (dtimebuff / 1000);
                dminutes = dseconds / 60;
                dhours = dminutes / 60;
                dseconds = dseconds % 60;
                dminutes = dminutes % 60;
                daytime.setText(dhours + ":"
                        + dminutes + ":"
                        + String.format("%02d", dseconds)
                );
                //
                wseconds = (int) (wtimebuff / 1000);
                whours = wminutes / 60;
                wminutes = wminutes % 60;
                wminutes = wseconds / 60;
                wseconds = wseconds % 60;
                weektime.setText(whours + ":"
                        + wminutes + ":"
                        + String.format("%02d", wseconds)
                );
                //
                mseconds = (int) (mtimebuff / 1000);
                mhours = mminutes / 60;
                mminutes = mminutes % 60;
                mminutes = mseconds / 60;
                mseconds = mseconds % 60;
                monthtime.setText(mhours + ":"
                        + mminutes + ":"
                        + String.format("%02d", mseconds)
                );
            }
        });



    }
}
