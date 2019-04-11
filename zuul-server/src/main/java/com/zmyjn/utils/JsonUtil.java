//package com.zmyjn.utils;
//
//import springfox.documentation.spring.web.json.Json;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class JsonUtil {
//
//
//
//    /**
//     * 转换json
//     * @param response
//     * @param map
//     */
//    public static void sendJsonMessage(HttpServletResponse response, Object map) {
//        Gson GSON = new Gson();
//
//
//        PrintWriter printWriter = null;
//        response.setContentType("application/json; charset=utf-8");
//        try {
//            printWriter = response.getWriter();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        printWriter.write(GSON.toJson(map));
//        printWriter.close();
//        try {
//            response.flushBuffer();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
