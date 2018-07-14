package com.example.pc.projectdrag;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import android.os.Handler;


public class level1 extends AppCompatActivity {
    private Handler mHandler = new Handler();

    private ViewGroup mainLayout;
    private ImageView imageVert;
    private ImageView panneauVert;

    private ImageView imageRouge;
    private ImageView panneauRouge;

    private ImageView imageJaune;
    private ImageView panneauJaune;

    private ImageView spinV;
    private ImageView spinJ;
    private ImageView spinR;
    private int xDelta;
    private int yDelta;

    private int[] slide_images;
    private int[] slide_images_fr;
    private int[] slide_images_en;

    private int[] encouragement_ar;
    private int[] encouragement_fr;
    private int[] encouragement_en;

    Button resetBtn;
    Button menuBtn;
    Context context;
    Chronometer chrono;
    CharSequence time;
    int elapsedMillis;
    public static int countActivitylevel1View;

    //popup window
    private Context mContext;
    private Activity mActivity;
    private ConstraintLayout mConstraintLayout;
    private PopupWindow PopupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);
        countActivitylevel1View++;
        Globals.Nombre_operation_echou1=0;
        Globals.Nombre_operation_reuss_level1=0;

        //popup window
        mContext = getApplicationContext();
        mActivity = level1.this;
        mConstraintLayout = (ConstraintLayout) findViewById(R.id.rl2);

        mainLayout = (ConstraintLayout) findViewById(R.id.main);
        imageJaune = (ImageView) findViewById(R.id.iconj);
        panneauJaune = (ImageView) findViewById(R.id.image3);
        spinJ = (ImageView) findViewById(R.id.spin);

        imageRouge = (ImageView) findViewById(R.id.iconr);
        panneauRouge = (ImageView) findViewById(R.id.image2);
        spinR = (ImageView) findViewById(R.id.spinR);

        imageVert = (ImageView) findViewById(R.id.iconv);
        panneauVert = (ImageView) findViewById(R.id.image1);
        spinV = (ImageView) findViewById(R.id.spinV);

        chrono = (Chronometer) findViewById(R.id.chrono);
        chrono.start();


        imageVert.setOnTouchListener(onTouchListener());
        imageRouge.setOnTouchListener(onTouchListener());
        imageJaune.setOnTouchListener(onTouchListener());


        resetBtn = (Button) findViewById(R.id.reset);
        menuBtn  = (Button) findViewById(R.id.menu);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), level1.class);
                view.getContext().startActivity(Intent);
                finish();
            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        slide_images = new int[]{
                R.raw.cheering1,
                R.raw.cheering2,
                R.raw.ca1,
                R.raw.ca2,
                R.raw.ca3,
                R.raw.ca4,
                R.raw.ca5,
                R.raw.ca7,
        };
        slide_images_en = new int[]{
                R.raw.enamzing,
                R.raw.enbest,
                R.raw.enbravo,
                R.raw.enexcelent,
                R.raw.ennicejob,
                R.raw.ensmart,
                R.raw.ensuper,
                R.raw.enverrygood,
                R.raw.enyoucan,
        };
        slide_images_fr = new int[]{
                R.raw.frbravo,
                R.raw.frexcelent,
                R.raw.frfier,
                R.raw.frformidable,
                R.raw.frincroyable,
                R.raw.frspecial,
        };

        encouragement_fr = new int[]{
                R.raw.frrprochedubut,
                R.raw.frrreessayer,
                R.raw.frrvousetescompetent,
                R.raw.frrvouspouvez,
        };
        encouragement_ar = new int[]{
                R.raw.arrantakarib,
                R.raw.arrhawil,
                R.raw.arrhawilmarratanokhra,
                R.raw.arrtastatii,
        };
        encouragement_en = new int[]{
                R.raw.enntrymore,
                R.raw.ennyoucan,
                R.raw.ennyoureclose,
        };

    }

    float initialX, initialY;
    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (!BackgroundSoundService.encouragement.isPlaying() && !BackgroundSoundService.cheerSound.isPlaying()) {

                    final int x = (int) event.getRawX();
                    final int y = (int) event.getRawY();


                    //music with diferent langage
                    context = getBaseContext();
                    Random r = new Random();

                    if (Globals.lang != null) {
                        switch (Globals.lang.getId()) {
                            case R.id.radioAnglais:
                                int soundNbrEN = (r.nextInt(9) + 0);
                                BackgroundSoundService.cheerSound = MediaPlayer.create(context, slide_images_en[soundNbrEN]);
                                BackgroundSoundService.cheerSound.setLooping(false);
                                BackgroundSoundService.cheerSound.setVolume(100, 100);
                                //____________________
                                int soundNbrENN = (r.nextInt(3) + 0);
                                BackgroundSoundService.encouragement = MediaPlayer.create(context, encouragement_en[soundNbrENN]);
                                BackgroundSoundService.encouragement.setLooping(false);
                                BackgroundSoundService.encouragement.setVolume(100, 100);
                                break;
                            case R.id.radioArab:
                                int soundNbr = (r.nextInt(8) + 0);
                                BackgroundSoundService.cheerSound = MediaPlayer.create(context, slide_images[soundNbr]);
                                BackgroundSoundService.cheerSound.setLooping(false);
                                BackgroundSoundService.cheerSound.setVolume(100, 100);
                                //____________________
                                int soundNbrARR = (r.nextInt(4) + 0);
                                BackgroundSoundService.encouragement = MediaPlayer.create(context, encouragement_ar[soundNbrARR]);
                                BackgroundSoundService.encouragement.setLooping(false);
                                BackgroundSoundService.encouragement.setVolume(100, 100);
                                break;
                            case R.id.radioFrancais:
                                int soundNbrFR = (r.nextInt(6) + 0);
                                BackgroundSoundService.cheerSound = MediaPlayer.create(context, slide_images_fr[soundNbrFR]);
                                BackgroundSoundService.cheerSound.setLooping(false);
                                BackgroundSoundService.cheerSound.setVolume(100, 100);
                                //____________________
                                int soundNbrFRR = (r.nextInt(4) + 0);
                                BackgroundSoundService.encouragement = MediaPlayer.create(context, encouragement_fr[soundNbrFRR]);
                                BackgroundSoundService.encouragement.setLooping(false);
                                BackgroundSoundService.encouragement.setVolume(100, 100);
                                break;
                        }
                    }


                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_DOWN:
                            initialX = view.getX();
                            initialY = view.getY();

                            ConstraintLayout.LayoutParams lParams = (ConstraintLayout.LayoutParams)
                                    view.getLayoutParams();

                            xDelta = x - lParams.leftMargin;
                            yDelta = y - lParams.topMargin;
                            break;

                        case MotionEvent.ACTION_MOVE:
                            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view
                                    .getLayoutParams();
                            layoutParams.leftMargin = x - xDelta;
                            layoutParams.topMargin = y - yDelta;
                            layoutParams.rightMargin = 0;
                            layoutParams.bottomMargin = 0;
                            view.setLayoutParams(layoutParams);
                            break;

                        //________________________
                        case MotionEvent.ACTION_UP:
                            if (view.getX() != 0 && view.getY() != 0) {
                                Globals.Nombre_operation_echou1++;
                                if (Globals.soundStatus==1) {
                                    BackgroundSoundService.encouragement.start();
                                }
                            }
                            break;
                    }


                    Rect myViewRectJ = new Rect();
                    imageJaune.getHitRect(myViewRectJ);
                    Rect otherViewRect1J = new Rect();
                    panneauJaune.getHitRect(otherViewRect1J);
                    if (Rect.intersects(myViewRectJ, otherViewRect1J)) {
                        Globals.Nombre_operation_reuss_level1++;
                        if (Globals.soundStatus==1) {
                            BackgroundSoundService.cheerSound.start();
                        }
                        imageJaune.setX(0);
                        imageJaune.setY(0);
                        int x2 = panneauJaune.getLeft() - panneauJaune.getWidth() / 2;
                        int y2 = panneauJaune.getTop() - spinJ.getHeight();
                        int heightPJ = panneauJaune.getHeight();
                        spinJ.setY((float) y2);
                        spinJ.setX((float) x2);
                        imageJaune.setVisibility(View.INVISIBLE);
                        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, heightPJ);
                        animation.setDuration(1500);
                        animation.setFillAfter(true);
                        animation.setAnimationListener(new MyAnimationListener());
                        spinJ.startAnimation(animation);
                    }

                    Rect myViewRect = new Rect();
                    imageVert.getHitRect(myViewRect);
                    Rect otherViewRect1 = new Rect();
                    panneauVert.getHitRect(otherViewRect1);
                    if (Rect.intersects(myViewRect, otherViewRect1)) {
                        Globals.Nombre_operation_reuss_level1++;
                        if (Globals.soundStatus==1) {
                            BackgroundSoundService.cheerSound.start();
                        }
                        imageVert.setX(0);
                        imageVert.setY(0);
                        int x1 = panneauVert.getLeft() - panneauVert.getWidth() / 2;
                        int y1 = panneauVert.getTop() - spinV.getHeight();
                        int heightPV = panneauVert.getHeight();
                        spinV.setY((float) y1);
                        spinV.setX((float) x1);
                        imageVert.setVisibility(View.INVISIBLE);
                        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, heightPV);
                        animation.setDuration(1500);
                        animation.setFillAfter(true);
                        animation.setAnimationListener(new MyAnimationListener());
                        spinV.startAnimation(animation);
                    }

                    Rect myViewRectR = new Rect();
                    imageRouge.getHitRect(myViewRectR);
                    Rect otherViewRect1R = new Rect();
                    panneauRouge.getHitRect(otherViewRect1R);
                    if (Rect.intersects(myViewRectR, otherViewRect1R)) {
                        Globals.Nombre_operation_reuss_level1++;
                        if (Globals.soundStatus==1) {
                            BackgroundSoundService.cheerSound.start();
                        }
                        imageRouge.setX(0);
                        imageRouge.setY(0);
                        int x3 = panneauRouge.getLeft() - panneauRouge.getWidth() / 2;
                        int y3 = panneauRouge.getTop() - spinR.getHeight();
                        int heightPR = panneauRouge.getHeight();
                        spinR.setY((float) y3);
                        spinR.setX((float) x3);
                        imageRouge.setVisibility(View.INVISIBLE);
                        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, heightPR);
                        animation.setDuration(1500);
                        animation.setFillAfter(true);
                        animation.setAnimationListener(new MyAnimationListener());

                        spinR.startAnimation(animation);
                    }

                    mainLayout.invalidate();

                }
                return true;
            }
        };
    }

    private class MyAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationEnd(Animation animation) {
            if(imageRouge.getVisibility() == View.INVISIBLE && imageVert.getVisibility() == View.INVISIBLE
                    && imageJaune.getVisibility() == View.INVISIBLE){

                elapsedMillis = (int) (SystemClock.elapsedRealtime() - chrono.getBase());

                Globals.timeTableForAVG1.add(elapsedMillis);
                Globals.minimum_temps_operation_sec = Globals.timeTableForAVG1.get(0);

                //Echaabi API
                Globals.id_niveau1 = "2";

                SimpleDateFormat sdD = new SimpleDateFormat("HH:mm:ss");
                Globals.heure_debut1 = sdD.format(new Date());


                SimpleDateFormat sdF = new SimpleDateFormat("HH:mm:ss");
                Globals.heure_fin1 = sdF.format(new Date());



                for(int i = 0; i < Globals.timeTableForAVG1.size(); i++) {
                    Globals.moyen_temps_operation_sec1 += Globals.timeTableForAVG1.get(i);

                    if(Globals.timeTableForAVG1.get(i) < Globals.minimum_temps_operation_sec){
                        Globals.minimum_temps_operation_sec = Globals.timeTableForAVG1.get(i);
                    }
                }

                Globals.moyen_temps_operation_sec1 = Globals.moyen_temps_operation_sec1/Globals.timeTableForAVG1.size();

                Game g1 = new Game(Globals.id_application, Globals.id_apprenant, Globals.id_accompagnant, Globals.id_exercice, Globals.id_niveau1, Globals.date_actuelle1, Globals.heure_debut1, Globals.heure_fin1, Globals.Nombre_operation_reuss_level1, Globals.Nombre_operation_echou1, Globals.minimum_temps_operation_sec/1000, Globals.moyen_temps_operation_sec1/1000, Globals.longitude, Globals.latitude, Globals.device, Globals.flag);

                Game_Database_Handler gHandeler1 = new Game_Database_Handler(getBaseContext());
                gHandeler1.addGame(g1);


                String Infos = "";
                Infos = gHandeler1.getData("2");




                if(Build.VERSION.SDK_INT<=21){
                    //PopupWindow.setElevation(5.0f);
                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                    View customView = inflater.inflate(R.layout.popup,null);
                    PopupWindow = new PopupWindow(
                            customView,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT
                    );

                    PopupWindow.setWidth(ConstraintLayout.LayoutParams.MATCH_PARENT);
                    PopupWindow.setHeight(250);
                    TextView tv = (TextView) customView.findViewById(R.id.tv);
                    Button refresh = (Button) customView.findViewById(R.id.refresh);
                    Button next = (Button) customView.findViewById(R.id.next);

                    tv.setText(Infos);
                    // Set a click listener for the popup window close button
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            PopupWindow.showAtLocation(mConstraintLayout, Gravity.CENTER,0,0);
                        }
                    }, 2800);

                    refresh.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(level1.this, level1.class));
                            finish();
                        }
                    });

                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(level1.this, level11.class));
                            finish();
                        }
                    });

                } else {
                    startActivity(new Intent(level1.this, level11.class));
                    finish();
                }
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

    }


}

