package com.example.mysudubomb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.example.mysudubomb.R;
import com.example.mysudubomb.myview.MyImageView;
import com.example.mysudubomb.properties.AppProperties;
import com.example.mysudubomb.service.MusicService;
import com.example.mysudubomb.sourceprovider.IMusic;
import com.example.mysudubomb.utils.NoActionBar;

public class AudioActivity extends AppCompatActivity {

    private Button btn_play_and_pause;
    private Button btn_replay;
    private IMusic iMusic;
    private MyCoon myCoon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        NoActionBar.noActionBar(this);
        initView();
        LinkService();


    }

    private void LinkService() {
        Intent intent = new Intent(this, MusicService.class);
        myCoon = new MyCoon();
        startService(intent);
        bindService(intent, myCoon, BIND_AUTO_CREATE);

    }

    private void initView() {
        btn_play_and_pause = (Button) findViewById(R.id.btn_play_and_pause);
        btn_replay = (Button) findViewById(R.id.btn_replay);
        MyImageView miv_audio = (MyImageView) findViewById(R.id.miv_audio);
        miv_audio.setImageUrl(AppProperties.AUTIO_PATH);
    }

    public void onclick(View view) {
        switch (view.getId()){
            case R.id.btn_play_and_pause:
                play();

                break;
            case R.id.btn_replay:
                replay();

                break;
            case R.id.btn_finish_audio:
                finish();
                break;
        }
    }
    public void replay() {
        iMusic.replay();
        btn_play_and_pause.setText(R.string.pause);
    }
    public void play( ) {

        int playState = iMusic.getPlayState();
        switch (playState){

            case MusicService.PLAY:
                iMusic.pause();
                btn_play_and_pause.setText(R.string.play);
                break;
            case MusicService.PAUSE:
            case MusicService.STOP:
                iMusic.play();
                btn_play_and_pause.setText(R.string.pause);
                break;
        }

    }private  class  MyCoon  implements ServiceConnection {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {/*回调*/
            iMusic = (IMusic) service;
            int playState = iMusic.getPlayState();
            if (playState == MusicService.PLAY){

                btn_play_and_pause.setText(R.string.pause);
            }
            /*每次bind一连接判断一下状态*/
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(myCoon);/*页面销毁断开bindservice*/
    }
}
