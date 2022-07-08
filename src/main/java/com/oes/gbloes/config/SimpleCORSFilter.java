package com.oes.gbloes.config;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于进行整站允许跨域访问的的过滤器
 */
@Component
@WebFilter(urlPatterns = "/*")
public class SimpleCORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        /*
         *  设置允许跨域请求的header
         */
        resp.setStatus(HttpStatus.OK.value());
        resp.setContentType("text/plain;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT,DELETE, OPTIONS");
        resp.setHeader("Access-Control-Max-Age", "0");
        resp.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token,WG-Token, Authorization, auth-token");
        //设置允许携带cookie。如果该值没有指定，则在跨域访问时的session会丢失，从而导致无法确定用户身份。
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        //设置暴露给客户端的header值。该值的作用是刷新token的时间，让客户端可以重新刷新token的有效时间
        resp.setHeader("Access-Control-Expose-Headers ", "new-auth-token");
        resp.setHeader("XDomainRequestAllowed", "1");

        //放行所有 OPTIONS 请求。
        String type = req.getMethod();
        /**
         * 在使用CORS方式处理跨域问题时，浏览器会先提交OPTIONS请求，用于验证服务器端是否允许跨域
         * 这是发生跨域请求403的主要原因。
         *
         * 这里要判断请求的method是否为'OPTIONS'，如果是，则放行，并且将前面写入到响应流中headers返回给客户端。
         * 通知客户端，当前站点是'允许'进行跨域访问的。
         */
        if (type.toUpperCase().equals("OPTIONS")) {
            return;
        }

        chain.doFilter(request, response);
    }
}
