package org.iptime.kairas.hw09;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class Music extends AppCompatActivity {
    ListView lv;
    ArrayList<String> song = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);

        Button btn_play = (Button) findViewById(R.id.play);
        Button btn_stop = (Button) findViewById(R.id.stop);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Music.this, MyService.class);
                startService(intent);
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Music.this, MyService.class);
                stopService(intent);
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
                intent.putExtra("file", music);
                startService(intent);
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
}
