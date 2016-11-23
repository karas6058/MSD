package org.iptime.kairas.hw11;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Convertor extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convertor);

        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.change_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        final Button n1 = (Button) findViewById(R.id.n1);
        final Button n2 = (Button) findViewById(R.id.n2);
        final Button n3 = (Button) findViewById(R.id.n3);
        final Button n4 = (Button) findViewById(R.id.n4);
        final Button n5 = (Button) findViewById(R.id.n5);
        final Button n6 = (Button) findViewById(R.id.n6);
        final Button n7 = (Button) findViewById(R.id.n7);
        final Button n8 = (Button) findViewById(R.id.n8);
        final Button n9 = (Button) findViewById(R.id.n9);
        final Button n0 = (Button) findViewById(R.id.n0);
        final Button backspace = (Button) findViewById(R.id.backspace);
        final Button confirm = (Button) findViewById(R.id.confirm);

        final EditText editText1 = (EditText) findViewById(R.id.edittext1);
        final EditText editText2 = (EditText) findViewById(R.id.edittext2);

        n1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editText1.setText(editText1.getText() + "1");
            }
        });

        n2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editText1.setText(editText1.getText() + "2");
            }
        });

        n3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editText1.setText(editText1.getText() + "3");
            }
        });

        n4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editText1.setText(editText1.getText() + "4");
            }
        });

        n5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editText1.setText(editText1.getText() + "5");
            }
        });

        n6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editText1.setText(editText1.getText() + "6");
            }
        });

        n7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editText1.setText(editText1.getText() + "7");
            }
        });

        n8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editText1.setText(editText1.getText() + "8");
            }
        });

        n9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editText1.setText(editText1.getText() + "9");
            }
        });

        n0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editText1.setText(editText1.getText() + "0");
            }
        });

        backspace.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String array = editText1.getText().toString();
                if (array.length() != 0)
                    editText1.setText(array.substring(0, array.length()-1));
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (spinner1.getSelectedItem().toString()) {
                    case "cm":
                        switch (spinner2.getSelectedItem().toString()) {
                            case "cm":
                                editText2.setText(editText1.getText().toString());
                                break;
                            case "m":
                                editText2.setText(String.valueOf(Double.parseDouble(editText1.getText().toString()) / 100));
                                break;
                            case "km":
                                editText2.setText(String.valueOf(Double.parseDouble(editText1.getText().toString()) / 100 / 1000));
                                break;
                        }
                        break;

                    case "m":
                        switch (spinner2.getSelectedItem().toString()) {
                            case "cm":
                                editText2.setText(String.valueOf(Double.parseDouble(editText1.getText().toString()) * 100));
                                break;
                            case "m":
                                editText2.setText(editText1.getText().toString());
                                break;
                            case "km":
                                editText2.setText(String.valueOf(Double.parseDouble(editText1.getText().toString()) / 1000));
                                break;
                        }
                        break;

                    case "km":
                        switch (spinner2.getSelectedItem().toString()) {
                            case "cm":
                                editText2.setText(String.valueOf(Double.parseDouble(editText1.getText().toString()) * 100 * 1000));
                                break;
                            case "m":
                                editText2.setText(String.valueOf(Double.parseDouble(editText1.getText().toString()) * 1000));
                                break;
                            case "km":
                                editText2.setText(editText1.getText().toString());
                                break;
                        }
                        break;
                }
            }
        });
    }
}
