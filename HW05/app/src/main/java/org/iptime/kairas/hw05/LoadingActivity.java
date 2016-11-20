package org.iptime.kairas.hw05;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class LoadingActivity extends Activity {
    public static String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setText(text);

        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0,3000);
    }
}
