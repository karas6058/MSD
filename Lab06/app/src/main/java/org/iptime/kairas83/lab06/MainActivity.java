package org.iptime.kairas83.lab06;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    MediaPlayer player;
    TextView tv;
    ListView lv;
    ArrayList<String> song = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = MediaPlayer.create(this, R.raw.music1);
        lv = (ListView) findViewById(R.id.listview);
        updateSonglist();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Field[] fields = R.raw.class.getFields();
                Uri music = Uri.parse("android.resource://" + getPackageName() + "/raw/" + fields[position + 1].getName());
                playSong(music);
            }
        });
    }

    public void updateSonglist() {
        Field[] fields = R.raw.class.getFields();
        for (int count = 1; count < fields.length; count++) {
            song.add(fields[count].getName());
        }
        ArrayAdapter<String> songList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, song);
        lv.setAdapter(songList);
    }

    private void playSong(Uri songPath) {
        try {
            player.reset();
            player= MediaPlayer.create(this, songPath);
            play(null);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void play(View view) {
        if (!player.isPlaying()) {
            player.start();
            tv = (TextView) this.findViewById(R.id.playStatus);
            tv.setText("재생중");
        }
    }

    public void stop(View view) {
        Log.i("what stop", String.valueOf(player));
        if (player.isPlaying()) {
            System.out.println(player);
            player.pause();
            tv.setText("정지");
        }
    }
}
