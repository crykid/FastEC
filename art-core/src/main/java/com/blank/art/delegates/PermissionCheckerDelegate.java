package com.blank.art.delegates;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.blank.art.ui.scanner.ScannerDelegate;
import com.blank.art.util.callback.CallbackManager;
import com.blank.art.util.callback.CallbackType;
import com.blank.art.util.callback.IGlobalCallback;
import com.blank.art.util.camera.ArtCamera;
import com.blank.art.util.camera.CameraImageBean;
import com.blank.art.util.camera.RequestCode;
import com.yalantis.ucrop.UCrop;

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

    /**
     * 扫描二维码（不直接调用）
     */
    @NeedsPermission(Manifest.permission.CAMERA)
    void startScan(BaseDelegate delegate) {
        delegate.getSupportDelegate().startForResult(new ScannerDelegate(), RequestCode.REQUESTCODE_SCAN);
    }

    public void startScanWithCheck(BaseDelegate delegate) {
        PermissionCheckerDelegatePermissionsDispatcher.startScanWithCheck(this, delegate);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCode.REQUESTCODE_TAKE_PHOTE:
                    final Uri resultUri = CameraImageBean.getInstance().getPath();
                    UCrop.of(resultUri, resultUri)
                            .withMaxResultSize(400, 400)
                            .start(getContext(), this);
                    break;
                case RequestCode.REQUESTCODE_PICK_PHOTE:
                    if (data != null) {
                        final Uri pickResult = data.getData();
                        final String pickCropPath = ArtCamera.createCropFile().getPath();

                        UCrop.of(pickResult, Uri.parse(pickCropPath))//传入相同的地址：将裁剪后的结果覆盖原来的
                                .withMaxResultSize(400, 800)
                                .start(getContext(), this);
                    }
                    break;
                case RequestCode.REQUESTCODE_CROP_PHOTE:
                    final Uri cropUri = UCrop.getOutput(data);
                    //拿到裁剪后的数据进行处理
                    final IGlobalCallback callback = CallbackManager
                            .getInstance()
                            .getCallback(CallbackType.ON_CROP);
                    if (callback != null) {
                        callback.executeCallback(cropUri);
                    }
                    break;
                case RequestCode.REQUESTCODE_CROP_ERROR:

                    Toast.makeText(getContext(), "剪裁出错", Toast.LENGTH_SHORT).show();

                    break;
                case RequestCode.REQUESTCODE_SCAN:

                    break;

                default:
                    break;
            }
        }

    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
//        if (requestCode == RequestCode.REQUESTCODE_SCAN) {
//            if (data != null) {
//                final String qrCode = data.getString("SCAN_RESULT");
//                final IGlobalCallback<String> callback = CallbackManager.getInstance()
//                        .getCallback(CallbackType.ON_SCAN);
//                if (callback != null) {
//                    callback.executeCallback(qrCode);
//                }
//            }
//        }
    }
}
