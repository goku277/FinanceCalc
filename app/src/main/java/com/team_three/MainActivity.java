package com.team_three;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splash= (ImageView) findViewById(R.id.splash_id);

     //   Animation rotate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
     //   splash.setAnimation(rotate);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, UserDashboard.class));
              //  finish();
                finishAffinity();
            }
        }, 2000);


    }
}
