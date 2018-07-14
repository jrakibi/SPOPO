package com.example.pc.projectdrag;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
 * Created by pc on 03/05/2018.
 */
public class BackgroundSoundService extends Service {
    private final String TAG = null;
    public static MediaPlayer player;
    public static MediaPlayer cheerSound;
    public static MediaPlayer encouragement;
    public static MediaPlayer howto;
    public IBinder onBind(Intent arg0) {

        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Context context = this;
        BackgroundSoundService.encouragement = MediaPlayer.create(context, R.raw.enamzing);
        BackgroundSoundService.cheerSound = MediaPlayer.create(context, R.raw.enamzing);
        BackgroundSoundService.howto = MediaPlayer.create(context, R.raw.enamzing);


    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }
    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }

    public void onStop(){
    }

    public void onPause() {
    }
    @Override
    public void onDestroy() {
    }

    @Override
    public void onLowMemory() {

    }
}