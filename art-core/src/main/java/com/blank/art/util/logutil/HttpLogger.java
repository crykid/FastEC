package com.blank.art.util.logutil;

/**
 * Created by : blank
 * Created on : 2018/5/9 at 14:35
 * Description: OkHttp Interceptor log字符格式化工具
 */

public class HttpLogger implements ParamsLogInterceptor.Logger {

    private StringBuilder mMessage = new StringBuilder();

    @Override
    public void log(String message) {
        // 请求或者响应开始
        if (message.startsWith("--> POST")) {
            mMessage.setLength(0);
        }
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        if ((message.startsWith("{") && message.endsWith("}"))
                || (message.startsWith("[") && message.endsWith("]"))) {

            //这里使用json格式化工具进行格式，实际上Logger有直接打印json数据得方法
            message = JsonUtil.formatJson(JsonUtil.decodeUnicode(message));
        }
        mMessage.append(message.concat("\n"));
        // 响应结束，打印整条日志
        if (message.startsWith("<-- END HTTP")) {
            LogUtil.d(mMessage.toString());
        }
    }
}
