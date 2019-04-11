//package com.zmyjn.filter;
//
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.alibaba.druid.support.json.JSONUtils;
//import com.zmyjn.core.base.controller.ResultData;
//import com.zmyjn.core.util.jwt.Audience;
//import com.zmyjn.core.util.jwt.JwtHelper;
//import io.jsonwebtoken.Claims;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.BeanFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import java.util.HashMap;
//import java.util.Map;
//
////使用zuul网关接口拦截参数
//@Component
//public class TokenFilter extends ZuulFilter {
//
//    private static Logger log = LoggerFactory.getLogger(TokenFilter.class);
//
//    @Autowired
//    private Audience audience;
//
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 0;
//    }
//
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    public Object run() {
//        //拦截所有请求
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        String url = request.getRequestURL().toString();
//        log.info(String.format("%s >>> %s", request.getMethod(), url));
//        //获取请求token参数
//        String token = getToken(ctx);
//        log.info("token:" + token);
//
//
//        if (url.indexOf("login") > 0) {
//            // 登录不需要验证token
//            return null;
//        }
//        if (url.indexOf("vercode") > 0) {
//            // 获取验证码，不需要登录
//            return null;
//        }
//        if (url.indexOf("upload") > 0) {
//            // 上传附件，不需要登录
//            return null;
//        }
//
//        if (url.indexOf("syssite") > 0) {
//            // 获取网站信息，不需要登录
//            return null;
//        }
//        if (url.indexOf("public") > 0) {
//            // 不需要登录
//            return null;
//        }
//        if (url.indexOf("index") > 0) {
//            // 不需要登录
//            return null;
//        }
//
//        // 登录不需要验证token
//        try {
//
//            if(StringUtils.isNotBlank(token)){
//
//                if (audience == null) {
//                    BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
//                    audience = (Audience) factory.getBean("audience");
//                }
//
//                token = token.substring(7);
//                final Claims claims = JwtHelper.parseJWT(token, audience.getBase64Secret());
//
//                if (claims == null) {
//                    ctx.setSendZuulResponse(false); // 是否对该请求进行路由
//                    ctx.setResponseStatusCode(401); // 返回错误码
//
//                    Map<String, Object> map = new HashMap<>();
//                    ResultData resultData = new ResultData();
//                    resultData.setMsg("未登录");
//                    resultData.setResult(false);
//
//                    String ss = "{\"result\":false,\"data\":{},\"code\":0,\"msg\":\"未登录\"}";
//                    // String json = JSONUtils.toJSONString(resultData);
////                ctx.setResponseBody("{\"result\":\"accessToken为空!\"}");
//                    ctx.setResponseBody(ss);
//                    ctx.getResponse().setContentType("text/html;charset=UTF-8");
//                    return null;
//                }
//
//            }else{
//                // 未登录
//
//                ResultData resultData = new ResultData();
//                resultData.setMsg("未登录");
//                resultData.setResult(false);
//                String ss = "{\"result\":false,\"data\":{},\"code\":0,\"msg\":\"未登录\"}";
//                ctx.setResponseBody(ss);
//                ctx.getResponse().setContentType("text/html;charset=UTF-8");
//            }
//
//
//
//        } catch (Exception e) {
//            log.error("token验证出错");
//        }
//
//
//        return null;
//    }
//
//
//    private String getToken(RequestContext context) {
//        HttpServletRequest request = context.getRequest();
//        String authToken = request.getHeader("access_token");
//        if (StringUtils.isBlank(authToken)) {
//            authToken = request.getParameter("access_token");
//        }
//        context.addZuulRequestHeader("access_token", authToken);
//        return authToken;
//    }
//}