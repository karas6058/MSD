package org.iptime.kairas.hw11;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    public static boolean onPass;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener buttonListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent;

                switch (v.getId()) {
                    case R.id.calculator:
                        intent = new Intent(getApplicationContext(), Calculator.class);
                        startActivity(intent);
                        break;

                    case R.id.convertor:
                        intent = new Intent(getApplicationContext(), Convertor.class);
                        startActivity(intent);
                        break;
//                        case R.id.btnMap:
//                        intent = new Intent(getApplicationContext(), MapsActivity.class);
//                        startActivity(intent);
//                        break;

                    case R.id.memo:
                        intent = new Intent(getApplicationContext(), Memo.class);
                        startActivity(intent);
                        break;

                    case R.id.music:
                        intent = new Intent(getApplicationContext(), Music.class);
                        startActivity(intent);
                        break;

                    case R.id.phonebook:
                        intent = new Intent(getApplicationContext(), Phonebook.class);
                        startActivity(intent);
                        break;

                    case R.id.setting:
                        intent = new Intent(getApplicationContext(), Setting.class);
                        startActivity(intent);
                        break;

                    case R.id.web:
                        intent = new Intent(getApplicationContext(), Web.class);
                        startActivity(intent);
                        break;

                }

                if(onPass) {
                    startActivity(new Intent(getApplicationContext(), LoadingActivity.class));
                }

            }
        };

        findViewById(R.id.calculator).setOnClickListener(buttonListener);
        findViewById(R.id.convertor).setOnClickListener(buttonListener);
        findViewById(R.id.btnMap).setOnClickListener(buttonListener);
        findViewById(R.id.memo).setOnClickListener(buttonListener);
        findViewById(R.id.music).setOnClickListener(buttonListener);
        findViewById(R.id.phonebook).setOnClickListener(buttonListener);
        findViewById(R.id.setting).setOnClickListener(buttonListener);
        findViewById(R.id.web).setOnClickListener(buttonListener);

    }
}