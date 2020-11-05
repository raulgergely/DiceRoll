package com.example.rolldice;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final Random random=new Random();
    private Button rollDices;
    private ImageView imgView1,imgView2;

    public static int randomValue(){
        return random.nextInt(6)+1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rollDices=findViewById(R.id.rollDices);
        final MediaPlayer mp=MediaPlayer.create(MainActivity.this,R.raw.diceroll);
        imgView1=findViewById(R.id.imageView1);
        imgView2=findViewById(R.id.imageView2);

        rollDices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value1=randomValue();
                int value2=randomValue();
                int res1=getResources().getIdentifier("dice_"+value1,"drawable",
                        "com.example.rolldice");
                int res2=getResources().getIdentifier("dice_"+value2,"drawable",
                        "com.example.rolldice");
                imgView1.setImageResource(res1);
                imgView2.setImageResource(res2);
                mp.start();
            }
        });
    }
}