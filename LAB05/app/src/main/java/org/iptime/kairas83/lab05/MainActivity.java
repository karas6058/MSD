package org.iptime.kairas83.lab05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String Levelm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myListener(View target) {
        Intent intent = new Intent(getApplicationContext(), IntroActivity.class);
        startActivity(intent);

    }
    public void myListener2(View target) {
        Intent intent = new Intent(getApplicationContext(), LevelActivity.class);
        startActivity(intent);

    }

    protected void onResume() {
        super.onResume();
        try {
            Intent intent = getIntent();
            Levelm = intent.getExtras().getString("Level");
            Toast.makeText(MainActivity.this, Levelm, Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {

        }
    }
}
