package org.iptime.kairas.hw09;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Music extends Activity {
    ListView lv;
    TextView tv;
    ArrayList<String> song = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);
        final Button btn_stop = (Button) findViewById(R.id.stop);
        final Button btn_play = (Button) findViewById(R.id.play);
        tv = (TextView) findViewById(R.id.playStatus);

        ActivityManager manager = (ActivityManager) this.getSystemService(Activity.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo serviceInfo : manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("org.iptime.kairas.hw09.MyService".equals(serviceInfo.service.getClassName())) {
                tv.setText("재생");
            }
        }

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
}
