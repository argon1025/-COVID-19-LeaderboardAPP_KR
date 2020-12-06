package com.argon1025.coronaviewer;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private ImageView title;
    private TextView TotalCase;
    private TextView TotalRecovered;
    private TextView TotalDeath;
    private TextView NowCase;
    private TextView updateTime;
    private TextView TodayRecovered;
    private TextView TodayDeath;
    private TextView TotalCaseBefore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (ImageView) findViewById(R.id.imageView); //타이틀 이미지
        Glide.with(this).load(R.drawable.loadingview).into(title); //타이틀 이미지 재생

        TotalCase = (TextView) findViewById(R.id.TotalCase);
        TotalRecovered = (TextView) findViewById(R.id.TotalRecovered);
        TotalDeath = (TextView) findViewById(R.id.TotalDeath);
        NowCase = (TextView) findViewById(R.id.NowCase);
        updateTime = (TextView) findViewById(R.id.updateTime);
        TodayRecovered = (TextView) findViewById(R.id.TodayRecovered);
        TodayDeath = (TextView) findViewById(R.id.TodayDeath);
        TotalCaseBefore = (TextView) findViewById(R.id.TotalCaseBefore);



        final CoronaAPI Coronalive = new CoronaAPI();

        new Thread(new Runnable() {
            public void run() {
                Coronalive.LoadingToData();

                runOnUiThread(new Runnable() { //비동기 처리 후 메인 스레드에서의 작업
                    @Override
                    public void run() {
                        new Handler().postDelayed(new Runnable() {// 0.5 초 후에 실행
                            @Override
                            public void run() {
                                // 실행할 동작 코딩
                                TotalCase.setText(Coronalive.getTotalCase());
                                TotalRecovered.setText(Coronalive.getTotalRecovered());
                                TotalDeath.setText(Coronalive.getTotalDeath());
                                NowCase.setText(Coronalive.getNowCase());
                                updateTime.setText(Coronalive.getupdateTime());
                                TodayRecovered.setText(Coronalive.getTodayRecovered());
                                TodayDeath.setText(Coronalive.getTodayDeath());
                                TotalCaseBefore.setText(Coronalive.getTotalCaseBefore());
                                title.setImageResource(R.drawable.loadingview1);
                            }
                        }, 5000);

                    }
                });
            }
        }).start();
    }

}