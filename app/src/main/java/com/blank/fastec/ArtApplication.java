package com.blank.fastec;

import android.app.Application;
import android.support.annotation.NonNull;

import com.blank.art.app.Art;
import com.blank.art.ec.database.DatabaseManager;
import com.blank.art.ec.icon.FontEcModule;
import com.blank.art.retrofit.intercepter.AddCookieInterceptor;
import com.blank.art.retrofit.intercepter.DebugInterceptor;
import com.blank.art.util.callback.CallbackManager;
import com.blank.art.util.callback.CallbackType;
import com.blank.art.util.callback.IGlobalCallback;
import com.blank.art.util.logutil.HttpLogger;
import com.blank.art.util.logutil.ParamsLogInterceptor;
import com.blank.fastec.event.ShareEvent;
import com.blank.fastec.event.TestEvent;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by : blank
 * Created on : 2018/6/26 at 10:28
 * Description: application,全局配置类
 */

public class ArtApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Art.init(this)
                .withApiHost("http://192.168.1.7:8000/")
//                .withApiHost("http://172.32.25.80:8000/")
                .withWebHost("https://www.example.com/")
                //引入默认的iconfont
                .withIcon(new FontAwesomeModule())
                //引入自己的字体图标
                .withIcon(new FontEcModule())
                //取到了res-raw-rest.json文件
                .withInterceptor(new DebugInterceptor("cart/", R.raw.cart))
                .withInterceptor(new DebugInterceptor("goods/", R.raw.goods))
                .withInterceptor(new DebugInterceptor("order_list/", R.raw.orders))
                .withInterceptor(new DebugInterceptor("addresses/", R.raw.addresses))
                .withInterceptor(new DebugInterceptor("search/", R.raw.addresses))
                .withInterceptor(new DebugInterceptor("goods_detail/", R.raw.goodsdetail))
                .withInterceptor(new ParamsLogInterceptor(new HttpLogger()).setLevel(ParamsLogInterceptor.Level.BODY))
                //添加同步cookie拦截器
                .withInterceptor(new AddCookieInterceptor())

                .withJavascriptInterface("ART")
                .withWebEvent("test", new TestEvent())
                .withWebEvent("share", new ShareEvent())

//                .withWechatAppId("")
//                .withWechatAppSecret("")
                .configure();

        CallbackManager.getInstance()
                .addCallback(CallbackType.OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@NonNull Object args) {
                        if (JPushInterface.isPushStopped(Art.getApplicationContext())) {
                            //极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Art.getApplicationContext());
                        }
                    }
                })
                .addCallback(CallbackType.STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@NonNull Object args) {
                        if (!JPushInterface.isPushStopped(Art.getApplicationContext())) {
                            JPushInterface.stopPush(Art.getApplicationContext());
                        }
                    }
                });


        //初始化数据库
        DatabaseManager.getInstance().init(this);
//        ShareSdk
        //Stetho默认配置
//        Stetho.initializeWithDefaults(this);

        //Stetho自定义配置（需要的时候可以使用，因为这个东西打印的log太多，影响调试，所以不需要使用的时候取消初始化）
//        initStetho();

    }

    /**
     * 自定义初始化Stetho,web调试工具<br>
     * <p>此处主要作用查看数据库、缓存等数据，ui映射</p>
     * <p>用法，chrome地址栏输入：chrome://inspect</p>
     */
    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}
