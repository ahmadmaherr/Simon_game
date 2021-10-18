package com.example.simon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String SHARED_PREFS_NAME = "MY_SHARED_PREF";
    private static final String SHARED_PREFS_NAME1 = "MY_SHARED_PREF1";

    public static ArrayList<String> playerss;
    public static ArrayList<String> scoreList;
    Button leaderboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        getArray1();
        getArray2();

        playerName = (TextView) findViewById(R.id.playerName);
        score = (ListView) findViewById(R.id.score);

        layout = (ConstraintLayout) findViewById(R.id.root);

        startt = (Button) findViewById(R.id.start);
        red = (Button) findViewById(R.id.red);
        yellow = (Button) findViewById(R.id.yellow);
        green = (Button) findViewById(R.id.green);
        blue = (Button) findViewById(R.id.blue);

        changePlayer = (Button) findViewById(R.id.chnagePlayer);

        highscorre = (ImageView) findViewById(R.id.highscore);

        wait(200);

        leaderboard = (Button) findViewById(R.id.goToLeaderboard);

        leaderboard.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                goToLeaderboard();
            }
        });

        red.setOnClickListener(this);
        green.setOnClickListener(this);
        blue.setOnClickListener(this);
        yellow.setOnClickListener(this);


    }


    public void goToLeaderboard() {
        Intent intent = new Intent(this, leaderboarddd.class);
        startActivity(intent);
    }

    private static MediaPlayer mp;
    public void playSound(String sound){
        if (mp != null && mp.isPlaying()) {
            mp.stop();
            mp.reset();
        }
        mp = MediaPlayer.create(this, getResources().getIdentifier(sound, "raw", getPackageName()));
        mp.start();
    }


    public static int highscore;

    ListView score;
    TextView playerName;
    ArrayList<String> randomColors = new ArrayList<String>();
    ArrayList<String> userClicked = new ArrayList<String>();
    String colors[] ={"red", "blue", "green", "yellow"};
    int Level = 0;
    ConstraintLayout layout;
    Button red;
    Button yellow;
    Button green;
    Button blue;
    Button startt;
    Button changePlayer;
    ImageView highscorre;


 /*  public class players {
       String name;
       int score;

       public players(String pname, int pscore){
           name = pname;
           score = pscore;
       }

   }*/



    public void start(View view){

        if(playerName.getText().toString().trim().length() <= 0){
            Toast.makeText(this, "Enter Player Name", Toast.LENGTH_SHORT).show();
        }
        else {
            ImageView logo = (ImageView) findViewById(R.id.logo);
            logo.animate().translationYBy(2500).setDuration(1000);
            wait(950);

            ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.root);
            layout.setBackgroundColor(Color.rgb(2, 153, 213));


            for (int i = 0; i < scoreList.size(); i++) {
                if (Integer.parseInt(scoreList.get(i)) > highscore) {
                    highscore = Integer.parseInt(scoreList.get(i));
                }
            }

            leaderboard.setVisibility(View.GONE);
            highscorre.setVisibility(View.GONE);
            playerName.setVisibility(View.GONE);
            changePlayer.setVisibility(View.GONE);



            Button start = (Button) findViewById(R.id.start);
            start.setVisibility(View.GONE);
            TextView level = (TextView) findViewById(R.id.level);
            TextView level1 = (TextView) findViewById(R.id.level3);
            TextView over = (TextView) findViewById(R.id.gameOver);
            if (level1.getVisibility() == view.VISIBLE) {
                level1.setVisibility(View.GONE);

            }
            if (over.getVisibility() == view.VISIBLE) {
                over.setVisibility(View.GONE);

            }
            if (level.getVisibility() == view.GONE) {
                level.setVisibility(View.VISIBLE);

            }

            Level += 1;
            level.setText("Level " + Level);

            Button red = (Button) findViewById(R.id.red);
            Button yellow = (Button) findViewById(R.id.yellow);
            Button green = (Button) findViewById(R.id.green);
            Button blue = (Button) findViewById(R.id.blue);
            if (red.getVisibility() == View.GONE) {
                red.setVisibility(View.VISIBLE);
                blue.setVisibility(View.VISIBLE);
                yellow.setVisibility(View.VISIBLE);
                green.setVisibility(View.VISIBLE);
            }

            /*logo.setVisibility(View.GONE);*/
            createSequence();
        }
    }

    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

  public void createSequence() {
        userClicked.clear();
        double randomNum = Math.floor(Math.random()*4);
        int num = (int) randomNum;
        randomColors.add(colors[num]);
        String color = colors[num];
        playSound(color);
        Button button = (Button) findViewById(getResources().getIdentifier(color, "id", getPackageName()));
        animate(button);
    }

    public void changePlayer(View view){
        changePlayer.setVisibility(View.GONE);
        playerName.setVisibility(View.VISIBLE);

    }

    public void animate(Button button){
    AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
    alphaAnimation.setDuration(250);
    alphaAnimation.setRepeatCount(1);
    alphaAnimation.setRepeatMode(Animation.REVERSE);
    button.startAnimation(alphaAnimation);
    }

/*
public int playerIndex(){
    int index = -1;
    String name = playerName.getText().toString();

    int playersSize = playerss.size();
    for (int i = 0; i < playersSize; i++) {
        if (playerss.get(i) == name) {
            index = i;
            Log.i("indexxxx:", playerss.get(i));
            i = playersSize;
            return index;
        }
    }
    return index;
*/


/*public void leaderboardProcess() {
    *//*if (playerss.isEmpty()) {*//*
        playerss.add(playerName.getText().toString());
        scoreList.add(String.valueOf(Level));
    *//*}
    if(playerIndex() == -1){
        playerss.add(playerName.getText().toString());
        scoreList.add(String.valueOf(Level));
    }
    else{
        playerss.set(playerIndex(),playerName.getText().toString());
        if(Integer.parseInt(scoreList.get(playerIndex())) < Level){
        scoreList.set(playerIndex(), String.valueOf(Level));
        }

    }*//*
    }*/





    public void checkInputs(int x) {
        TextView level = (TextView) findViewById(R.id.level);
        TextView level1 = (TextView) findViewById(R.id.level3);
        TextView over = (TextView) findViewById(R.id.gameOver);
        if (userClicked.get(x-1) == randomColors.get(x-1)) {
            if (userClicked.size() == randomColors.size()) {
                Level++;
                level.setText("Level " + Level);
                wait(1000);
                createSequence();
            }
        }
            else
            {

                playerss.add(playerName.getText().toString());


                level.setVisibility(View.GONE);
                over.setVisibility(View.VISIBLE);

                if(Level <= 3){
                level1.setText("you lost at level " + Level +", you're such a loser");
                }
                if(Level>3 && Level<7){
                level1.setText("you lost at level " + Level +", is that it? Come on!");
                }
                if(Level>=7 && Level<10){
                    level1.setText("you reached level " + Level +", you have a good memory");
                }
                if(Level>=10 && Level<15){
                    level1.setText("you reached level " + Level +", WOW YOUR MEMORY IS AMAZING!");
                }
                if(Level>=15){
                    level1.setText("you reached level " + Level +", YOU'RE EXCEPTIONAL CONGRATULATIONS!");
                }
                changePlayer.setVisibility(View.VISIBLE);

                    level1.setVisibility(View.VISIBLE);




                if(Level > highscore){
                    highscore = Level;
                    layout.setBackgroundColor(Color.rgb(0, 255, 51));
                    highscorre.setVisibility(View.VISIBLE);
                    playSound("wsound");
                }

                scoreList.add(String.valueOf(Level));

                 if(highscorre.getVisibility() == View.GONE) {
                     layout.setBackgroundColor(Color.RED);
                     playSound("wrong");
                 }



                Button startt = (Button) findViewById(R.id.start);

                startt.setText(getResources().getString(R.string.play_again));

                startt.setVisibility(View.VISIBLE);

                red.setVisibility(View.GONE);
                blue.setVisibility(View.GONE);
                yellow.setVisibility(View.GONE);
                green.setVisibility(View.GONE);

                leaderboard.setVisibility(View.VISIBLE);
                leaderboard.setBackgroundColor(Color.rgb(0, 147, 0));
                randomColors.clear();
                Level = 0;

                /*leaderboarddd.setAdapters();*/

                saveArray1();
                saveArray2();

            }


    }





    public void saveArray2() {
        SharedPreferences sp1 = getSharedPreferences(SHARED_PREFS_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor mEdit1 = sp1.edit();
        Gson gson = new Gson();
        String json = gson.toJson(playerss);
        mEdit1.putString("players list", json);
        mEdit1.apply();
    }
    public void saveArray1() {
        SharedPreferences sp2 = getSharedPreferences(SHARED_PREFS_NAME1, Activity.MODE_PRIVATE);
        SharedPreferences.Editor mEdit2 = sp2.edit();
        Gson gson2 =new Gson();
        String json2 = gson2.toJson(scoreList);
        mEdit2.putString("score list", json2);
        mEdit2.apply();
    }

    public void getArray1() {
        SharedPreferences sp1 = getSharedPreferences(SHARED_PREFS_NAME, Activity.MODE_PRIVATE);

        Gson gson = new Gson();

        String json = sp1.getString("players list", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        playerss = gson.fromJson(json, type);
        if(playerss == null){
            playerss = new ArrayList<>();
        }
    }

    public void getArray2() {
        SharedPreferences sp2 = getSharedPreferences(SHARED_PREFS_NAME1, Activity.MODE_PRIVATE);

        Gson gson2 = new Gson();

        String json2 = sp2.getString("score list", null);
        Type type2 = new TypeToken<ArrayList<String>>() {}.getType();
        scoreList = gson2.fromJson(json2, type2);
        if(scoreList == null) {
            scoreList = new ArrayList<>();
        }}
    /*public void onStop() {
        saveArray1();
        saveArray2();
        super.onStop();
    }
*/


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.red:
                userClicked.add("red");
                playSound("red");
                break;
            case R.id.blue:
                userClicked.add("blue");
                playSound("blue");
                break;
            case R.id.yellow:
                userClicked.add("yellow");
                playSound("yellow");
                break;
            case R.id.green:
                userClicked.add("green");
                playSound("green");}
        checkInputs(userClicked.size());

    }

}


