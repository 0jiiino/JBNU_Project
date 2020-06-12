package com.jbnu.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, Splash.class);
        startActivity(intent);

        Button tip = (Button)findViewById(R.id.tip);
        tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //꿀팁 게시판 클릭시 활성화
                Intent intent = new Intent(getApplicationContext(),Firesign.class);
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
        notice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //공지 사항 클릭시 활성화
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.jbnu.ac.kr/kor/?menuID=139&pno=1"));
                startActivity(intent);
            }
        });

        // 권한 설정 및 초기화 -> 클래스 액티비티이름 + 고정값. init은 함수 이름 뒤에는 고정 -> 자기 자신에게 넘겨준다.
        // 빌드 한 번 해주면 클래스가 알아서 생성
        // permissionDispatcher 검색
        MainActivityPermissionsDispatcher.initWithPermissionCheck(this);
    }

    // 퍼미션 허용, 비허용 -> 이쪽으로 이벤트 넘어감
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //  권한이 부여되었는지 확인
        // 액티비티이름 + 고정.고정
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    /* 초기화 */
    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void init() {
        // 권한설정 및 초기화
        // private, public 등을 넣으면 안됨.
        // 메인에서 퍼미션 잡으면 다른 곳에서 퍼미션 잡지 않아도 됨.
    }

    @OnShowRationale({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void showRationale(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton("수락", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("거절", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage("앱을 이용하기 위해 권한이 필요합니다.")
                .show();
    }

    @OnPermissionDenied({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void showDenied() {
        Toast.makeText(this, "앱을 이용하기 위해 권한이 필요합니다.", Toast.LENGTH_LONG).show();
    }

    // 묻지 않음 체크.
    @OnNeverAskAgain({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void showNeverAsk() {
        Toast.makeText(this, "앱을 이용하기 위해 권한이 필요합니다.", Toast.LENGTH_LONG).show();
    }
}