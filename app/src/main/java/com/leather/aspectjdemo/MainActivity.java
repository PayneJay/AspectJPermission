package com.leather.aspectjdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.leather.permission_lib.annotation.PermissionDenied;
import com.leather.permission_lib.annotation.PermissionNeed;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_permission_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionRequest();
            }
        });
    }

    @PermissionNeed(value = {Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR,
            Manifest.permission.CAMERA}, requestCode = 100)
    public void permissionRequest() {
        Log.d("leather", "权限申请通过了");
    }

    @PermissionDenied
    public void permissionDenied(int requestCode) {
        Log.d("leather", "权限申请拒绝了 requestCode : " + requestCode);
    }
}