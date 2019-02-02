package com.example.exoplayer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class MainActivity extends AppCompatActivity {

    //Necesitamos dos variables en la que vamos a guardar la vista y el propio reproductor que va a ser una instancia de exoplayer

    private PlayerView playerView;
    private SimpleExoPlayer player;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        playerView = findViewById(R.id.player_view);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent cogerEditText = getIntent();
        String url = cogerEditText.getStringExtra("valor_edittext");

        //TextView text_url = (TextView) findViewById(R.id.textView_valor_url);
        //text_url.setText(String.valueOf(url));

        //Creamos el reproductor
        player = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());
        //Se lo asignamos a la view
        playerView.setPlayer(player);

        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this,"Exoplayer"));

        ExtractorMediaSource archivo_multimedia = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(String.valueOf(url)));

        player.prepare(archivo_multimedia);
        //Lo ponemos en marcha
        player.setPlayWhenReady(true);
    }

    @Override
    protected void onStop() {
        super.onStop();

        playerView.setPlayer(null);
        player.release();
        player=null;
    }

    public void volver (View view){
        Intent intent = new Intent(this, EntradaActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }



}
