package org.iptime.kairas.lab11;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CALENDAR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        String[] projection = {CalendarContract.Events._ID, CalendarContract.Events.TITLE};

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CALENDAR)) {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CALENDAR}, MY_PERMISSIONS_REQUEST_READ_CALENDAR);
            } else {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CALENDAR}, MY_PERMISSIONS_REQUEST_READ_CALENDAR);
            }
        } else {
            Cursor cursor = getContentResolver().query(CalendarContract.Events.CONTENT_URI, projection, null, null, null);

            int nameIdx = cursor.getColumnIndexOrThrow(CalendarContract.Events.TITLE);
            int idIdx = cursor.getColumnIndexOrThrow(CalendarContract.Events._ID);

            String[] result = new String[cursor.getCount()];

            while (cursor.moveToNext()) {
                String name = cursor.getString(nameIdx);
                String id = cursor.getString(idIdx);
                result[cursor.getPosition()] = name + "(" + id + ")";
                Toast.makeText(this, "이벤트 이름 : " + name + " 등록 번호" + id, Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CALENDAR: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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
