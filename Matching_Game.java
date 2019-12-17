package com.arashad96.androiddeveloperintermidatekit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Random;

public class Matching_Game extends AppCompatActivity {
    Button github;
    Button info;
    public ImageButton btn1;
    public ImageButton btn2;
    public ImageButton btn3;
    public ImageButton btn4;
    public ImageButton btn5;
    public ImageButton btn6;
    public ImageButton btn7;
    public ImageButton btn8;
    public int random1;
    public int random2;
    public int random3;
    public int random4;
    public String[] images = {"cat", "dog", "horse", "duck"};
    public String x;
    public int[] numbers = new int[8];
    public int raqm;
    public int randButtons[];
    public int buttonsStatus[];
    public int pair[];
    public ImageButton[] masterbuttons ;
    public boolean finish = false;
    public long start_time;
    public MediaPlayer mp;
    String name;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_matching__game);

        Bundle x = getIntent().getExtras();
        if(x!= null) {
            name=x.getString("name");
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        start_time = System.currentTimeMillis();
        //random selection of images
        Random rand = new Random();
        random1 = 0;
        random2 = 1;
        random3 = 2;
        random4 = 3;
        buttonsStatus = new int[]{0,0,0,0,0,0,0,0};
        randButtons = new int[]{random1,random1,random2,random2,random3,random3,random4,random4};
        pair = new int[]{-1,-1};

        //fill random values from 0 to 3 in the array
        for(int i=0; i<numbers.length;i++){
            raqm =rand.nextInt(4)+0;
            numbers[i] = raqm;
        }

        //check no more than to 2 numbers are same
        for (int i=0; i<numbers.length;i++){

        }

        //test output
        for (int i =0;i<numbers.length;i++){
            Log.d("check",numbers[i]+" testing aho");
        }
        //shuffle the array
        shuffleArray(randButtons);

        //another test
        for (int i=0;i<randButtons.length;i++) {
            Log.d("checker", randButtons[i] + " test rand");
        }

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGameMaster(0);
            }
        });

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGameMaster(1);
            }
        });

        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGameMaster(2);
            }
        });

        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGameMaster(3);
            }
        });

        btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGameMaster(4);
            }
        });

        btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGameMaster(5);
            }
        });

        btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGameMaster(6);
            }
        });

        btn8 = findViewById(R.id.btn8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGameMaster(7);
            }
        });

        masterbuttons = new ImageButton[]{btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8};
        gameMaster();

        //LocalBroadcastManager.getInstance(game.this).sendBroadcast(i);
        github = findViewById(R.id.github);
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ARashad96/Matching_Game"));
                startActivity(intent);
            }
        });
        info = findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new android.app.AlertDialog.Builder(Matching_Game.this)
                        .setIcon(R.drawable.profile)
                        .setTitle("App info")
                        .setMessage("This app is a simple matching game of images using toast, imagebuttons, mediaplayer, audiomanager and linearlayout.")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }
        });
    }

    public void gameMaster(){
        int completed = 0;
        for(int i = 0; i<8; i++){
            //check if the user pressed then open the image without any conditions
            if(buttonsStatus[i]==0){
                masterbuttons[i].setImageResource(R.drawable.question_mark);}
            else{

                completed++;
                x = images[randButtons[i]];
                int tmp = image_setter(x);
                masterbuttons[i].setImageResource(tmp);
            }
        }

        if(completed >= 6 ){
            //return to the screen and show score
            //startActivity(new Intent(Matching_Game.this, MainActivity.class));

            finish = true;
            long finishstopwatch = System.currentTimeMillis()/1000- start_time/1000 ;
            score = (int)finishstopwatch;
            Toast.makeText(getApplicationContext(),"Congrats you won in "+finishstopwatch+" Sec",Toast.LENGTH_SHORT).show();
            /*Intent i = new Intent(Matching_Game.this, history.class);
            i.putExtra("name",name);
            i.putExtra("score",score);
            startActivity(i);*/
        }
        if(pair[0]!= -1) {

            x = images[randButtons[pair[0]]];
            int tmp = image_setter(x);
            masterbuttons[pair[0]].setImageResource(tmp);
        }


        if (pair[1] != -1) {
            x = images[randButtons[pair[1]]];
            int tmp = image_setter(x);
            masterbuttons[pair[1]].setImageResource(tmp);

        }

        comparever2();
    }

    public void setGameMaster(int bNumber) {

        play(randButtons[bNumber]);

        if (pair[0] == -1) {
            pair[0] = bNumber;
        }
        if (pair[0] == bNumber) {
            Toast.makeText(getApplicationContext(), "Choose another image", Toast.LENGTH_SHORT).show();
        }
        else if(pair[1] == -1) {
            pair[1] = bNumber;
        }

        //buttonsStatus[bNumber] = 1;
        gameMaster();
    }

    public void shuffleArray(int[] ar)
    {
        Random rnd = new Random();
        for (int i = 8 - 1; i > -1; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    //play sounds
    public void play(int animal){
        switch (animal) {
            case 0:
                mp = MediaPlayer.create(getBaseContext(), R.raw.cat);
                mp.start();
                break;
            case 1:
                mp = MediaPlayer.create(getBaseContext(), R.raw.dog);
                mp.start();
                break;
            case 2:
                mp = MediaPlayer.create(getBaseContext(), R.raw.horse);
                mp.start();
                break;
            case 3:
                mp = MediaPlayer.create(getBaseContext(), R.raw.duck);
                mp.start();
                break;
            default:
        }
    }

    //set the image
    public int image_setter(String img) {
        switch (img) {
            case "cat":
                return R.drawable.cat;
            case "dog":
                return R.drawable.dog;
            case "horse":
                return R.drawable.horse;
            case "duck":
                return R.drawable.duck;
            default:
                return R.drawable.question_mark;
        }
    }

    public void comparever2(){
        int x=pair[0];
        int y=pair[1];
        if (pair[0]!=-1 && pair[1]!=-1) {
            if (randButtons[x] == randButtons[y]) {
                buttonsStatus[x] = 1;
                buttonsStatus[y] = 1;
            } else {
                Toast.makeText(getApplicationContext(), "wrong match", Toast.LENGTH_SHORT).show();
            }
            pair[0]=-1;
            pair[1]=-1;
        }
    }
    /////////////////////////////
}