package com.blank.art.app;

/**
 * Created by : blank
 * Created on : 2018/6/26 at 10:36
 * Description:全局配置项
 */

public enum ConfigTypes {
    /**
     * api服务器域名
     */
    API_HOST,
    /**
     *
     */
    WEB_HOST,
    /**
     * 全局上下文
     */
    APPLICATION_CONTEXT,
    /**
     * 是否初始化
     */
    CONFIG_READY,
    /**
     * 自定义图标、文字
     */
    ICON,

    /**
     * 拦截器配置
     */
    INTERCEPTOR,

    /**
     * 微信appId
     */
    WECHAT_APP_ID,
    /**
     * 微信appSecret
     */
    WECHAT_APP_SECRET,


    ACTIVITY,

    /**
     * javascript（h5/js与原生交互需要）接口
     */
    JAVASCRIPT_INTERFACE,

    /**
     * Hander配置项
     */
    HANDLER,


}
