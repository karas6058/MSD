package org.iptime.kairas.hw09;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.TextView;

public class MyService extends Service {
    private MediaPlayer mediaPlayer = null;

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d("slog", "onStart()");

        if (mediaPlayer != null)
            mediaPlayer.stop();

        String file = intent.getStringExtra("file");

        if (file == null)
            mediaPlayer = MediaPlayer.create(this, R.raw.music1);
        else {
            Uri song = Uri.parse(file);
            mediaPlayer = MediaPlayer.create(this, song);
        }

        mediaPlayer.start();
    }

    @Override
    public void onDestroy() {
        Log.d("slog", "onDestroy()");
        mediaPlayer.stop();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
