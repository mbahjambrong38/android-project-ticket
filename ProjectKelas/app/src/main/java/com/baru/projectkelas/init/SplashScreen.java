package com.baru.projectkelas.init;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.baru.projectkelas.HomeActivity;
import com.baru.projectkelas.MainActivity;
import com.baru.projectkelas.R;
import com.baru.projectkelas.db.Preference;
import com.baru.projectkelas.db.db;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_splash_screen);

        db.initUser(this);
        db.initEvent(this);
        db.initType(this);
        db.initProduct(this);
        db.initOrder(this);
        db.initConfirmation(this);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int idMember = 0;
                Preference.setContext(SplashScreen.this);
                idMember = Preference.getIdMeber();
                if(idMember == 0){
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
                    intent.putExtra("id", idMember);
                    startActivity(intent);
                    finish();
                }

            }
        },2000);
    }
}
