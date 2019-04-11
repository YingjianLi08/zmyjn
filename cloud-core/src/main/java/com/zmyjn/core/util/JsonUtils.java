package com.zmyjn.core.util;

import com.alibaba.druid.support.json.JSONUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonUtils {


    /**
     * 转换json
     * @param response
     * @param map
     */
    public static void sendJsonMessage(HttpServletResponse response, Object map) {
//        Gson GSON = new Gson();
        PrintWriter printWriter = null;
        response.setContentType("application/json; charset=utf-8");
        try {
            printWriter = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter.write(JSONUtils.toJSONString(map));
        printWriter.close();
        try {
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
