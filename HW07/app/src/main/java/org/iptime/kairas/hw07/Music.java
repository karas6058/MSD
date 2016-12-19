package org.iptime.kairas.hw07;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Music extends Activity {
    ListView lv;
    TextView tv;
    ArrayList<String> song;
    private static SeekBar seekBar;
    private static Handler seekHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);

        song = new ArrayList<String>();

        final Button btn_stop = (Button) findViewById(R.id.stop);
        final Button btn_play = (Button) findViewById(R.id.play);

        tv = (TextView) findViewById(R.id.playStatus);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekHandler = new Handler();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (MyService.mediaPlayer.isPlaying()) {
                    MyService.mediaPlayer.seekTo(progress);
                    seekBar.setProgress(progress);
                }
            }
        });


        ActivityManager manager = (ActivityManager) this.getSystemService(Activity.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo serviceInfo : manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("org.iptime.kairas.hw07.MyService".equals(serviceInfo.service.getClassName())) {
                tv.setText("재생");
            }
        }

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekHandler.postDelayed(run, 1000);
                Intent intent = new Intent(Music.this, MyService.class);
                startService(intent);
                tv.setText("재생");
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Music.this, MyService.class);
                stopService(intent);
                tv.setText("중지");
            }
        });


        lv = (ListView) findViewById(R.id.listview);
        updateSonglist();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Field[] fields = R.raw.class.getFields();
                Uri music = Uri.parse("android.resource://" + getPackageName() + "/raw/" + fields[position + 1].getName());
                Intent intent = new Intent(Music.this, MyService.class);
                intent.putExtra("file", music.toString());
                startService(intent);
                tv.setText("재생");
            }
        });
    }

    public void updateSonglist() {
        Field[] fields = R.raw.class.getFields();
        for (int count = 1; count < fields.length -1; count++) {
            song.add(fields[count].getName());
        }
        ArrayAdapter<String> songList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, song);
        lv.setAdapter(songList);
    }

    private static Runnable run = new Runnable() {

        public void run() {

            if (MyService.mediaPlayer.isPlaying()) {
                int mediaPos_new = MyService.mediaPlayer.getCurrentPosition();
                int mediaMax_new = MyService.mediaPlayer.getDuration();

                seekBar.setMax(mediaMax_new);
                seekBar.setProgress(mediaPos_new);
                seekHandler.postDelayed(run, 1000);
            }
        }
    };
}
