package com.blank.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * Created by : blank
 * Created on : 2018/6/26 at 10:33
 * Description:项目配置文件
 */

public class Configurator {

    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();

    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigTypes.CONFIG_READY.name(), false);
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
        LATTE_CONFIGS.put(ConfigTypes.CONFIG_READY.name(), true);
    }


    public static WeakHashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigTypes.API_HOST.name(), host);
        return this;
    }

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
