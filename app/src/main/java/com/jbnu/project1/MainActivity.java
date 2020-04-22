package com.jbnu.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jbnu.project1.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button belt = (Button)findViewById(R.id.belt);
        belt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //벨트 체크리스트 클릭시 활성화
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        Button map = (Button)findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //편의 시설 위치 클릭시 활성화

            }
        });

    }
}
