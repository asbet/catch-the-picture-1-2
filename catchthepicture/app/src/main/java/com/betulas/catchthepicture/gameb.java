package com.betulas.catchthepicture;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class gameb extends AppCompatActivity {
    TextView textView1;
    TextView textView2;
    int  number;
    int score;
    Runnable runnable;
    Handler handler;
    Random rand;
    Button button2;
    ImageView img;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameb);
        textView1=findViewById(R.id.textView2);
        textView2=findViewById(R.id.textView3);
        button2=findViewById(R.id.button2);
        img=findViewById(R.id.imageView);
        number=10;
       // imageButton.setClickable(false);
    }

    public void clc(View view){
             //score=0;
        score=0;
        handler =new Handler();
        runnable=new Runnable() {

            @Override
            public void run() {
                textView1.setText("remaining time: "+number);
                number--;
                textView1.setText("remaining time: "+number);
                handler.postDelayed(runnable,1000);
                
                DisplayMetrics displaynetrics=new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displaynetrics);
                Timer timer=new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Random R=new Random();
                                float dx=R.nextFloat()*displaynetrics.widthPixels;
                                float dy=R.nextFloat()*displaynetrics.heightPixels;
                                Timer timer =new Timer();
                                img.animate()
                                        .x(dx)
                                        .y(dy)
                                        .setDuration(0)
                                        .start();
                            }
                        });
                    }
                },0,1000);



                if (number==0){
                    textView2.setText("Your Score: "+ score);
                    handler.removeCallbacks(runnable);
                    textView1.setText("Time: "+number);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                    // alert dialog başlığını tanımlıyoruz.
                    alertDialogBuilder.setTitle("Your score is: "+score);

                    // alert dialog özelliklerini oluşturuyoruz.
                    alertDialogBuilder
                            .setMessage("Do you want to play agin??")
                            .setCancelable(false)
                            .setIcon(R.mipmap.ic_launcher_round)
                            // Evet butonuna tıklanınca yapılacak işlemleri buraya yazıyoruz.
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    number=10;
                                    runnable.run();
                                }
                            })
                            // İptal butonuna tıklanınca yapılacak işlemleri buraya yazıyoruz.
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();

                                }
                            });

                    // alerti gösteriyoruz
                    alertDialogBuilder.show();

                }
            }

        };
        handler.post(runnable);

        //imageButton.setClickable(true);
    }

}