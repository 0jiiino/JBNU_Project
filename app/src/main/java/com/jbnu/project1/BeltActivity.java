package com.jbnu.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BeltActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belt);

        Button btn_white = (Button)findViewById(R.id.btn_white);
        btn_white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //화이트 벨트 클릭시 활성화
                Intent intent = new Intent(getApplicationContext(),WhiteBelt.class);
                startActivity(intent);
            }
        });
        Button btn_yellow = (Button)findViewById(R.id.btn_yellow);
        btn_yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //옐로우 벨트 클릭시 활성화
                Intent intent = new Intent(getApplicationContext(),YellowBelt.class);
                startActivity(intent);
            }
        });
        Button btn_blue = (Button)findViewById(R.id.btn_blue);
        btn_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //옐로우 벨트 클릭시 활성화
                Intent intent = new Intent(getApplicationContext(),BlueBelt.class);
                startActivity(intent);
            }
        });
        Button btn_red = (Button)findViewById(R.id.btn_red);
        btn_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //옐로우 벨트 클릭시 활성화
                Intent intent = new Intent(getApplicationContext(),RedBelt.class);
                startActivity(intent);
            }
        });

    }
}
