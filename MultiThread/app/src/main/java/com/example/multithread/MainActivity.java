package com.example.multithread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = findViewById(R.id.change_text);
        final TextView tx = findViewById(R.id.textview);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemoThread thread1 = new DemoThread();
                Log.d("子线程号", "" + thread1.getId());
                try {
                    thread1.start();  //开始休眠5秒

                } catch (Exception e) {

                }
                Log.d("主线程号", "" + Thread.currentThread().getId());
                tx.setText("我是" + counter);
            }

        });
    }

        class DemoThread extends Thread {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {

                }
                counter++;

            }
        }
    }
