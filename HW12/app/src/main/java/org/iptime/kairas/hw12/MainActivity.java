package org.iptime.kairas.hw12;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    SensorManager sensorManager;
    SensorEventListener accL;
    SensorEventListener oriL;
    SensorEventListener lightL;
    SensorEventListener proximityL;
    Sensor oriSensor;
    Sensor accSensor;
    Sensor lightSensor;
    Sensor proximitySensor;
    TextView ax, ay, az;
    TextView ox, oy, oz;
    TextView light, proximity;

    AudioManager am;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        am= (AudioManager) getBaseContext().getSystemService(getApplicationContext().AUDIO_SERVICE);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);    // SensorManager 인스턴스를 가져옴
        oriSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);       // 방향 센서
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);     // 가속도 센서
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);           // 조도 센서
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);   // 근접센서
        oriL = new oriListener();        // 방향 센서 리스너 인스턴스
        accL = new accListener();       // 가속도 센서 리스너 인스턴스
        lightL = new lightListener();
        proximityL = new proximityListener();
        ax = (TextView) findViewById(R.id.acc_x);
        ay = (TextView) findViewById(R.id.acc_y);
        az = (TextView) findViewById(R.id.acc_z);
        ox = (TextView) findViewById(R.id.ori_x);
        oy = (TextView) findViewById(R.id.ori_y);
        oz = (TextView) findViewById(R.id.ori_z);
        light = (TextView) findViewById(R.id.light);
        proximity = (TextView) findViewById(R.id.proximity);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onResume() {
        super.onResume();

        sensorManager.registerListener(accL, accSensor, SensorManager.SENSOR_DELAY_NORMAL);    // 가속도 센서 리스너 오브젝트를 등록
        sensorManager.registerListener(oriL, oriSensor, SensorManager.SENSOR_DELAY_NORMAL);    // 방향 센서 리스너 오브젝트를 등록
        sensorManager.registerListener(lightL, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);    // 방향 센서 리스너 오브젝트를 등록
        sensorManager.registerListener(proximityL, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);    // 방향 센서 리스너 오브젝트를 등록
    }

    @Override
    public void onPause() {
        super.onPause();

        sensorManager.unregisterListener(oriL);    // unregister acceleration listener
        sensorManager.unregisterListener(accL);    // unregister orientation listener
        sensorManager.unregisterListener(lightL);    // unregister acceleration listener
        sensorManager.unregisterListener(proximityL);    // unregister orientation listener
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private class accListener implements SensorEventListener {
        long currentTime;
        long lastTime;
        long gabOfTime;
        float x, y, z;
        float lastX, lastY, lastZ;
        @Override
        public void onSensorChanged(SensorEvent event) {  // 가속도 센서 값이 바뀔때마다 호출됨
            ax.setText(Float.toString(event.values[0]));
            ay.setText(Float.toString(event.values[1]));
            az.setText(Float.toString(event.values[2]));
            Log.i("SENSOR", "Acceleration changed.");
            Log.i("SENSOR", "  Acceleration X: " + event.values[0]
                    + ", Acceleration Y: " + event.values[1]
                    + ", Acceleration Z: " + event.values[2]);

            currentTime = System.currentTimeMillis();
            gabOfTime = (currentTime - lastTime);

            if (gabOfTime > 100) {
                lastTime = currentTime;
                x = event.values[0];
                y = event.values[1];
                z = event.values[2];

                float speed = Math.abs(x + y + z - lastX - lastY - lastZ) /gabOfTime * 1000;

                if (speed > 200) {  //민감도
                    am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE); //진동 모드
                    Toast.makeText(getApplicationContext(), "진동모드입니다", Toast.LENGTH_SHORT).show();
                }
                lastX = event.values[0];
                lastY = event.values[1];
                lastZ = event.values[2];
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }

    private class oriListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {  // 방향 센서 값이 바뀔때마다 호출됨
            ox.setText(Float.toString(event.values[0]));
            oy.setText(Float.toString(event.values[1]));
            oz.setText(Float.toString(event.values[2]));
            Log.i("SENSOR", "Orientation changed.");
            Log.i("SENSOR", "  Orientation X: " + event.values[0]
                    + ", Orientation Y: " + event.values[1]
                    + ", Orientation Z: " + event.values[2]);
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }

    private class lightListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {  // 방향 센서 값이 바뀔때마다 호출됨
            light.setText(Float.toString(event.values[0]));
            Log.i("SENSOR", "Light changed.");
            Log.i("SENSOR", "  Light: " + event.values[0]);

            if (event.values[0] >= 500) {
                am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);  //벨소리 모드
                Toast.makeText(getApplicationContext(), "소리모드입니다", Toast.LENGTH_SHORT).show();
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }

    private class proximityListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {  // 방향 센서 값이 바뀔때마다 호출됨
            proximity.setText(Float.toString(event.values[0]));
            Log.i("SENSOR", "Proximity changed.");
            Log.i("SENSOR", "  Proximity: " + event.values[0] + "distance");

            if (event.values[0] == 0) {
                am.setRingerMode(AudioManager.RINGER_MODE_SILENT);  //무음 모드
                Toast.makeText(getApplicationContext(), "무음모드입니다", Toast.LENGTH_SHORT).show();
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }
}
