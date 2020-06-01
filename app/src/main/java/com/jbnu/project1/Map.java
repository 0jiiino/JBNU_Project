package com.jbnu.project1;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class Map extends AppCompatActivity implements OnMapReadyCallback {
    private static String TAG = Map.class.getSimpleName();

    private GoogleMap googleMap;                        // 구글 map
    private Marker currentMarker;                       // 현재 나의 위치 marker

    // 현위치를 받아오기 위해 사용되는 객체
    private FusedLocationProviderClient fusedLocationClient;
    // 위치 업데이트에 대한 서비스 품질을 요청하는 데 사용
    private LocationRequest locationRequest;

    // 편의시설 array list
    private ArrayList<Facility> facilities;

    private static final int REQUEST_CODE_LOCATION_ENABLE = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // 화면 켜진 상태 유지하기
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setTitle("교내 편의 시설 위치 정보");

        // 현위치 정보 얻데이트 성능 설정
        this.locationRequest = new LocationRequest()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(this.locationRequest);

        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // 구글 지도 표시
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapping);
        mapFragment.getMapAsync(this);

        // 편의시설 객체 만들기
        createFacility();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // 현위치 정보 받아오기 위한 설정
        this.fusedLocationClient.requestLocationUpdates(this.locationRequest, mLocationCallback, null);

        if (this.googleMap != null) {
            // 지도에 현재위치 표시 true
            this.googleMap.setMyLocationEnabled(true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (this.fusedLocationClient != null) {
            // 위치정보 받아오는 callback 제거
            this.fusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_LOCATION_ENABLE:
                    // 사용자가 위치정보 활성 시켰는지 검사
                    if (checkLocationServicesStatus()) {
                        Log.d(TAG, "onActivityResult : 위치정보 활성화 되있음");
                        return;
                    }
                    break;
            }
        }
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;

        // 최초 위치 표시 (디폴트 전북대)
        setMyLocation(35.846950, 127.129399);

        // 위치정보 업데이트 시작
        startLocationUpdates();

        // 편의시설 마크 표시
        for (Facility facility : this.facilities) {
            LatLng latLng = new LatLng(facility.latitude, facility.longitude);
            // 마크 생성
            createMarker(latLng, facility.title, facility.snippet, BitmapDescriptorFactory.HUE_ROSE);
        }

        this.googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));
    }

    /* 편의시설 객체 만들기 */
    private void createFacility() {
        this.facilities = new ArrayList<>();

        this.facilities.add(new Facility("중앙도서관", "1층, 4층", 35.848561, 127.131932));
        this.facilities.add(new Facility("공과대학 1호관", "복사집", 35.846844, 127.132551));
        this.facilities.add(new Facility("인문대 1호관", "1층", 35.843219, 127.133082));
        this.facilities.add(new Facility("상대 1호관", "1층", 35.844730, 127.133859));
        this.facilities.add(new Facility("상대 2호관", "1층", 35.844794, 127.135297));
        this.facilities.add(new Facility("제 1학생회관", "1층", 35.845824, 127.128157));
        this.facilities.add(new Facility("학습도서관", "1층", 35.845680, 127.133184));
        this.facilities.add(new Facility("의학 도서관", "1층", 35.847752, 127.143497));
        this.facilities.add(new Facility("사회과학대", "1층", 35.844102, 127.133763));
        this.facilities.add(new Facility("사범대 본관", "1층", 35.842641, 127.132017));
    }

    /* 위치정보 업데이트 시작 */
    private void startLocationUpdates() {
        // 위치정보 사용 여부 상태 체크
        if (checkLocationServicesStatus()) {
            // 현재 위치 정보 받아오기 위한 설정
            this.fusedLocationClient.requestLocationUpdates(locationRequest, mLocationCallback, Looper.myLooper());
            this.googleMap.setMyLocationEnabled(true);
        }else {
            // 위치정보 활성화 하기
            showDialogForLocationServiceSetting();
        }
    }

    /* 위치정보 사용여부 체크 */
    private boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    /* 나의 위치 표시 */
    private void setMyLocation(double latitude, double longitude) {
        if (this.currentMarker != null) {
            this.currentMarker.remove();
        }

        LatLng latLng = new LatLng(latitude, longitude);

        // 마커 생성
        this.currentMarker = createMarker(latLng, "나", "", BitmapDescriptorFactory.HUE_CYAN);
        // 마크 타이틀 항상 표시
        this.currentMarker.showInfoWindow();

        // 카메라 이동
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    /* 마커 생성 */
    private Marker createMarker(LatLng latLng, String markerTitle, String markerSnippet, float icon) {
        MarkerOptions markerOptions = new MarkerOptions();
        // 위치
        markerOptions.position(latLng);
        // 타이틀
        markerOptions.title(markerTitle);
        // 서브 타이틀
        if (!TextUtils.isEmpty(markerSnippet)) {
            markerOptions.snippet(markerSnippet);
        }

        // 아이콘 표시
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(icon));

        // 구글맵에 마크 추가
        return this.googleMap.addMarker(markerOptions);
    }

    /* 위치정보 활성화 */
    private void showDialogForLocationServiceSetting() {
        new AlertDialog.Builder(this)
                .setTitle("위치 서비스")
                .setMessage("앱을 사용하기 위해 위치 서비스가 필요합니다.\n위치 설정을 하시겠습니까?")
                .setCancelable(true)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(callGPSSettingIntent, REQUEST_CODE_LOCATION_ENABLE);
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    /* 위치가 바뀌면 callback 함수 실행 : 현위치 받아오는 Callback */
    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);

            // 위치정보
            List<Location> locationList = locationResult.getLocations();

            if (locationList.size() > 0) {
                Log.d(TAG, "LocationCallback");

                Location location = locationList.get(locationList.size() - 1);
                //Location location = locationList.get(0);

                // 나의 현재 위치에 마커 생성하고 이동
                setMyLocation(location.getLatitude(), location.getLongitude());
            }
        }
    };

    /* 편의시설 객체 */
    private class Facility {
        String title;
        String snippet;
        double latitude;                 // 위도
        double longitude;                // 경도

        Facility(String title, String snippet, double latitude, double longitude) {
            this.title = title;
            this.snippet = snippet;
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }
}