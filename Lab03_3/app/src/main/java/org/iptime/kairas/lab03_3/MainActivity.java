package org.iptime.kairas.lab03_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PasswordTransformationMethod pwd = new PasswordTransformationMethod();
        editText = (EditText) findViewById(R.id.password);
        editText.setTransformationMethod(pwd);

        CheckBox checkbox = (CheckBox) findViewById(R.id.checkBox);
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((((CheckBox) view).isChecked())) {
                    Toast.makeText(getApplicationContext(), "자동 로르인 설정", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "자동 로르인 해제", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void onClickLogin(View v) {
        Toast.makeText(getApplicationContext(), "로그인", Toast.LENGTH_SHORT).show();
    }
    public void onClickJoin(View v) {
        Toast.makeText(getApplicationContext(), "회원가입", Toast.LENGTH_SHORT).show();
    }
}
