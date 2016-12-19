package org.iptime.kairas.hw06;

import android.Manifest;
import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Phonebook extends Activity {

    EditText edit_name, edit_tel;

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);

        if (ContextCompat.checkSelfPermission(Phonebook.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Phonebook.this, Manifest.permission.READ_CONTACTS)) {
                ActivityCompat.requestPermissions(Phonebook.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            } else {
                ActivityCompat.requestPermissions(Phonebook.this, new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        }

        edit_name = (EditText) findViewById(R.id.name);
        edit_tel = (EditText) findViewById(R.id.tel);
    }

    public void onRequestPermissionsResult(int requestCode, String permission[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }
        }
    }

    public void load(View view) {
        TextView tv = (TextView) findViewById(R.id.tv);

        Cursor c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        if (c.getCount() <  1) {
            tv.setText("연락처가 없습니다");
            Toast.makeText(this, "연락처가 없습니다", Toast.LENGTH_LONG).show();
            return;
        }


        String str = "";
        c.moveToFirst();

        do {
            String name = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            str += "이름: " + name + "/폰번호: " + phoneNumber + "\n";
        } while (c.moveToNext());

        tv.setText(str);
    }

    public void insert(View view) {
        String name = edit_name.getText().toString();
        String tel = edit_tel.getText().toString();

        try {
            ArrayList<ContentProviderOperation> list = new ArrayList<>();
            list.add(
                    ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                            .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                            .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                            .build()
            );

            list.add(
                    ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                            .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)   //이름
                            .build()
            );

            list.add(
                    ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                            .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, tel)           //전화번호
                            .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)   //번호타입(Type_Mobile : 모바일)
                            .build()
            );

            getApplicationContext().getContentResolver().applyBatch(ContactsContract.AUTHORITY, list);  //주소록추가
            list.clear();   //리스트 초기화

            Toast.makeText(this, "연락처가 추가되었습니다", Toast.LENGTH_LONG).show();
            load(view);

        } catch(Exception e) {
            Toast.makeText(this, "연락처가 추가가 실패하였습니다", Toast.LENGTH_LONG).show();
        }
    }

    public void delete(View view) {
        String name = edit_name.getText().toString();

        try {
            getApplicationContext().getContentResolver().delete(ContactsContract.RawContacts.CONTENT_URI, ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY + "=" + name, null);
            Toast.makeText(this, "연락처가 삭제되었습니다", Toast.LENGTH_LONG).show();
            load(view);
        } catch (Exception e) {
            Toast.makeText(this, "연락처 삭제가 실패하였습니다", Toast.LENGTH_LONG).show();
        }

    }
}