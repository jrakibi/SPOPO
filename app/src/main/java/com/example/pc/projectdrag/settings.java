package com.example.pc.projectdrag;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class settings extends Activity {

    Intent svc;
    private RadioGroup radioLangGroup;
    private RadioButton radioLangButton;
    private Button btnValid;
    private TextView langText;
    private TextView soundText;

    private RadioButton checkedFrRadio;
    private RadioButton checkedArRadio;
    private RadioButton checkedAnRadio;

    public Switch switchSound;

    public static int selectedId;
    public static int countActivityView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        countActivityView+=1;

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        // Nejjar API
        Context context =this;
        Intent intent = getIntent();

        //echaabi API
        // set variables
        Globals.id_accompagnant = intent.getStringExtra("id_accomp");
        Globals.id_apprenant = intent.getStringExtra("kid_id");

        //background music
        BackgroundSoundService.player = MediaPlayer.create(this, R.raw.happybackground);
        BackgroundSoundService.player.setLooping(false);
        BackgroundSoundService.player.setVolume(100,100);


        radioLangGroup = (RadioGroup) findViewById(R.id.radioLang);
        btnValid = (Button) findViewById(R.id.btnValidate);
        langText = (TextView) findViewById(R.id.lang);
        soundText  = (TextView) findViewById(R.id.sound);
        checkedFrRadio = (RadioButton) findViewById(R.id.radioFrancais);
        checkedArRadio = (RadioButton) findViewById(R.id.radioArab);
        checkedAnRadio = (RadioButton) findViewById(R.id.radioAnglais);

        switchSound =  (Switch) findViewById(R.id.soundState);

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        switchSound.setChecked(sharedPreferences.getBoolean("toggleButton", true));

        switchSound.setOnClickListener(new Switch.OnClickListener() {
            public void onClick(View v) {
                if(switchSound.isChecked()) {
                    Globals.soundStatus = 1;
                    SharedPreferences sharedPreferences = PreferenceManager
                            .getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("toggleButton", true);
                    editor.commit();
                }else{
                    Globals.soundStatus = 0;
                    SharedPreferences sharedPreferences = PreferenceManager
                            .getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("toggleButton", false);
                    editor.commit();
                }
            }
        });

        if(countActivityView==1) {
            checkedFrRadio.setChecked(true);
            Globals.lang = (RadioButton) findViewById(R.id.radioFrancais);
            svc=new Intent(this, BackgroundSoundService.class);
            startService(svc);
        }



        switch (Globals.lang.getId()){
            case R.id.radioAnglais :
                btnValid.setText("VALIDATE");
                langText.setText("Change language");
                soundText.setText("Sound");
                checkedAnRadio.setChecked(true);
                break;
            case R.id.radioArab:
                btnValid.setText("تأكيد");
                langText.setText("تغيير اللغة");
                soundText.setText("الصوت");
                checkedArRadio.setChecked(true);
                break;
            case R.id.radioFrancais:
                btnValid.setText("VALIDER");
                langText.setText("Changer la langue");
                soundText.setText("Son");
                checkedFrRadio.setChecked(true);
                break;
        }

        /*if(countActivityView !=1) {
            if (BackgroundSoundService.player.isPlaying()) {
                switchSound.setChecked(true);
            }else if (!BackgroundSoundService.player.isPlaying()) {
                switchSound.setChecked(false);
            }
        }*/


        addListenerOnButton();




    }

    public void addListenerOnButton() {

        btnValid.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // get selected radio button from radioGroup
                selectedId = radioLangGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioLangButton = (RadioButton) findViewById(selectedId);

                Globals.lang = (RadioButton) findViewById(selectedId);

                /*if(!switchSound.isChecked()){
                    if(BackgroundSoundService.player.isPlaying()){
                        BackgroundSoundService.player.pause();
                    }
                } else {
                    if (!BackgroundSoundService.player.isPlaying()){
                        BackgroundSoundService.player.start();
                    }
                }*/

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }

        });

    }
}
