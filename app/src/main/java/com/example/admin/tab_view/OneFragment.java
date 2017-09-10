package com.example.admin.tab_view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Admin on 8/28/2017.
 */

@SuppressLint("ValidFragment")
class OneFragment extends Fragment {

    TextView stopwatch_view;
    Button start, pause, reset,close, stopwatch;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;
    Handler handler;
    int Seconds, Minutes, MilliSeconds;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View Stopwatch_view = inflater.inflate(R.layout.fragment1, container, false);

        stopwatch = (Button) Stopwatch_view.findViewById(R.id.stop_btn);

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                MillisecondTime = SystemClock.uptimeMillis() - StartTime;

                UpdateTime = TimeBuff + MillisecondTime;

                Seconds = (int) (UpdateTime / 1000);

                Minutes = Seconds / 60;

                Seconds = Seconds % 60;

                MilliSeconds = (int) (UpdateTime % 1000);

                stopwatch_view.setText("" + Minutes + ":"
                        + String.format("%02d", Seconds) + ":"
                        + String.format("%03d", MilliSeconds));

                handler.postDelayed(this, 0);

            }
        };

        stopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.stopwach_dialog);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                stopwatch_view = (TextView) dialog.findViewById(R.id.stopwatch_view);
                start = (Button)dialog.findViewById(R.id.stop_start);
                pause = (Button)dialog.findViewById(R.id.stop_pause);
                reset = (Button)dialog.findViewById(R.id.stop_reset);
                close=(Button)dialog.findViewById(R.id.stop_close);
                handler = new Handler() ;

                pause.setEnabled(false);
                reset.setEnabled(false);

                start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        StartTime = SystemClock.uptimeMillis();
                        handler.postDelayed(runnable, 0);
                        pause.setEnabled(true);
                        reset.setEnabled(false);
                        start.setEnabled(false);
                    }
                });

                pause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TimeBuff += MillisecondTime;
                        handler.removeCallbacks(runnable);
                        start.setEnabled(true);
                        reset.setEnabled(true);
                    }
                });

                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MillisecondTime = 0L ;
                        StartTime = 0L ;
                        TimeBuff = 0L ;
                        UpdateTime = 0L ;
                        Seconds = 0 ;
                        Minutes = 0 ;
                        MilliSeconds = 0 ;

                        stopwatch_view.setText("00:00:00");

                    }
                });
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.setCancelable(true);
                        dialog.dismiss();
                    }
                });

            }
        });
        return Stopwatch_view;
    }
}
