package com.jbnu.project1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

import static java.lang.Integer.getInteger;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseUnsignedInt;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_id, et_pass, et_passck,et_name,et_num;
    private Button btn_register,validateButton;
    private AlertDialog dialog;
    private boolean validate=false;
    Button b;
    EditText et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_id=findViewById(R.id.et_id);
        et_pass=findViewById(R.id.et_pass);
        et_passck=findViewById(R.id.et_passck);
        et_name=findViewById(R.id.et_name);
        et_num=findViewById(R.id.et_num);
        validateButton=findViewById(R.id.validateButton);

        b = (Button) findViewById(R.id.validateButton);
        et = (EditText) findViewById(R.id.et_id);

        b.setEnabled(false); // set button disable initially
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().equals("")) {
                    b.setEnabled(false);
                } else {
                    b.setEnabled(true);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        et_passck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            String password = et_pass.getText().toString();
            String check = et_passck.getText().toString();

            if(password.equals(check)){
                et_pass.setBackgroundColor(Color.GREEN);
                et_passck.setBackgroundColor(Color.GREEN);
            }else{
                et_pass.setBackgroundColor(Color.RED);
                et_passck.setBackgroundColor(Color.RED);
            }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID=et_id.getText().toString();

                Response.Listener<String> responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse=new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder=new AlertDialog.Builder( RegisterActivity.this );
                                dialog=builder.setMessage("사용할 수 있는 아이디입니다.")
                                        .setPositiveButton("확인",null)
                                        .create();
                                dialog.show();
                                et_id.setEnabled(true);
                                validate=true;
                                validateButton.setText("사용가능");
                            }
                            else{
                                AlertDialog.Builder builder=new AlertDialog.Builder( RegisterActivity.this );
                                dialog=builder.setMessage("사용할 수 없는 아이디입니다.")
                                        .setNegativeButton("확인",null)
                                        .create();
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest validateRequest=new ValidateRequest(userID,responseListener);
                RequestQueue queue= Volley.newRequestQueue(RegisterActivity.this);
                queue.add(validateRequest);

            }
        });



        btn_register=findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID=et_id.getText().toString();
                String userPass=et_pass.getText().toString();
                String userName=et_name.getText().toString();
                String userNum=et_num.getText().toString();
                Button button = (Button) findViewById(R.id.validateButton);
                String yourText = (String)button.getText();


                if(userID.equals("")||userPass.equals("")||userName.equals("")||et_num.getText().toString().equals("") ){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("빈칸을 채워주세요")
                            .setNegativeButton("OK", null)
                            .create();
                    dialog.show();
                    return;
                }
                if(yourText.equals("중복확인"))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("아이디 중복확인을 해주세요")
                            .setNegativeButton("OK", null)
                            .create();
                    dialog.show();
                    return;
                }
                if(!et_pass.getText().toString().equals(et_passck.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"비밀번호 불일치",Toast.LENGTH_SHORT).show();
                    et_pass.setText("");
                    et_passck.setText("");
                    et_pass.requestFocus();
                    return;
                }

                String a = et_pass.getText().toString();
                // 대소문자 구분 숫자 특수문자  조합 8~ 15 자리
                String pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,15}$";
                Boolean tt = Pattern.matches(pwPattern,a);
                if( tt != true){
                    Toast.makeText(RegisterActivity.this, "비밀번호 형식을 지켜주세요", Toast.LENGTH_SHORT).show();
                    et_pass.setText("");
                    et_passck.setText("");
                    et_pass.requestFocus();
                    return;
                }


                    Response.Listener<String> responseListener =
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        boolean success = jsonObject.getBoolean("success");
                                        if (success) {
                                            Toast.makeText(getApplicationContext(), "회원 등록에 성공", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(getApplicationContext(), "회원 등록에 실패", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };

                RegisterRequest registerRequest=new RegisterRequest(userID,userPass,userName,userNum,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);


            }
        });



    }


}
