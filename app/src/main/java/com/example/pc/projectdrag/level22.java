package com.example.pc.projectdrag;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class level22 extends AppCompatActivity {
    private Handler mHandler = new Handler();

    private ViewGroup mainLayout;
    private ImageView panneau;

    private ImageView icon1;
    private ImageView icon2;
    private ImageView icon3;
    private ImageView icon4;

    private ImageView spin1;
    private ImageView spin2;
    private ImageView spin3;
    private ImageView spin4;

    private int xDelta;
    private int yDelta;

    private Rect myViewRect1, myViewRect2, myViewRect3, myViewRect4;
    private Rect otherViewRect1, otherViewRect2, otherViewRect3, otherViewRect4;

    private int[] slide_images;
    private int[] slide_images_fr;
    private int[] slide_images_en;
    private int[] encouragement_ar;
    private int[] encouragement_fr;
    private int[] encouragement_en;
    Context context;

    Chronometer chrono;
    CharSequence time;
    int elapsedMillis;

    Button soundBtn;
    Button resetBtn;
    Button menuBtn;
    public static int countActivitylevel2View;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level22);

        countActivitylevel2View++;
        mainLayout = (ConstraintLayout) findViewById(R.id.main);


        panneau = (ImageView) findViewById(R.id.panneau);
        icon1 = (ImageView) findViewById(R.id.icon1);
        icon2 = (ImageView) findViewById(R.id.icon2);
        icon3 = (ImageView) findViewById(R.id.icon3);
        icon4 = (ImageView) findViewById(R.id.icon4);

        spin1 = (ImageView) findViewById(R.id.spin1);
        spin2 = (ImageView) findViewById(R.id.spin2);
        spin3 = (ImageView) findViewById(R.id.spin3);
        spin4 = (ImageView) findViewById(R.id.spin4);

        chrono = (Chronometer) findViewById(R.id.chrono);
        chrono.start();

        icon1.setOnTouchListener(onTouchListener());
        icon2.setOnTouchListener(onTouchListener());
        icon3.setOnTouchListener(onTouchListener());
        icon4.setOnTouchListener(onTouchListener());

        resetBtn = (Button) findViewById(R.id.reset);
        menuBtn  = (Button) findViewById(R.id.menu);
        context =this;

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), level22.class);
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
                                if (Globals.soundStatus==1) {
                                    BackgroundSoundService.encouragement.start();
                                }
                            }
                            break;
                    }



                    myViewRect1 = new Rect();
                    icon1.getHitRect(myViewRect1);
                    otherViewRect1 = new Rect();
                    panneau.getHitRect(otherViewRect1);
                    if (Rect.intersects(myViewRect1, otherViewRect1)) {
                        if (Globals.soundStatus==1) {
                            BackgroundSoundService.cheerSound.start();
                        }
                        icon1.setX(0);
                        icon1.setY(0);
                        icon1.setVisibility(View.INVISIBLE);
                        int x1 = panneau.getLeft() - panneau.getWidth() / 4;
                        int y1 = panneau.getTop() - spin1.getHeight();
                        int heightP = panneau.getHeight();
                        spin1.setVisibility(View.VISIBLE);
                        spin1.setX((float) x1);
                        spin1.setY((float) y1);
                        //spin1.setY(120);
                        //spin1.setX(215);
                        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, heightP - 3 * spin1.getHeight());
                        animation.setDuration(1500);
                        animation.setFillAfter(true);
                        animation.setAnimationListener(new level22.MyAnimationListener());
                        spin1.startAnimation(animation);
                    }

                    myViewRect2 = new Rect();
                    icon2.getHitRect(myViewRect2);
                    otherViewRect2 = new Rect();
                    panneau.getHitRect(otherViewRect2);
                    if (Rect.intersects(myViewRect2, otherViewRect2)) {
                        if (Globals.soundStatus==1) {
                            BackgroundSoundService.cheerSound.start();
                        }
                        icon2.setX(0);
                        icon2.setY(0);
                        int x2 = panneau.getLeft() - panneau.getWidth() / 4;
                        int y2 = panneau.getTop() - spin2.getHeight();
                        int heightP = panneau.getHeight();
                        icon2.setVisibility(View.INVISIBLE);
                        spin2.setX((float) x2);
                        spin2.setY((float) y2);
                        spin2.setVisibility(View.VISIBLE);
                        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, heightP - 2 * spin1.getHeight());
                        animation.setDuration(1500);
                        animation.setFillAfter(true);
                        animation.setAnimationListener(new level22.MyAnimationListener());
                        spin2.startAnimation(animation);
                    }

                    myViewRect3 = new Rect();
                    icon3.getHitRect(myViewRect3);
                    otherViewRect3 = new Rect();
                    panneau.getHitRect(otherViewRect3);
                    if (Rect.intersects(myViewRect3, otherViewRect3)) {
                        if (Globals.soundStatus==1) {
                            BackgroundSoundService.cheerSound.start();
                        }
                        icon3.setX(0);
                        icon3.setY(0);
                        int x3 = panneau.getLeft() - panneau.getWidth() / 4;
                        int y3 = panneau.getTop() - spin3.getHeight();
                        int heightP = panneau.getHeight();
                        spin3.setX((float) x3);
                        spin3.setY((float) y3);
                        spin3.setVisibility(View.VISIBLE);
                        icon3.setVisibility(View.INVISIBLE);
                        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, heightP - spin1.getHeight());
                        animation.setDuration(1500);
                        animation.setFillAfter(true);
                        animation.setAnimationListener(new level22.MyAnimationListener());
                        spin3.startAnimation(animation);
                    }

                    myViewRect4 = new Rect();
                    icon4.getHitRect(myViewRect4);
                    otherViewRect4 = new Rect();
                    panneau.getHitRect(otherViewRect4);
                    if (Rect.intersects(myViewRect4, otherViewRect4)) {
                        if (Globals.soundStatus==1) {
                            BackgroundSoundService.cheerSound.start();
                        }
                        icon4.setX(0);
                        icon4.setY(0);
                        int x4 = panneau.getLeft() - panneau.getWidth() / 4;
                        int y4 = panneau.getTop() - spin4.getHeight();
                        int heightP = panneau.getHeight();
                        spin4.setX((float) x4);
                        spin4.setY((float) y4);
                        spin4.setVisibility(View.VISIBLE);
                        icon4.setVisibility(View.INVISIBLE);
                        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, heightP);
                        animation.setDuration(1500);
                        animation.setFillAfter(true);
                        animation.setAnimationListener(new level22.MyAnimationListener());
                        spin4.startAnimation(animation);
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
            if(icon1.getVisibility() == View.INVISIBLE && icon2.getVisibility() == View.INVISIBLE
                    && icon4.getVisibility() == View.INVISIBLE && icon3.getVisibility() == View.INVISIBLE){

                elapsedMillis = (int) (SystemClock.elapsedRealtime() - chrono.getBase());

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(level22.this, levels.class));
                        finish();
                    }
                }, 2800);

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
