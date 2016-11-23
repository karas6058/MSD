package org.iptime.kairas.hw10;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Calculator extends Activity {
    private boolean flag =  false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

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
        final Button dot = (Button) findViewById(R.id.dot);

        final Button divide = (Button) findViewById(R.id.divide);
        final Button mult = (Button) findViewById(R.id.mult);
        final Button minus = (Button) findViewById(R.id.minus);
        final Button plus = (Button) findViewById(R.id.plus);

        final Button equal = (Button) findViewById(R.id.equal);

        final Button clear = (Button) findViewById(R.id.clear);

        final EditText edittext1 = (EditText) findViewById(R.id.edittext1);
        final EditText edittext2 = (EditText) findViewById(R.id.edittext2);

        n1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext2.setText(edittext2.getText() + "1");
            }
        });

        n2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext2.setText(edittext2.getText() + "2");
            }
        });

        n3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext2.setText(edittext2.getText() + "3");
            }
        });

        n4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext2.setText(edittext2.getText() + "4");
            }
        });

        n5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext2.setText(edittext2.getText() + "5");
            }
        });

        n6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext2.setText(edittext2.getText() + "6");
            }
        });

        n7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext2.setText(edittext2.getText() + "7");
            }
        });

        n8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext2.setText(edittext2.getText() + "8");
            }
        });

        n9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext2.setText(edittext2.getText() + "9");
            }
        });

        n0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext2.setText(edittext2.getText() + "0");
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext2.setText(edittext2.getText() + ".");
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag)
                    edittext1.setText(edittext2.getText() + "+");
                else
                    edittext1.setText(edittext1.getText() + "" + edittext2.getText() + "+");

                edittext2.setText("");

                flag = false;
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag)
                    edittext1.setText(edittext2.getText() + "-");
                else
                    edittext1.setText(edittext1.getText() + "" + edittext2.getText() + "-");

                edittext2.setText("");

                flag = false;
            }
        });

        mult.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag)
                    edittext1.setText(edittext2.getText() + "*");
                else
                    edittext1.setText(edittext1.getText() + "" + edittext2.getText() + "*");

                edittext2.setText("");

                flag = false;
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag)
                    edittext1.setText(edittext2.getText() + "/");
                else
                    edittext1.setText(edittext1.getText() + "" + edittext2.getText() + "/");

                edittext2.setText("");

                flag = false;
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                flag = true;
                String str = edittext1.getText() + "" + edittext2.getText();
                edittext1.setText(str + "=");

                Expression calc = new ExpressionBuilder(str).build();
                double result = calc.evaluate();

                edittext2.setText(Double.toString(result));
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edittext1.setText("");
                edittext2.setText("");
            }
        });
    }
}