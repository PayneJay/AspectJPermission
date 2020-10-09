package com.leather.permission_lib;

public interface IPermission {
    /**
     * 权限拒绝
     */
    void permissionDenied(int requestCode, String[] permissions);

    /**
     * 权限通过
     */
    void permissionGranted();
}
