package com.examples.ss3_2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TextView;

import static java.lang.Double.parseDouble;

public class MainActivity extends AppCompatActivity {
    TabHost tabHost;

    Button btn1, btn2, btn3;

    EditText editText1, editText2, editText3;

    TextView textView1, textView2;

    double Official = 3.305785;

    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("각종 계산기");

        tabHost = (TabHost) findViewById(R.id.tabhost1);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("1").setContent(R.id.tab1).setIndicator("BMI 계산기");

        TabHost.TabSpec tab2 = tabHost.newTabSpec("2").setContent(R.id.tab2).setIndicator("면적 계산기");

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);

        init();

    }

    void init(){

        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);

        textView1 = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);

        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button3);
        btn3 = (Button) findViewById(R.id.button4);

        frameLayout = (FrameLayout) findViewById(android.R.id.tabcontent);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BMIcheck();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToMeter();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToPyeong();
            }
        });

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                switch (s) {
                    case "1":
                        frameLayout.setBackgroundColor(Color.parseColor("#FFFFbb33"));
                        break;
                    case "2":
                        frameLayout.setBackgroundColor(Color.parseColor("#ff00ddff"));
                        break;

                }

            }
        });
    }

    void BMIcheck(){
        String s1, s2;

        s1 = editText1.getText().toString();
        s2 = editText2.getText().toString();

        int kilo = (int)Math.pow(parseDouble(s2)/100,2);

        double result = parseDouble(s1)/kilo;

        if(result < 18.5){
            textView1.setTextColor(Color.RED);
            textView1.setText("체중부족!!!!");
        }else if(result <22.9){
            textView1.setTextColor(Color.GREEN);
            textView1.setText("정상!!!!");
        }else if(result <24.9){
            textView1.setTextColor(Color.RED);
            textView1.setText("과체중!!!!");
        }else if(result >25){
            textView1.setTextColor(Color.RED);
            textView1.setText("비만입니다!!!");
        }
    }

    void changeToMeter(){
        String s1 = editText3.getText().toString();
        double result = Double.parseDouble(s1)*Official;
        textView2.setText(result + " 제곱미터");

    }

    void changeToPyeong(){
        String s1 = editText3.getText().toString();
        double result = Math.round(Double.parseDouble(s1)/Official*100)/100.0;
        textView2.setText(result+" 평");
    }
}
