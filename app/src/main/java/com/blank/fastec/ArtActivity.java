package com.blank.fastec;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by blank.
 * Created on 6/26/2018.
 * Description:
 */

public class ArtActivity extends AppCompatActivity {
    private static final String TAG = "ArtActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: 执行了");
    }
}
