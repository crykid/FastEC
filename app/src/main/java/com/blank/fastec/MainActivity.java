package com.blank.fastec;

import com.blank.art.activities.ProxyActivity;
import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.launcher.LauncherDelegate;

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

        return new LauncherDelegate();
    }
}
