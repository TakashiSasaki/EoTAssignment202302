package com.example.eotassignment202302;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.io.IOException;

public class MediaActivity extends AppCompatActivity {

    private MediaPlayer _player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        this._player = new MediaPlayer();

        //final String mediaFileUriStr =
        //        "android.resource://" + getPackageName() + "/"
        //                + R.raw.mountain_stream;
        final String mediaFileUriStr =
                "android.resource://" + getPackageName() + "/"
                        + R.raw.movie;

        Uri mediaFileUri = Uri.parse(mediaFileUriStr);

        try {
            _player.setDataSource(getApplicationContext(), mediaFileUri);
            _player.setOnPreparedListener(new PlayerPreparedListener());
            _player.setOnCompletionListener(new PlayerCompletionListener());
            _player.prepareAsync(); //ここでメディアファイル（mp3, mp4）がロードされる
        } catch (IOException e) {
            e.printStackTrace();
        }//try


        //スイッチにオンオフを検出するリスナを与える
        ((SwitchMaterial) findViewById(R.id.swLoop))
                .setOnCheckedChangeListener(
                        new LoopSwitchChangedListener()
                );

        //動画を表示するために表示領域（SurfaceView）を制御する
        //SurfaceHolderを取得する。
        //ここは複雑なので余裕があったら授業で。
        SurfaceHolder surfaceHolder
                = ((SurfaceView) findViewById(R.id.surfaceView))
                .getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                _player.setDisplay(holder);
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

            }
        });

    }//onCreate method

    @Override
    protected void onDestroy() {
        if (_player.isPlaying()) {
            _player.stop();
        }//if

        //MediaPlayerが確保しているリソースを解放する。
        //メディアファイルのバッファリングに使ったメモリなども解放される。
        _player.release();

        _player = null; //デストラクタでの解放を促す
        //子クラスの終了処理が終わってから親クラスの終了処理
        super.onDestroy();
    }//onDestroy

    //リスナクラスをクラス内クラスとして実装する。

    //プレーヤーの再生準備が整った時のリスナクラス。
    private class PlayerPreparedListener
            implements MediaPlayer.OnPreparedListener {

        @Override
        public void onPrepared(MediaPlayer mp) {
            //無駄な一時変数は使わない（場合による）
            findViewById(R.id.btPlay).setEnabled(true); //再生準備が整ったからね
            findViewById(R.id.btBack).setEnabled(true); //再生準備が整ったからね
            findViewById(R.id.btForward).setEnabled(true); //再生準備が整ったからね
        }//onPrepared method
    }//PlayerPreparedListener class

    //再生が終了したときのリスナクラス。
    //プレイヤーにループが設定されていても、
    //一回の再生が終わるごとにこのリスナが呼び出される。
    private class PlayerCompletionListener
            implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            if (!_player.isLooping()) {
                //再生終了時には再生開始を促すテキストにする
                ((Button) findViewById(R.id.btPlay))
                        .setText(R.string.bt_play_play);
            }//if
        }//onCompletion method
    }//PlayerCompletionListener class

    //再歳ボタンがタップされた時の処理
    //レイアウトインフレーターから見つけてもらうために public にする
    public void onPlayButtonClick(View view) {
        Button btPlay = findViewById(R.id.btPlay);
        if (_player.isPlaying()) {
            //プレーヤーが再生中なら一時停止
            _player.pause();
            //一時停止中には再開を促すテキストにする
            btPlay.setText(R.string.bt_play_play);
        } else {
            //プレーヤーが再生中でなければ再生開始
            _player.start();
            //再生中は一時停止を促すテキストにする
            btPlay.setText(R.string.bt_play_pause);
        }//if
    }//onPlayButtonClick method

    //戻るボタンが押されたとき
    public void onBackButtonClick(View view) {
        //再生位置を先頭に変更。
        this._player.seekTo(0);
    }//onBackButtonClick method

    //進むボタンが押されたとき
    public void onForwardButtonClick(View view) {
        int duration = _player.getDuration();
        _player.seekTo(duration);

        //再生中でなければ再生を開始
        if (!_player.isPlaying()) {
            _player.start();
        }
    }//onForwardButtonClick method

    //リピート再生のスイッチを検出するリスナ
    private class LoopSwitchChangedListener
            implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            _player.setLooping(isChecked);
        }//onCheckedChanged method
    }//LoopSwitchChangedListener class
}// MainActivity class