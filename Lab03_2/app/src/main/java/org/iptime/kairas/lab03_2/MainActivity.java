package org.iptime.kairas.lab03_2;

import android.graphics.Color;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textview);
    }

    public void onClick(View v) {
        Random num = new Random();
        int red = num.nextInt(255);
        int green = num.nextInt(255);
        int blue = num.nextInt(255);

        textView.setBackgroundColor(Color.rgb(red, green, blue));
    }
}