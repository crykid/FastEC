package com.blank.art.retrofit.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.blank.art.app.Art;
import com.blank.art.retrofit.callback.IRequest;
import com.blank.art.retrofit.callback.ISuccess;
import com.blank.art.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by : blank
 * Created on : 2018/6/27 at 16:50
 * Description:
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest request, ISuccess success) {
        this.REQUEST = request;
        this.SUCCESS = success;
    }

    @Override
    protected File doInBackground(Object... objects) {
        String downloadDir = (String) objects[0];
        String extension = (String) objects[1];
        final ResponseBody body = (ResponseBody) objects[2];
        final String name = (String) objects[3];

        final InputStream is = body.byteStream();
        if (TextUtils.isEmpty(downloadDir)) {
            downloadDir = "down_loads";
        }
        if (TextUtils.isEmpty(extension)) {
            //TODO:暂时不处理这个
            extension = "";
        }
        if (TextUtils.isEmpty(name)) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);
        }
    }

    /**
     * 回到主线程的操作
     *
     * @param file
     */
    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

    }

    private void autoInstallApk(File file) {
        //如果文件以".apk"结尾
        if (FileUtil.getExtension(file.getPath()).equals(".apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            Art.getApplicationContext().startActivity(install);
        }
    }
}
