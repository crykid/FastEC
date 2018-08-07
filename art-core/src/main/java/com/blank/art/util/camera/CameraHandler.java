package com.blank.art.util.camera;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blank.art.R;
import com.blank.art.delegates.PermissionCheckerDelegate;
import com.blank.art.util.file.FileUtil;
import com.blankj.utilcode.util.FileUtils;

import java.io.File;

/**
 * Created by : blank
 * Created on : 2018/8/7 at 22:38
 * Description:图片处理类
 */

public class CameraHandler implements View.OnClickListener {

    private final AlertDialog DIALOG;
    private final PermissionCheckerDelegate DELEGATE;

    public CameraHandler(PermissionCheckerDelegate delegate) {
        this.DELEGATE = delegate;
        DIALOG = new AlertDialog.Builder(delegate.getContext())
                .create();
    }

    final void beginCameraDialog() {
        DIALOG.show();
        final Window window = DIALOG.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_camera_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            //透明
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            params.dimAmount = 0.5f;
            window.setAttributes(params);

            window.findViewById(R.id.abtn_photodialog_btn_take).setOnClickListener(this);
            window.findViewById(R.id.abtn_photodialog_btn_native).setOnClickListener(this);
            window.findViewById(R.id.abtn_photodialog_btn_cancel).setOnClickListener(this);
        }

    }

    private String generatePhotoName() {
        return FileUtil.getFileNameByTime("IMG", "JPG");
    }

    private void takePhoto() {
        final String currentPhotoName = generatePhotoName();
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final File tempFile = new File(FileUtil.CAMERA_PHOTO_DIR, currentPhotoName);

        //7.0强制使用provider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            final ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, tempFile.getPath());
            final Uri uri = DELEGATE.getContext().getContentResolver()
                    .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            //将uri路径转化为实际路径

            final File realFile = FileUtils.getFileByPath(FileUtil.getRealFilePath(DELEGATE.getContext(), uri));
            final Uri realUri = Uri.fromFile(realFile);
            CameraImageBean.getInstance().setPath(realUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        } else {
            final Uri fileUri = Uri.fromFile(tempFile);
            CameraImageBean.getInstance().setPath(fileUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        }
        DELEGATE.startActivityForResult(
                intent,
                RequestCode.REQUESTCODE_TAKE_PHOTE
        );
    }

    private void pickPhoto() {
        final Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        DELEGATE.startActivityForResult(
                Intent.createChooser(intent, "选择获取图片的方式"),
                RequestCode.REQUESTCODE_PICK_PHOTE
        );


    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.abtn_photodialog_btn_take) {
            DIALOG.cancel();
            takePhoto();

        } else if (i == R.id.abtn_photodialog_btn_native) {
            DIALOG.cancel();
            pickPhoto();

        } else if (i == R.id.abtn_photodialog_btn_cancel) {
            DIALOG.cancel();

        }
    }
}
