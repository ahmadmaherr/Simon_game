package com.example.simon;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.os.Bundle;

import android.widget.ListView;

public class leaderboarddd extends AppCompatActivity {

     ArrayAdapter arrayadapterName;
     ArrayAdapter arrayadapterScore;

     ListView leaderboardd;
     ListView score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_leaderboard);

        MainActivity.wait(1000);




        leaderboardd = (ListView) findViewById(R.id.leaderboard);
        score = (ListView) findViewById(R.id.score);
        arrayadapterName = new ArrayAdapter<String>(this, R.layout.mylist, MainActivity.playerss);
        arrayadapterScore = new ArrayAdapter<String>(this, R.layout.mylist, MainActivity.scoreList);
        setAdapters();

    }

    public void setAdapters(){
        leaderboardd.setAdapter(arrayadapterName);
        score.setAdapter(arrayadapterScore);
    }


    public void clear(View view){
        MainActivity.highscore = 0;
        MainActivity.playerss.clear();
        arrayadapterName.notifyDataSetChanged();
        MainActivity.scoreList.clear();
        arrayadapterScore.notifyDataSetChanged();
    }





}