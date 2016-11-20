package org.iptime.kairas.hw09;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Phonebook extends AppCompatActivity {

    DBHelper helper;
    SQLiteDatabase db;
    EditText edit_name, edit_tel;
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);
        helper = new DBHelper(this);
        listView = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);

        listView.setAdapter(adapter);

        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = helper.getReadableDatabase();
        }

        edit_name = (EditText) findViewById(R.id.name);
        edit_tel = (EditText) findViewById(R.id.tel);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog alert = new AlertDialog.Builder(getApplicationContext()).create();
                alert.setTitle("주소록");
                alert.setButton(AlertDialog.BUTTON_POSITIVE, "확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });

                String name = parent.getAdapter().getItem(position).toString();
                Cursor cursor = db.rawQuery("SELECT name, tel FROM contacts WHERE name = '" +  name + "';", null);
                String tel = "";
                while (cursor.moveToNext()) {
                    tel = cursor.getString(1);
                }
                alert.setMessage(name + " / " + tel);
                alert.show();
            }

        });
    }
    public void insert(View target) {
        String name = edit_name.getText().toString();
        String tel = edit_tel.getText().toString();
        db.execSQL("INSERT INTO contacts VALUES (null, '" + name + "','" + tel + "');");
        Toast.makeText(getApplicationContext(), "추가됨", Toast.LENGTH_SHORT).show();

        edit_name.setText("");
        edit_tel.setText("");

        adapter.add(name.toString());

    }

    public void search(View target) {
        String name = edit_name.getText().toString();
        Cursor cursor;
        cursor = db.rawQuery("SELECT name, tel FROM contacts WHERE name = '" + name + "';", null);

        while (cursor.moveToNext()) {
            String tel = cursor.getString(1);
            edit_tel.setText(tel);
        }
    }

}

