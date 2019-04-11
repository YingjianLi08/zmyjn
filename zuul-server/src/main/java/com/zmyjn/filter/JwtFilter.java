package com.zmyjn.filter;

import com.zmyjn.core.util.jwt.Audience;
import com.zmyjn.core.util.jwt.JwtHelper;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private Audience audience;


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        //等到请求头信息authorization信息
        final String authHeader = request.getHeader("access_token");
        String localUrl = "http://" + req.getServerName() //服务器地址
                + ":"
                + request.getServerPort()//端口号
                + request.getContextPath()      //项目名称
                + request.getServletPath();

        String strBackUrl = "http://" + req.getServerName() //服务器地址
                + ":"
                + request.getServerPort()//端口号
                + request.getContextPath()      //项目名称
                + request.getServletPath();      //请求页面或其他地址
        //+ "?" + (request.getQueryString()); //参数

        logger.error("当前url："+strBackUrl);

        Object ss = request.getRequestURL();
        logger.error(ss.toString());



        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);
        } else {

            String token = "";
            if (authHeader == null || !authHeader.startsWith("bearer;")) {
                logger.error("当前请求头为空");

            }else{
                token = authHeader.substring(7);
            }

            try {
                if (audience == null) {
                    BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                    audience = (Audience) factory.getBean("audience");
                }

                final Claims claims = JwtHelper.parseJWT(token, audience.getBase64Secret());
                if (claims == null) {

                    logger.error("当前url:"+strBackUrl);
//                    if(localUrl.length() == strBackUrl.length() && authHeader != null){
//                        // 已登录
//                        logger.error("已登录");
//                        chain.doFilter(req, res);
//                        return;
//                    }else
                    if(strBackUrl.indexOf("/login")>0){
                        // 登录页面不需要拦截
                        chain.doFilter(req, res);
                        return;
                    }else if(strBackUrl.indexOf(".css")>1 || strBackUrl.indexOf(".js")>1){
                        // css 和js不需要拦截
                        chain.doFilter(req, res);
                        return;
                    }else{
                        // 未登录，重定向到登录页面
                        //logger.error("未登录，重定向到登录页面");
                        String url = "http://"+request.getHeader("Host")+"/login.html";
                        //System.out.println("重定向地址："+url);
//                        response.sendRedirect(url);
//                        return;
                    }
                }
                request.setAttribute("Constants.CLAIMS", claims);
            } catch (final Exception e) {
                logger.error("系统出错");
            }
            chain.doFilter(req, res);
        }
    }
}
