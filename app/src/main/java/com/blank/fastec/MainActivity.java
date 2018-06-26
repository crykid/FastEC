package com.blank.fastec;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.blank.art.activities.ProxyActivity;
import com.blank.art.delegates.ArtDelegate;

public class MainActivity extends ProxyActivity {
    private static final String TAG = "MainActivity";
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//
//        Log.d(TAG, "onCreate:执行了 ");
//        super.onCreate(savedInstanceState, persistentState);
//    }

    @Override
    public ArtDelegate getRootDelegate() {
        return new MainDelegate();
    }
}
