package org.iptime.kairas83.lab05;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by kairas on 2016-10-10.
 */

public class LevelActivity extends AppCompatActivity {
    RadioGroup rg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level);

        rg = (RadioGroup) findViewById(R.id.RadioGroup1);

    }
    public void onBackPressed() {
        super.onBackPressed();
        RadioButton rd = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
        String LevelType = rd.getText().toString();

        Intent intent = new Intent(LevelActivity.this, MainActivity.class);
        intent.putExtra("Level", LevelType);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
