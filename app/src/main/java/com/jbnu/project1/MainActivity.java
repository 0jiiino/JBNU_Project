package com.jbnu.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button tip = (Button)findViewById(R.id.tip);
        tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //꿀팁 게시판 클릭시 활성화
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        Button map = (Button)findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //편의 시설 위치 클릭시 활성화
                Intent intent = new Intent(getApplicationContext(), Map.class);
                startActivity(intent);
            }
        });
        Button Belt = (Button)findViewById(R.id.belt);
        Belt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //벨트 클릭시 활성화
                Intent intent = new Intent(getApplicationContext(), BeltActivity.class);
                startActivity(intent);
            }
        });

        Button notice = (Button)findViewById(R.id.notice);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //편의 시설 위치 클릭시 활성화
                Intent intent = new Intent(getApplicationContext(), notice.class);
                startActivity(intent);
            }
        });

    }
}