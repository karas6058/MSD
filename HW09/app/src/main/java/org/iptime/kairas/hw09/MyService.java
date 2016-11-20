package org.iptime.kairas.hw09;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;

public class MyService extends Service {
    private MediaPlayer mediaPlayer = null;

    public MyService() {
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d("slog", "onStart()");
        if (mediaPlayer != null)
            mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(this, intent.getIntExtra("file", R.raw.music1));
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
