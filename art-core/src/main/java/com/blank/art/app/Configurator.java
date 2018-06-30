package com.blank.art.app;

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

    private Configurator() {
        CONFIGS.put(ConfigTypes.CONFIG_READY.name(), false);
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
        initIcons();
        CONFIGS.put(ConfigTypes.CONFIG_READY.name(), true);
    }


    public static HashMap<Object, Object> getConfigs() {
        return CONFIGS;
    }

    public final Configurator withApiHost(String host) {
        CONFIGS.put(ConfigTypes.API_HOST.name(), host);
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

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
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
     * 检查是否配置完成
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) CONFIGS.get(ConfigTypes.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configruation is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfigruation(Enum<ConfigTypes> key) {
        checkConfiguration();
        return (T) CONFIGS.get(key);
    }
}
