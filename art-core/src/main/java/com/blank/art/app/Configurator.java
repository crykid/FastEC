package com.blank.art.app;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.blank.art.delegates.web.event.Event;
import com.blank.art.delegates.web.event.EventManager;
import com.blankj.utilcode.util.Utils;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by : blank
 * Created on : 2018/6/26 at 10:33
 * Description:项目配置文件的存储、获取
 */

public class Configurator {

    /**
     * 各项配置map
     */
    private static final HashMap<Object, Object> CONFIGS = new HashMap<>();

    /**
     * 自定义文字图库集合
     */
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    /**
     * 自定义拦截器集合
     */
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();


    private static final Handler HANDLER = new Handler();

    private Configurator() {
        CONFIGS.put(ConfigTypes.CONFIG_READY, false);
        CONFIGS.put(ConfigTypes.HANDLER, HANDLER);

    }

    /**
     * 单例
     *
     * @return
     */
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();

    }


    public final void configure() {
        //初始化工具类
        initIcons();
        CONFIGS.put(ConfigTypes.CONFIG_READY, true);
        Utils.init(Art.getApplicationContext());
    }


    public static HashMap<Object, Object> getConfigs() {
        return CONFIGS;
    }

    public final Configurator withApiHost(String host) {
        CONFIGS.put(ConfigTypes.API_HOST, host);
        return this;
    }

    /**
     * 初始化iconfont
     */
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));

            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    /**
     * 引入自定义字体图标库
     *
     * @param descriptor
     * @return
     */
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    /**
     * 使用当前activity
     *
     * @param activity
     * @return
     */
    public final Configurator withActivity(Activity activity) {
        CONFIGS.put(ConfigTypes.ACTIVITY, activity);
        return this;
    }

    /**
     * 初始化微信，添加微信appId
     *
     * @param appid 微信appId
     * @return
     */
    public final Configurator withWechatAppId(String appid) {
        CONFIGS.put(ConfigTypes.WECHAT_APP_ID, appid);
        return this;
    }

    /**
     * 初始化微信，添加微信appSecret
     *
     * @param appSecret 微信appSecret
     * @return
     */
    public final Configurator withWechatAppSecret(String appSecret) {
        CONFIGS.put(ConfigTypes.WECHAT_APP_SECRET, appSecret);
        return this;
    }

    /**
     * 添加拦截器
     *
     * @param interceptor
     * @return
     */
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        CONFIGS.put(ConfigTypes.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 配置javascript接口名称，注：该名称需要与h5/js协调一致
     *
     * @param name javascript接口名称
     * @return
     */
    public final Configurator withJavascriptInterface(@NonNull String name) {
        CONFIGS.put(ConfigTypes.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    /**
     * 添加一些特殊的js event
     *
     * @param name
     * @param event
     * @return
     */
    public final Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }

    /**
     * 浏览器加载的host
     *
     * @param host
     * @return
     */
    public final Configurator withWebHost(String host) {
        CONFIGS.put(ConfigTypes.WEB_HOST, host);
        return this;
    }


    /**
     * 检查是否配置完成
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) CONFIGS.get(ConfigTypes.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configruation is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfigruation(Object key) {
        checkConfiguration();

        final Object value = CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL !");
        }

        return (T) CONFIGS.get(key);
    }
}
