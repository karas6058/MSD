package org.iptime.kairas.parkofjeonju;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ParkListActivity extends AppCompatActivity {
    ArrayList<MyItem> arItem;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parklist);
        setTitle("공원 목록");

        arItem = new ArrayList<MyItem>();
        MyItem mi;
        mi = new MyItem(R.drawable.chamseam_park, "참새암 공원");
        arItem.add(mi);
        mi = new MyItem(R.drawable.daga_park, "다가 공원");
        arItem.add(mi);
        mi = new MyItem(R.drawable.dukjin_park, "덕진 공원");
        arItem.add(mi);
        mi = new MyItem(R.drawable.ecocity, "에코 시티");
        arItem.add(mi);
        mi = new MyItem(R.drawable.garyunsan_park, "가련산 공원");
        arItem.add(mi);
        mi = new MyItem(R.drawable.gijize, "진지제 공원");
        arItem.add(mi);

        MyListAdapter myListAdapter = new MyListAdapter(this, R.layout.list_content, arItem);

        ListView myList = (ListView) findViewById(R.id.list);
        myList.setAdapter(myListAdapter);
    }


}

class MyItem {
    MyItem(int aimage, String aName) {
        image = aimage;
        name = aName;
    }
    int image;
    String name;
}