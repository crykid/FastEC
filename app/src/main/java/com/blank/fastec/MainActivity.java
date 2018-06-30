package com.blank.fastec;

import android.widget.Toast;

import com.blank.art.activities.ProxyActivity;
import com.blank.art.app.ISignListener;
import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.launcher.LauncherDelegate;
import com.blank.art.ec.sign.SignInDelegate;
import com.blank.art.ec.sign.SignUpDelegate;
import com.blank.art.ui.launcher.ILauncherListener;
import com.blank.art.ui.launcher.OnLuncherFinishTag;

public class MainActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener {
    private static final String TAG = "MainActivity";

    @Override
    public ArtDelegate getRootDelegate() {

        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLuncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，登录成功了", Toast.LENGTH_SHORT).show();
                break;
            case NOT_SIGNED:
                startWithPop(new SignInDelegate());

                break;
            default:
                break;
        }
    }
}
