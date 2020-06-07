package com.jbnu.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class BlueBelt extends AppCompatActivity {

    Button btn1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_belt);

        SharedPreferences pref2 = getSharedPreferences("pref2", Activity.MODE_PRIVATE); // Shared Preference를 불러옵니다.
        final EditText edit1 = (EditText)findViewById(R.id.editText01);
        final EditText edit2 = (EditText)findViewById(R.id.editText02);
        final EditText edit3 = (EditText)findViewById(R.id.editText03);
        final EditText edit4 = (EditText)findViewById(R.id.editText04);
        final EditText edit5 = (EditText)findViewById(R.id.editText05);
        final EditText edit6 = (EditText)findViewById(R.id.editText06);
        final EditText edit7 = (EditText)findViewById(R.id.editText07);
        final EditText edit8 = (EditText)findViewById(R.id.editText08);
        final EditText edit9 = (EditText)findViewById(R.id.editText09);
        final EditText edit20 = (EditText)findViewById(R.id.editText20);
        final EditText edit21 = (EditText)findViewById(R.id.editText21);
        final EditText edit22 = (EditText)findViewById(R.id.editText22);
        final EditText edit23 = (EditText)findViewById(R.id.editText23);
        final EditText edit24 = (EditText)findViewById(R.id.editText24);
        final EditText edit25 = (EditText)findViewById(R.id.editText25);
        final EditText edit26 = (EditText)findViewById(R.id.editText26);

        CheckBox check1 = (CheckBox)findViewById(R.id.checkbox01);
        CheckBox check2 = (CheckBox)findViewById(R.id.checkbox02);
        CheckBox check3 = (CheckBox)findViewById(R.id.checkbox03);
        CheckBox check4 = (CheckBox)findViewById(R.id.checkbox04);
        CheckBox check5 = (CheckBox)findViewById(R.id.checkbox05);
        CheckBox check6 = (CheckBox)findViewById(R.id.checkbox06);
        CheckBox check7 = (CheckBox)findViewById(R.id.checkbox07);
        CheckBox check8 = (CheckBox)findViewById(R.id.checkbox08);
        CheckBox check9 = (CheckBox)findViewById(R.id.checkbox09);
        CheckBox check10 = (CheckBox)findViewById(R.id.checkbox10);
        CheckBox check20 = (CheckBox)findViewById(R.id.checkbox20);
        CheckBox check21 = (CheckBox)findViewById(R.id.checkbox21);
        CheckBox check22 = (CheckBox)findViewById(R.id.checkbox22);
        CheckBox check23 = (CheckBox)findViewById(R.id.checkbox23);
        CheckBox check24 = (CheckBox)findViewById(R.id.checkbox24);
        CheckBox check25 = (CheckBox)findViewById(R.id.checkbox25);
        CheckBox check26 = (CheckBox)findViewById(R.id.checkbox26);

        // 저장된 값들을 불러옵니다.
        String text1 = pref2.getString("editText1", "");
        String text2 = pref2.getString("editText2", "");
        String text3 = pref2.getString("editText3", "");
        String text4 = pref2.getString("editText4", "");
        String text5 = pref2.getString("editText5", "");
        String text6 = pref2.getString("editText6", "");
        String text7 = pref2.getString("editText7", "");
        String text8 = pref2.getString("editText8", "");
        String text9 = pref2.getString("editText9", "");
        String text20 = pref2.getString("editText20", "");
        String text21 = pref2.getString("editText21", "");
        String text22 = pref2.getString("editText22", "");
        String text23 = pref2.getString("editText23", "");
        String text24 = pref2.getString("editText24", "");
        String text25 = pref2.getString("editText25", "");
        String text26 = pref2.getString("editText26", "");

        Boolean chk1 = pref2.getBoolean("check1", false);
        Boolean chk2 = pref2.getBoolean("check2", false);
        Boolean chk3 = pref2.getBoolean("check3", false);
        Boolean chk4 = pref2.getBoolean("check4", false);
        Boolean chk5 = pref2.getBoolean("check5", false);
        Boolean chk6 = pref2.getBoolean("check6", false);
        Boolean chk7 = pref2.getBoolean("check7", false);
        Boolean chk8 = pref2.getBoolean("check8", false);
        Boolean chk9 = pref2.getBoolean("check9", false);
        Boolean chk10 = pref2.getBoolean("check10", false);
        Boolean chk20 = pref2.getBoolean("check20", false);
        Boolean chk21 = pref2.getBoolean("check21", false);
        Boolean chk22 = pref2.getBoolean("check22", false);
        Boolean chk23 = pref2.getBoolean("check23", false);
        Boolean chk24 = pref2.getBoolean("check24", false);
        Boolean chk25 = pref2.getBoolean("check25", false);
        Boolean chk26 = pref2.getBoolean("check26", false);

        edit1.setText(text1);
        edit2.setText(text2);
        edit3.setText(text3);
        edit4.setText(text4);
        edit5.setText(text5);
        edit6.setText(text6);
        edit7.setText(text7);
        edit8.setText(text8);
        edit9.setText(text9);
        edit20.setText(text20);
        edit21.setText(text21);
        edit22.setText(text22);
        edit23.setText(text23);
        edit24.setText(text24);
        edit25.setText(text25);
        edit26.setText(text26);

        check1.setChecked(chk1);
        check2.setChecked(chk2);
        check3.setChecked(chk3);
        check4.setChecked(chk4);
        check5.setChecked(chk5);
        check6.setChecked(chk6);
        check7.setChecked(chk7);
        check8.setChecked(chk8);
        check9.setChecked(chk9);
        check10.setChecked(chk10);
        check20.setChecked(chk20);
        check21.setChecked(chk21);
        check22.setChecked(chk22);
        check23.setChecked(chk23);
        check24.setChecked(chk24);
        check25.setChecked(chk25);
        check26.setChecked(chk26);

        btn1 = (Button) findViewById(R.id.plus);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (edit1.getText().toString().equals("") || edit2.getText().toString().equals("") ||
                        edit3.getText().toString().equals("") || edit4.getText().toString().equals("")
                        || edit6.getText().toString().equals("") || edit7.getText().toString().equals("") || edit8.getText().toString().equals("")
                        || edit9.getText().toString().equals("")|| edit20.getText().toString().equals("")|| edit21.getText().toString().equals("")
                        || edit22.getText().toString().equals("")|| edit23.getText().toString().equals("")|| edit24.getText().toString().equals("")
                        || edit25.getText().toString().equals("")|| edit26.getText().toString().equals("")) {
                    Toast.makeText(BlueBelt.this, "빈칸에 0 입력해주세요",
                            Toast.LENGTH_LONG).show();
                } else {
                    String s1 = edit1.getText().toString();
                    String s2 = edit2.getText().toString();
                    String s3 = edit3.getText().toString();
                    String s4 = edit4.getText().toString();
                    String s6 = edit6.getText().toString();
                    String s7 = edit7.getText().toString();
                    String s8 = edit8.getText().toString();
                    String s9 = edit9.getText().toString();
                    String s20 = edit20.getText().toString();
                    String s21 = edit21.getText().toString();
                    String s22 = edit22.getText().toString();
                    String s23 = edit23.getText().toString();
                    String s24 = edit24.getText().toString();
                    String s25 = edit25.getText().toString();
                    String s26 = edit26.getText().toString();

                    int result = Integer.parseInt(s1) + Integer.parseInt(s2) +
                            Integer.parseInt(s3) + Integer.parseInt(s4)+ Integer.parseInt(s6) + Integer.parseInt(s7)+ Integer.parseInt(s8)
                            + Integer.parseInt(s9)+ Integer.parseInt(s20)+ Integer.parseInt(s21)+ Integer.parseInt(s22)+ Integer.parseInt(s23)
                            + Integer.parseInt(s24)+ Integer.parseInt(s25);
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
        Button btn6 = (Button)findViewById(R.id.search);
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
        SharedPreferences pref2 = getSharedPreferences("pref2", Activity.MODE_PRIVATE); // UI 상태를 저장합니다.
        SharedPreferences.Editor editor = pref2.edit(); // Editor를 불러옵니다.

        EditText edit1 = (EditText)findViewById(R.id.editText01);
        EditText edit2 = (EditText)findViewById(R.id.editText02);
        EditText edit3 = (EditText)findViewById(R.id.editText03);
        EditText edit4 = (EditText)findViewById(R.id.editText04);
        EditText edit5 = (EditText)findViewById(R.id.editText05);
        EditText edit6 = (EditText)findViewById(R.id.editText06);
        EditText edit7 = (EditText)findViewById(R.id.editText07);
        EditText edit8 = (EditText)findViewById(R.id.editText08);
        EditText edit9 = (EditText)findViewById(R.id.editText09);
        EditText edit20 = (EditText)findViewById(R.id.editText20);
        EditText edit21 = (EditText)findViewById(R.id.editText21);
        EditText edit22 = (EditText)findViewById(R.id.editText22);
        EditText edit23 = (EditText)findViewById(R.id.editText23);
        EditText edit24 = (EditText)findViewById(R.id.editText24);
        EditText edit25 = (EditText)findViewById(R.id.editText25);
        EditText edit26 = (EditText)findViewById(R.id.editText26);

        CheckBox check1 = (CheckBox)findViewById(R.id.checkbox01);
        CheckBox check2 = (CheckBox)findViewById(R.id.checkbox02);
        CheckBox check3 = (CheckBox)findViewById(R.id.checkbox03);
        CheckBox check4 = (CheckBox)findViewById(R.id.checkbox04);
        CheckBox check5 = (CheckBox)findViewById(R.id.checkbox05);
        CheckBox check6 = (CheckBox)findViewById(R.id.checkbox06);
        CheckBox check7 = (CheckBox)findViewById(R.id.checkbox07);
        CheckBox check8 = (CheckBox)findViewById(R.id.checkbox08);
        CheckBox check9 = (CheckBox)findViewById(R.id.checkbox09);
        CheckBox check10 = (CheckBox)findViewById(R.id.checkbox10);
        CheckBox check20 = (CheckBox)findViewById(R.id.checkbox20);
        CheckBox check21 = (CheckBox)findViewById(R.id.checkbox21);
        CheckBox check22 = (CheckBox)findViewById(R.id.checkbox22);
        CheckBox check23 = (CheckBox)findViewById(R.id.checkbox23);
        CheckBox check24 = (CheckBox)findViewById(R.id.checkbox24);
        CheckBox check25 = (CheckBox)findViewById(R.id.checkbox25);
        CheckBox check26 = (CheckBox)findViewById(R.id.checkbox26);


        // 저장할 값들을 입력합니다.
        editor.putString("editText1", edit1.getText().toString());
        editor.putString("editText2", edit2.getText().toString());
        editor.putString("editText3", edit3.getText().toString());
        editor.putString("editText4", edit4.getText().toString());
        editor.putString("editText5", edit5.getText().toString());
        editor.putString("editText6", edit6.getText().toString());
        editor.putString("editText7", edit7.getText().toString());
        editor.putString("editText8", edit8.getText().toString());
        editor.putString("editText9", edit9.getText().toString());
        editor.putString("editText20", edit20.getText().toString());
        editor.putString("editText21", edit21.getText().toString());
        editor.putString("editText22", edit22.getText().toString());
        editor.putString("editText23", edit23.getText().toString());
        editor.putString("editText24", edit24.getText().toString());
        editor.putString("editText25", edit25.getText().toString());
        editor.putString("editText26", edit26.getText().toString());

        editor.putBoolean("check1", check1.isChecked());
        editor.putBoolean("check2", check2.isChecked());
        editor.putBoolean("check3", check3.isChecked());
        editor.putBoolean("check4", check4.isChecked());
        editor.putBoolean("check5", check5.isChecked());
        editor.putBoolean("check6", check6.isChecked());
        editor.putBoolean("check7", check7.isChecked());
        editor.putBoolean("check8", check8.isChecked());
        editor.putBoolean("check9", check9.isChecked());
        editor.putBoolean("check10", check10.isChecked());
        editor.putBoolean("check20", check20.isChecked());
        editor.putBoolean("check21", check21.isChecked());
        editor.putBoolean("check22", check22.isChecked());
        editor.putBoolean("check23", check23.isChecked());
        editor.putBoolean("check24", check24.isChecked());
        editor.putBoolean("check25", check25.isChecked());
        editor.putBoolean("check26", check26.isChecked());

        editor.commit(); // 저장합니다.
    }

    public void popup05(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("경력계획서").setMessage("오아시스-큰사람프로젝트 관리-경력계획서 메뉴-검사 후 결과 다운-포인트신청(결과첨부)");

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

        builder.setTitle("취업지원과 상담").setMessage("오아시스-진로취업상담 예약-상담후 취업지원과에서 포인트 입력");

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

        builder.setTitle("기업탐방/현장실습").setMessage("기업체험활동보고서 작성(증빙자료 준비)-오아시스-큰사람프로젝트 관리-포인트 신청");

        AlertDialog alertDialog = builder.create();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alertDialog.getWindow().getAttributes());
        lp.width=400;
        lp.height=600;
        Window window = alertDialog.getWindow();
        window.setAttributes(lp);

        alertDialog.show();
    }

    public void popup03(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("외국어").setMessage("외국어 성적표 파일스캔(모의토익 불가)-오아시스-큰사람프로젝트 관리-포인트 신청(성적표첨부)");

        AlertDialog alertDialog = builder.create();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alertDialog.getWindow().getAttributes());
        lp.width=400;
        lp.height=500;
        Window window = alertDialog.getWindow();
        window.setAttributes(lp);

        alertDialog.show();
    }

    public void popup07(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("입사지원서 교육").setMessage("취업지원과 프로그램 개설시 개별신청(홈페이지로 사전공지)-수료자에 한해 취업지원과에서 일괄 포인트 입력");

        AlertDialog alertDialog = builder.create();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alertDialog.getWindow().getAttributes());
        lp.width=400;
        lp.height=500;
        Window window = alertDialog.getWindow();
        window.setAttributes(lp);

        alertDialog.show();
    }

    public void popup08(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("기업직무 적성검사").setMessage("모의인적성검사 참여(취업지원과)-수료자에 한해 취업지원과에서 일괊 포인트 입력[온라인 검사시 검사지 첨부하여 오아시스 신청");

        AlertDialog alertDialog = builder.create();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alertDialog.getWindow().getAttributes());
        lp.width=400;
        lp.height=500;
        Window window = alertDialog.getWindow();
        window.setAttributes(lp);

        alertDialog.show();
    }

    public void popup09(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("셀프면접/면접기초교육").setMessage("오아시스-셀프면접실 관리-면접실 사용-취업지원과 프로그램 개설시 개별신청(홈페이지 사전공지)-" +
                "수료자에 한해 취업지원과에서 일괄 포인트 입력");

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
