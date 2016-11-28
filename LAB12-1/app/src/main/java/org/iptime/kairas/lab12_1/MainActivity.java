package org.iptime.kairas.lab12_1;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity implements View.OnClickListener {

    private static final int MY_PERMISSIONS_REEQUEST_CALL_PHONE = 1;
    private Button mCall;
    private Button mDial;
    private Button mSendMessage;
    private EditText mEditNumber;
    private EditText mEditMsg;
    private String mNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCall = (Button) findViewById(R.id.btnCall);
        mDial = (Button) findViewById(R.id.btnDial);
        mSendMessage = (Button) findViewById(R.id.btnMessage);
        mEditNumber = (EditText) findViewById(R.id.edtNumber);
        mEditMsg = (EditText) findViewById(R.id.msg);
        mCall.setOnClickListener(this);
        mDial.setOnClickListener(this);
        mSendMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        mNum =mEditNumber.getText().toString();
        String tel = "tel:" + mNum;
        switch (v.getId()) {
            case R.id.btnCall:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REEQUEST_CALL_PHONE);
                    }
                } else {
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(tel)));
                }
                break;

            case R.id.btnDial:

                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(tel)));
                break;

            case R.id.btnMessage:

                mNum = mEditNumber.getText().toString();
                String txt = mEditMsg.getText().toString();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.putExtra("address", mNum);
                intent.putExtra("sms_body", txt);
                intent.setType("vnd.android-dir/mms-sms");
                startActivity(intent);

                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REEQUEST_CALL_PHONE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    } else {

                    }
                } else {

                }
                return;
            }
        }
    }
}
