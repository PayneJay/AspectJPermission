package com.leather.permission_lib;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.leather.permission_lib.utils.PermissionsUtils;

public class PermissionRequestActivity extends AppCompatActivity {
    public static String REQUEST_CODE = "permission_request_code";
    public static String REQUEST_PERMISSIONS = "request_permissions";

    //请求权限集
    private String[] permissions;
    private int requestCode;
    private static IPermission mIPermission;

    /**
     * 启动权限请求类
     *
     * @param context     上下文
     * @param permissions 权限数组
     * @param requestCode 请求code
     */
    public static void startPermissionActivity(Context context, String[] permissions, int requestCode, IPermission iPermission) {
        mIPermission = iPermission;
        Log.d("leather", "startPermissionActivity");
        Intent intent = new Intent(context, PermissionRequestActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putStringArray(REQUEST_PERMISSIONS, permissions);
        bundle.putInt(REQUEST_CODE, requestCode);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_request);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            permissions = bundle.getStringArray(REQUEST_PERMISSIONS);
            requestCode = bundle.getInt(REQUEST_CODE, 0);
        }

        PermissionsUtils.getInstance().checkPermissions(this, permissions, mIPermission);
        Log.d("leather", "checkPermissions");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @androidx.annotation.NonNull String[] permissions,
                                           @androidx.annotation.NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsUtils.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
}