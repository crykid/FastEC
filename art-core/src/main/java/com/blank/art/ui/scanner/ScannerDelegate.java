package com.blank.art.ui.scanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ui.widget.ScanView;
import com.blank.art.util.callback.CallbackManager;
import com.blank.art.util.callback.CallbackType;
import com.blank.art.util.callback.IGlobalCallback;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by : blank
 * Created on : 2018/8/12 at 12:00
 * Description:二维码扫描，使用ZBar
 */

public class ScannerDelegate extends ArtDelegate implements ZBarScannerView.ResultHandler {
    private ScanView mScanView = null;

    @Override
    public Object getLayout() {
        return mScanView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mScanView == null) {
            mScanView = new ScanView(getContext());

        }
        mScanView.setAutoFocus(true);
        mScanView.setResultHandler(this);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void handleResult(Result result) {
        final Bundle bundle = new Bundle();
        bundle.putString("SCAN_RESULT", result.getContents());
//        setFragmentResult(RESULT_OK, bundle);
        getSupportDelegate().pop();


        //可以直接通过回调的方式，不再需要result方式
//        final String qrCode = data.getString("SCAN_RESULT");
        final IGlobalCallback<String> callback = CallbackManager.getInstance()
                .getCallback(CallbackType.ON_SCAN);
        if (callback != null) {
            callback.executeCallback(result.getContents());
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mScanView != null) {
            mScanView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mScanView != null) {
            mScanView.stopCameraPreview();
            mScanView.stopCamera();
        }
    }
}
