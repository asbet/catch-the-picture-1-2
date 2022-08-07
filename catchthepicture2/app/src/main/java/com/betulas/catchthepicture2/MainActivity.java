package com.betulas.catchthepicture2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timetext;
    TextView scoretext;
    int score;
    int remaining_time;
    ImageView img2; ImageView img3; ImageView img4; ImageView img5;
    ImageView img6; ImageView img7; ImageView img8; ImageView img9; ImageView img10;
    ImageView[] imageArray;
    Button startButton;
    Handler handler;
    Runnable runnable;
    Random rd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timetext=findViewById(R.id.textView);
        scoretext=findViewById(R.id.textView2);
        startButton=findViewById(R.id.button);
        img2=findViewById(R.id.imageView2);
        img3=findViewById(R.id.imageView3);
        img4=findViewById(R.id.imageView4);
        img5=findViewById(R.id.imageView5);
        img6=findViewById(R.id.imageView6);
        img7=findViewById(R.id.imageView7);
        img8=findViewById(R.id.imageView8);
        img9=findViewById(R.id.imageView9);
        img10=findViewById(R.id.imageView10);
        imageArray= new ImageView[] {img2,img3,img4,img5,img6,img7,img8,img9,img10};
        score=0;
        remaining_time=10;
        rd=new Random();


        //hideImages methodunu çağırdık
        hideImages();
    }
    public void increaseScore(View view){
        score++;
        scoretext.setText("Score "+score);
    }
    public void onClick(View view){
        score=0;
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                startButton.setEnabled(false);
                timetext.setText("Time: "+l/1000);
            }

            @Override
            public void onFinish() {
                for (ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                startButton.setEnabled(true);
                scoretext.setText("score:"+score);

                AlertDialog.Builder alert= new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Do you wanna play again?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                             Intent intent=getIntent();
                             finish();
                             startActivity(intent);
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                });
                alert.show();

            }
        }
        .start();

    }
    //hideImages methodu oluşturduk
    public void hideImages(){
        handler=    new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for (ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                int i =rd.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                // this=bir üst methoda işaret eder. this yerine runnable da yazabiliriz.
                handler.postDelayed(this,900);

            }
        };
        handler.post(runnable);

    }

}