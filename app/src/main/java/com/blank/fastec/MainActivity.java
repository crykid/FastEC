package com.blank.fastec;

import com.blank.art.activities.ProxyActivity;
import com.blank.art.delegates.ArtDelegate;

public class MainActivity extends ProxyActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        Toast.makeText(this, "传入 context", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public ArtDelegate getRootDelegate() {
        return new MainDelegate();
    }
}
