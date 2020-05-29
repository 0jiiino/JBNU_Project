package com.jbnu.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class YellowBelt extends AppCompatActivity {

    Button btn1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yellow_belt);

        SharedPreferences pref1 = getSharedPreferences("pref1", Activity.MODE_PRIVATE); // Shared Preference를 불러옵니다.
        final EditText edit1 = (EditText)findViewById(R.id.editText01);
        final EditText edit2 = (EditText)findViewById(R.id.editText02);
        final EditText edit3 = (EditText)findViewById(R.id.editText03);
        final EditText edit4 = (EditText)findViewById(R.id.editText04);
        final EditText edit5 = (EditText)findViewById(R.id.editText05);
        final EditText edit6 = (EditText)findViewById(R.id.editText06);

        CheckBox check1 = (CheckBox)findViewById(R.id.checkbox01);
        CheckBox check2 = (CheckBox)findViewById(R.id.checkbox02);
        CheckBox check3 = (CheckBox)findViewById(R.id.checkbox03);
        CheckBox check4 = (CheckBox)findViewById(R.id.checkbox04);
        CheckBox check5 = (CheckBox)findViewById(R.id.checkbox05);
        CheckBox check6 = (CheckBox)findViewById(R.id.checkbox06);
        CheckBox check7 = (CheckBox)findViewById(R.id.checkbox07);

        // 저장된 값들을 불러옵니다.
        String text1 = pref1.getString("editText1", "");
        String text2 = pref1.getString("editText2", "");
        String text3 = pref1.getString("editText3", "");
        String text4 = pref1.getString("editText4", "");
        String text5 = pref1.getString("editText5", "");
        String text6 = pref1.getString("editText6", "");

        Boolean chk1 = pref1.getBoolean("check1", false);
        Boolean chk2 = pref1.getBoolean("check2", false);
        Boolean chk3 = pref1.getBoolean("check3", false);
        Boolean chk4 = pref1.getBoolean("check4", false);
        Boolean chk5 = pref1.getBoolean("check5", false);
        Boolean chk6 = pref1.getBoolean("check6", false);
        Boolean chk7 = pref1.getBoolean("check7", false);

        edit1.setText(text1);
        edit2.setText(text2);
        edit3.setText(text3);
        edit4.setText(text4);
        edit5.setText(text5);
        edit6.setText(text6);

        check1.setChecked(chk1);
        check2.setChecked(chk2);
        check3.setChecked(chk3);
        check4.setChecked(chk4);
        check5.setChecked(chk5);
        check6.setChecked(chk6);
        check7.setChecked(chk7);

        btn1 = (Button) findViewById(R.id.plus);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (edit1.getText().toString().equals("") || edit2.getText().toString().equals("") ||
                        edit3.getText().toString().equals("") || edit4.getText().toString().equals("")
                        || edit6.getText().toString().equals("")) {
                    Toast.makeText(YellowBelt.this, "값이 없습니다.",
                            Toast.LENGTH_LONG).show();
                } else {
                    String s1 = edit1.getText().toString();
                    String s2 = edit2.getText().toString();
                    String s3 = edit3.getText().toString();
                    String s4 = edit4.getText().toString();
                    String s6 = edit6.getText().toString();
                    int result = Integer.parseInt(s1) + Integer.parseInt(s2) +
                            Integer.parseInt(s3) + Integer.parseInt(s4)+ Integer.parseInt(s6);
                    edit5.setText(Integer.toString(result));

                }
            }
        });
        Button btn4 = (Button)findViewById(R.id.button04);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BookList.class);
                startActivity(intent);
            }
        });
        Button btn6 = (Button)findViewById(R.id.button06);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchBook.class);
                startActivity(intent);
            }
        });


    }

    public void onStop(){ // 어플리케이션이 화면에서 사라질때
        super.onStop();
        SharedPreferences pref1 = getSharedPreferences("pref1", Activity.MODE_PRIVATE); // UI 상태를 저장합니다.
        SharedPreferences.Editor editor = pref1.edit(); // Editor를 불러옵니다.

        EditText edit1 = (EditText)findViewById(R.id.editText01);
        EditText edit2 = (EditText)findViewById(R.id.editText02);
        EditText edit3 = (EditText)findViewById(R.id.editText03);
        EditText edit4 = (EditText)findViewById(R.id.editText04);
        EditText edit5 = (EditText)findViewById(R.id.editText05);
        EditText edit6 = (EditText)findViewById(R.id.editText06);

        CheckBox check1 = (CheckBox)findViewById(R.id.checkbox01);
        CheckBox check2 = (CheckBox)findViewById(R.id.checkbox02);
        CheckBox check3 = (CheckBox)findViewById(R.id.checkbox03);
        CheckBox check4 = (CheckBox)findViewById(R.id.checkbox04);
        CheckBox check5 = (CheckBox)findViewById(R.id.checkbox05);
        CheckBox check6 = (CheckBox)findViewById(R.id.checkbox06);
        CheckBox check7 = (CheckBox)findViewById(R.id.checkbox07);

        // 저장할 값들을 입력합니다.
        editor.putString("editText1", edit1.getText().toString());
        editor.putString("editText2", edit2.getText().toString());
        editor.putString("editText3", edit3.getText().toString());
        editor.putString("editText4", edit4.getText().toString());
        editor.putString("editText5", edit5.getText().toString());
        editor.putString("editText6", edit6.getText().toString());

        editor.putBoolean("check1", check1.isChecked());
        editor.putBoolean("check2", check2.isChecked());
        editor.putBoolean("check3", check3.isChecked());
        editor.putBoolean("check4", check4.isChecked());
        editor.putBoolean("check5", check5.isChecked());
        editor.putBoolean("check6", check6.isChecked());
        editor.putBoolean("check7", check7.isChecked());

        editor.commit(); // 저장합니다.
    }

    public void popup03(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("외국어").setMessage("성적표 파일스캔(토익은 정규토익만)-오아시스-큰사람프로젝트 관리-포인트 신청(성적표 첨부)");

        AlertDialog alertDialog = builder.create();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alertDialog.getWindow().getAttributes());
        lp.width=400;
        lp.height=500;
        Window window = alertDialog.getWindow();
        window.setAttributes(lp);

        alertDialog.show();
    }

    public void popup02(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("직무검사").setMessage("오아시스-진로적성검사 예약-상담후 취업지원과에서 포인트 입력[인터넷검사시 결과지 오아시스로 신청");

        AlertDialog alertDialog = builder.create();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alertDialog.getWindow().getAttributes());
        lp.width=400;
        lp.height=500;
        Window window = alertDialog.getWindow();
        window.setAttributes(lp);

        alertDialog.show();
    }

    public void popup01(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("진로계획서").setMessage("오아시스-큰사람프로젝트 관리-진로계획서 작성-검사후 검사결과 다운(큰사람 프로젝트 관리에서 파일 첨부하여 포인트 신청");

        AlertDialog alertDialog = builder.create();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alertDialog.getWindow().getAttributes());
        lp.width=400;
        lp.height=600;
        Window window = alertDialog.getWindow();
        window.setAttributes(lp);

        alertDialog.show();
    }

    public void popup05(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("채용트렌드특강").setMessage("취업지원과 프로그램 개설시 개별신청(홈페이지로 사전공지)-수료자에 한해 취업지원과에서 포인트 입력");

        AlertDialog alertDialog = builder.create();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alertDialog.getWindow().getAttributes());
        lp.width=400;
        lp.height=500;
        Window window = alertDialog.getWindow();
        window.setAttributes(lp);

        alertDialog.show();
    }
}
