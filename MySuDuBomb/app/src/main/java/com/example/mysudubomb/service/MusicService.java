package com.example.mysudubomb.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.mysudubomb.R;
import com.example.mysudubomb.sourceprovider.IMusic;

import java.io.IOException;

public class MusicService extends Service {
    public static final int PLAY=1;
    public static final int STOP=2;
    public static final int PAUSE=3;

    private int playState = STOP;
    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();/*如果接口实现不了，找一个接口的实现子类，去继承*/
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mediaPlayer==null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.autio);
        }//对mediaPlayer是否为空进行一个判断，如果不为空就不再Create，否则会重复播放



        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
        mediaPlayer = null;
        super.onDestroy();

    }

    private void play(){

        mediaPlayer.start();
        setPlayState(PLAY);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer arg0) {
                //如果当前歌曲播放完毕
                replay();
            }
        });


    }
    private void replay(){
        mediaPlayer.stop();
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
        setPlayState(PLAY);
    }
    private void pause(){
        if (mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            setPlayState(PAUSE);

        }

    }

    private int getPlayState(){

        return this.playState;

    }
    public void setPlayState(int playState) {
        this.playState=playState;
    }
    private class MyBind extends Binder implements IMusic {
        @Override
        public void play() {
            MusicService.this.play();
        }

        @Override
        public void replay() {
            MusicService.this.replay();
        }

        @Override
        public void pause() {
            MusicService.this.pause();
        }


        @Override
        public int getPlayState() {
            return MusicService.this.getPlayState();
        }


    }
}
