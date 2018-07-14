package com.example.pc.projectdrag;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button close;
    Button settings;
    Button play;
    Button score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.play);
        close = (Button) findViewById(R.id.close);
        score = (Button) findViewById(R.id.score);
        settings = (Button) findViewById(R.id.settings);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        if( Globals.lang != null) {
            switch (Globals.lang.getId()) {
                case R.id.radioAnglais:
                    play.setText("Play");
                    close.setText("Exit");
                    settings.setText("Settings");
                    score.setText("Score");
                    break;
                case R.id.radioArab:
                    play.setText("إلعب");
                    close.setText("إنهاء");
                    settings.setText("الاعدادات");
                    score.setText("الرقم القياسي");
                    break;
                case R.id.radioFrancais:
                    play.setText("Jouer");
                    close.setText("Fermer");
                    settings.setText("Parametres");
                    score.setText("Score");
                    break;
            }
        }

        Context context =this;

        // Nejjar API

        Intent intent = getIntent();

        //echaabi API
        // set variables
        Globals.id_exercice = "T_5_4";
        Globals.id_application = "2018_2_3_3";

        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Globals.longitude = 33.968516;
        Globals.latitude = -6.891886;

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Globals.date_actuelle1= df.format(new Date());
        Globals.date_actuelle2= df.format(new Date());

        Globals.device = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        Globals.flag = true;
    }

    public void play(View v)
    {
        Intent intent = new Intent (this, levels.class);
        startActivity(intent);
        finish();
    }

    public void settings(View v)
    {
        Intent intent = new Intent (this, settings.class);
        startActivity(intent);
        finish();
    }

    public void score(View v)
    {
        Intent intent = new Intent (this, score.class);
        startActivity(intent);
        finish();
    }

    public void close(View v)
    {
        Intent intent = new Intent(getApplicationContext(), settings.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

    public void playMusic(View v)
    {
        Context context =this;
    }


}

