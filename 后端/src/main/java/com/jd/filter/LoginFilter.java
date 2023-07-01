package com.jd.filter;

import com.alibaba.fastjson.JSONObject;
import com.jd.entity.Result;
import com.jd.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse rsp = (HttpServletResponse) servletResponse;

        if ("OPTIONS".equals(req.getMethod())) {
            log.info("OPTIONS请求，直接放行......");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        rsp.setHeader("Access-Control-Allow-Origin", "*");
        rsp.setHeader("Content-type", "text/html;charset=utf-8");
        // 1.获取请求url
        String url = req.getRequestURL().toString();
        log.info("请求的url:{}", url);
        // 判断url末尾是不是login
        String[] str = url.split("/");
        if ("login".equals(str[str.length - 1])
                || "register".equals(str[str.length - 1])
                || "sendEmail".equals(str[str.length - 1])
                || "codeIsOk".equals(str[str.length - 1])) {
            log.info("是 【登录/注册】 页面，放行......");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 判断是不是商品展示页面，是则放行，不需要登录就可展示
        if ("goods".equals(str[str.length - 2])) {
            log.info("是 【商品展示】 页面，放行......");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 获取请求头的token
        String jwt = req.getHeader("token");
        if (!StringUtils.hasLength(jwt)) {
            log.info("token不存在，返回未登录信息......");
            Result error = Result.error("请先登录！！");
            // 手动把字符串转为json类型的字符串，阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            servletResponse.getWriter().write(notLogin);
            return;
        }
        // 解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtil.parseJWT(jwt);
        } catch (Exception e) {
//            e.printStackTrace();
            log.info("令牌解析失败，返回未登录的错误信息");
            Result error = Result.error("请先登录！！");
            // 手动把字符串转为json类型的字符串，阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            servletResponse.getWriter().write(notLogin);
            return;
        }
        // 放行
        log.info("令牌合法，放行......");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
