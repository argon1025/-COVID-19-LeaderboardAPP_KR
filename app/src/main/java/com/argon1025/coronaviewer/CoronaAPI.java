/*--
    KIU Corona API
    Copyright (C) 2020, Lee seongrok
    argon1025@gmail.com
*/
package com.argon1025.coronaviewer;

import android.util.JsonReader;
import android.util.Log;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CoronaAPI {
    String TotalCase = null; //국내 확진자수
    String TotalRecovered = null; //국내 완치자수
    String TotalDeath = null; //국내 사망자수
    String NowCase = null; //국내 격리자수
    String updateTime = null; // 정보 업데이트 기준
    String TodayRecovered = null; //오늘자 완치자
    String TodayDeath = null; //오늘하루 사망자
    String TotalCaseBefore = null; //전날대비 환자수

    public void LoadingToData(){
        try {
            // 연결 시작
            URL url = new URL("https://api.corona-19.kr/korea/?serviceKey=d462d4d3f6f8b317b0b2c3129172e95b6");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            InputStream is = conn.getInputStream();

            // 스트림 입력
            InputStreamReader responseBodyReader = new InputStreamReader(is, "UTF-8");
            JsonReader jsonReader = new JsonReader(responseBodyReader);

            jsonReader.beginObject(); // JSON object 처리 작업 시작

            while (jsonReader.hasNext()) { // Loop through all keys
                String key = jsonReader.nextName(); // Fetch the next key
                if (key.equals("TotalCase")) { // Check if desired key
                    TotalCase = jsonReader.nextString();
                    Log.e("REST_API_GET_TotalCase", "method: " + TotalCase);
                } else if(key.equals("TotalRecovered")){
                    TotalRecovered = jsonReader.nextString();
                    Log.e("REST_API_GET_TotalRecovered", "method: " + TotalRecovered);
                } else if(key.equals("TotalDeath")){
                    TotalDeath = jsonReader.nextString();
                    Log.e("REST_API_GET_TotalDeath", "method: " + TotalDeath);
                } else if(key.equals("NowCase")){
                    NowCase = jsonReader.nextString();
                    Log.e("REST_API_GET_NowCase", "method: " + NowCase);
                } else if(key.equals("updateTime")){
                    updateTime = jsonReader.nextString();
                    Log.e("REST_API_GET_updateTime", "method: " + updateTime);
                } else if(key.equals("TodayRecovered")){
                    TodayRecovered = jsonReader.nextString();
                    Log.e("REST_API_GET_TodayRecovered", "method: " + TodayRecovered);
                } else if(key.equals("TodayDeath")){
                    TodayDeath = jsonReader.nextString();
                    Log.e("REST_API_GET_TodayDeath", "method: " + TodayDeath);
                } else if(key.equals("TotalCaseBefore")){
                    TotalCaseBefore = jsonReader.nextString();
                    Log.e("REST_API_GET_TotalCaseBefore", "method: " + TotalCaseBefore);
                } else{
                    jsonReader.skipValue(); // Skip values of other keys
                }
            }
        }
        catch (Exception e) {
            // Error calling the rest api
            Log.e("REST_API", "failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //확진자수 리턴
    public String getTotalCase(){
        return TotalCase;
    }

    //완치자수 리턴
    public String getTotalRecovered(){
        return TotalRecovered;
    }

    //사망자수 리턴
    public String getTotalDeath(){ return TotalDeath;}

    //격리자수 리턴
    public String getNowCase(){
        return NowCase;
    }

    //정보 업데이트 기준 리턴
    public String getupdateTime(){
        return updateTime;
    }

    //하루 완치자 리턴
    public String getTodayRecovered(){
        return TodayRecovered;
    }

    //하루 사망자 리턴
    public String getTodayDeath(){
        return TodayDeath;
    }

    //전날대비 환자수 리턴
    public String getTotalCaseBefore(){
        return TotalCaseBefore;
    }

}
