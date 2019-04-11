package com.zmyjn.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.zmyjn.core.util.JsonUtils;
import com.zmyjn.core.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MrWang
 * @version v1.0
 * @date 2019/03/30
 * @Description pre前置过滤器
 */
@Component
public class PreFilter extends ZuulFilter {
//    @Value("${default.login.prefix}")
    private String defaultLoginPrefix = "/login";

//    @Value("${default.login.time}")
    private int loginTime = 36000;

   // @Autowired
   // private JedisCluster redisUtils;


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 解决跨域/token校验
     * 拦截器具体的代码
     * @return
     */
    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        HttpServletResponse response = requestContext.getResponse();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Authorization,X-Requested-With");
//        if (PassportCodeUtil.TOKEN_OPTION.equals(request.getMethod())) {
//            JsonUtil.sendJsonMessage(response,"ok");
//            requestContext.setSendZuulResponse(false);
//            return null;
//        }
        String requestURI = request.getRequestURI();
        //不对登录进行拦截
//        if (defaultLoginPrefix.equals(requestURI)) {
//            return null;
//        }
        if (requestURI.indexOf(defaultLoginPrefix) > 0) {
            return null;
        }

        String accessToken = request.getHeader("Authorization");
        System.out.println("accessToken:"+accessToken);
        if (StringUtils.isBlank(accessToken)) {
            Map map = new HashMap();
            map.put("msg","未登录或登录失效");
            JsonUtils.sendJsonMessage(response, map);
            requestContext.setSendZuulResponse(false);
            return null;
        }
//        String user = redisUtils.get(accessToken);
        String user = accessToken;
        if (StringUtils.isNotBlank(user)) {
            //redisUtils.expire(accessToken, loginTime);
            try {
                //requestContext.addZuulRequestHeader("userInfo", URLEncoder.encode(user, "UTF-8"));

                System.out.println(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } else {
            try {
                //JsonUtil.sendJsonMessage(response, ResponseUtil.rms(PassportCodeUtil.PFX, PassportCodeUtil.TOKEN_ERROR, "未登录或登录失效", null));
            } catch (Exception e) {
                e.printStackTrace();
            }
            requestContext.setSendZuulResponse(false);
            return null;
        }
    }
}
