package com.blank.art.delegates;

import android.Manifest;
import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.blank.art.util.camera.ArtCamera;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by : blank
 * Created on : 2018/6/26 at 17:41
 * Description:
 */
@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegate {

    //不是直接调用方法，但不能是private方法
    @NeedsPermission(Manifest.permission.CAMERA)
    void startCamera() {
        ArtCamera.start(this);
    }

    //这个方法可以被调用
    public void startCameraWithCheck() {
        PermissionCheckerDelegatePermissionsDispatcher.startCameraWithCheck(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied() {
        Toast.makeText(getContext(), "不允许拍照", Toast.LENGTH_SHORT).show();
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void onCameraRationale(PermissionRequest request) {
        showRationaleDialog(request);
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNever() {
        Toast.makeText(getContext(), "永久拒绝使用", Toast.LENGTH_SHORT).show();
    }

    private void showRationaleDialog(final PermissionRequest request) {
//        new AlertDialog.Builder(getContext())
//                .setPositiveButton("同意使用", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        request.proceed();
//                    }
//                })
//                .setNegativeButton("拒绝使用", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        request.cancel();
//                    }
//                })
//                .setCancelable(false)
//                .setMessage("权限管理")
//                .show();
        new AlertDialog.Builder(getContext())
                .setPositiveButton("同意使用", (dialog, which) -> {
                    dialog.cancel();
                    request.proceed();

                })
                .setNegativeButton("拒绝使用", (dialog, which) -> {
                    dialog.cancel();
                    request.cancel();

                }).setCancelable(false)
                .setMessage("权限管理")
                .show();
    }
}
