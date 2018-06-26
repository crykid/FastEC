package com.blank.latte.app;

import java.util.WeakHashMap;

/**
 * Created by : blank
 * Created on : 2018/6/26 at 10:33
 * Description:项目配置文件
 */

public class Configurator {

    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();

    public Configurator() {
        LATTE_CONFIGS.put(ConfigTypes.CONFIG_READY.name(), false);
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();

    }

    public final void configure() {
        LATTE_CONFIGS.put(ConfigTypes.CONFIG_READY.name(), true);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public static WeakHashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigTypes.API_HOST.name(), host);
        return this;
    }

    /**
     * 检查是否配置完成
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigTypes.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configruation is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfigruation(Enum<ConfigTypes> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key);
    }
}
