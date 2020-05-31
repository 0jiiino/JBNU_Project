package com.jbnu.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class WhiteBelt extends AppCompatActivity {
    /** Called when the activity is first created. */

    Button btn1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white_belt);

        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE); // Shared Preference를 불러옵니다.
        final EditText edit1 = (EditText)findViewById(R.id.editText01);
        final EditText edit2 = (EditText)findViewById(R.id.editText02);
        final EditText edit3 = (EditText)findViewById(R.id.editText03);
        final EditText edit4 = (EditText)findViewById(R.id.editText04);
        final EditText edit5 = (EditText)findViewById(R.id.editText05);

        CheckBox check1 = (CheckBox)findViewById(R.id.checkbox01);
        CheckBox check2 = (CheckBox)findViewById(R.id.checkbox02);
        CheckBox check3 = (CheckBox)findViewById(R.id.checkbox03);
        CheckBox check4 = (CheckBox)findViewById(R.id.checkbox04);
        CheckBox check5 = (CheckBox)findViewById(R.id.checkbox05);
        CheckBox check6 = (CheckBox)findViewById(R.id.checkbox06);

        // 저장된 값들을 불러옵니다.
        String text1 = pref.getString("editText1", "");
        String text2 = pref.getString("editText2", "");
        String text3 = pref.getString("editText3", "");
        String text4 = pref.getString("editText4", "");
        String text5 = pref.getString("editText5", "");

        Boolean chk1 = pref.getBoolean("check1", false);
        Boolean chk2 = pref.getBoolean("check2", false);
        Boolean chk3 = pref.getBoolean("check3", false);
        Boolean chk4 = pref.getBoolean("check4", false);
        Boolean chk5 = pref.getBoolean("check5", false);
        Boolean chk6 = pref.getBoolean("check6", false);

        edit1.setText(text1);
        edit2.setText(text2);
        edit3.setText(text3);
        edit4.setText(text4);
        edit5.setText(text5);

        check1.setChecked(chk1);
        check2.setChecked(chk2);
        check3.setChecked(chk3);
        check4.setChecked(chk4);
        check5.setChecked(chk5);
        check6.setChecked(chk6);

        btn1 = (Button) findViewById(R.id.plus);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (edit1.getText().toString().equals("") || edit2.getText().toString().equals("") ||
                        edit3.getText().toString().equals("") || edit4.getText().toString().equals("")) {
                    Toast.makeText(WhiteBelt.this, "빈칸에 0 입력해주세요",
                            Toast.LENGTH_LONG).show();
                } else {
                    String s1 = edit1.getText().toString();
                    String s2 = edit2.getText().toString();
                    String s3 = edit3.getText().toString();
                    String s4 = edit4.getText().toString();
                    int result = Integer.parseInt(s1) + Integer.parseInt(s2) +
                            Integer.parseInt(s3) + Integer.parseInt(s4);
                    edit5.setText(Integer.toString(result));

                }
            }
        });




        Button btn2 = (Button)findViewById(R.id.button04);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BookList.class);
                startActivity(intent);
            }
        });
        Button btn3 = (Button)findViewById(R.id.search);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchBook.class);
                startActivity(intent);
            }
        });

    }

    public void onStop(){ // 어플리케이션이 화면에서 사라질때
        super.onStop();
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE); // UI 상태를 저장합니다.
        SharedPreferences.Editor editor = pref.edit(); // Editor를 불러옵니다.

        EditText edit1 = (EditText)findViewById(R.id.editText01);
        EditText edit2 = (EditText)findViewById(R.id.editText02);
        EditText edit3 = (EditText)findViewById(R.id.editText03);
        EditText edit4 = (EditText)findViewById(R.id.editText04);
        EditText edit5 = (EditText)findViewById(R.id.editText05);

        CheckBox check1 = (CheckBox)findViewById(R.id.checkbox01);
        CheckBox check2 = (CheckBox)findViewById(R.id.checkbox02);
        CheckBox check3 = (CheckBox)findViewById(R.id.checkbox03);
        CheckBox check4 = (CheckBox)findViewById(R.id.checkbox04);
        CheckBox check5 = (CheckBox)findViewById(R.id.checkbox05);
        CheckBox check6 = (CheckBox)findViewById(R.id.checkbox06);

        // 저장할 값들을 입력합니다.
        editor.putString("editText1", edit1.getText().toString());
        editor.putString("editText2", edit2.getText().toString());
        editor.putString("editText3", edit3.getText().toString());
        editor.putString("editText4", edit4.getText().toString());
        editor.putString("editText5", edit5.getText().toString());

        editor.putBoolean("check1", check1.isChecked());
        editor.putBoolean("check2", check2.isChecked());
        editor.putBoolean("check3", check3.isChecked());
        editor.putBoolean("check4", check4.isChecked());
        editor.putBoolean("check5", check5.isChecked());
        editor.putBoolean("check6", check6.isChecked());

        editor.commit(); // 저장합니다.
    }


    public void popup(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("대학생활계획서").setMessage("오아시스-큰사람프로젝트 관리-대학생활 계획서-검사 후 결과 다운로드");

        AlertDialog alertDialog = builder.create();
        
       WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
       lp.copyFrom(alertDialog.getWindow().getAttributes());
       lp.width=400;
       lp.height=400;
       Window window = alertDialog.getWindow();
       window.setAttributes(lp);
        
        alertDialog.show();
    }

    public void popup1(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("인성검사").setMessage("행복드림센터 방문검사\n검사 후 행복드림센터에서 포인트 입력");

        AlertDialog alertDialog = builder.create();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alertDialog.getWindow().getAttributes());
        lp.width=400;
        lp.height=400;
        Window window = alertDialog.getWindow();
        window.setAttributes(lp);

        alertDialog.show();
    }

    public void popup3(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("적성검사").setMessage("오아시스-진로적성검사 예약-대학생활-검사 후 취업지원과에서 포인트 입력");

        AlertDialog alertDialog = builder.create();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alertDialog.getWindow().getAttributes());
        lp.width=400;
        lp.height=400;
        Window window = alertDialog.getWindow();
        window.setAttributes(lp);

        alertDialog.show();
    }

    public void popup2(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("외국어(모의토익 가능)").setMessage("토익 성적표 파일스캔-오아시스-큰사람프로젝트 관리-포인트 신청(파일 첨부)");

        AlertDialog alertDialog = builder.create();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alertDialog.getWindow().getAttributes());
        lp.width=400;
        lp.height=400;
        Window window = alertDialog.getWindow();
        window.setAttributes(lp);

        alertDialog.show();
    }
}
