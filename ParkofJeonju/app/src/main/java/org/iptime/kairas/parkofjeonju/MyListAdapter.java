package org.iptime.kairas.parkofjeonju;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by kaira on 2016-11-09.
 */
public class MyListAdapter extends BaseAdapter {
    private Context maincon;
    private LayoutInflater Inflater;
    private ArrayList<MyItem> arSrc;
    int layout;

    public MyListAdapter(Context context, int alayout, ArrayList<MyItem> aarSrc) {
        maincon = context;
        Inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        arSrc = aarSrc;
        layout = alayout;

    }
    public int getCount() {
        return arSrc.size();
    }
    public String getItem(int position) {
        return arSrc.get(position).name;
    }
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        if (convertView == null) {
            convertView = Inflater.inflate(layout, parent, false);
        }
        ImageView img = (ImageView) convertView.findViewById(R.id.image);
        img.setImageResource(arSrc.get(position).image);

        TextView txt = (TextView) convertView.findViewById(R.id.title);
        txt.setText(arSrc.get(position).name);

        Button btn = (Button) convertView.findViewById(R.id.btn);
        btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                String str = arSrc.get(pos).name + "공원을 호출합니다.";
                Toast.makeText(maincon, str, Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
