package com.cxy.security.cxysecuritydemo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/***
 * Created by Caoxingyun on 2019/03/14
 * Filter 可以在request到达前进行操作，可以修改请求参数
 *        可以再response返回前对response响应进行修改
 *        项目启动时执行过滤器的init方法
 *          调用方法时拦截请求，进入doFilter方法进行逻辑处理
 *          当方法结束返回时调用destroy方法
 *          如何将第三方中没用component注解的filter加入到springboot的项目中的过滤器链上
 */

/***
 * 认证在多个请求之间共享
 * 认证信息Authencation放到SecurityContext中
 * 再放到SecurityContextHolder中。
 * 从SecurityContextHolder 中都可以获得当前登录的认证信息。
 * SecurityContextPersistenceFilter
 * 在过滤器的最顶端，当请求到达时验证session是否有验证信息
 * 有就放到线程里。在请求结束后拿出线程里的 验证信息放到session中。
 * 不同的请求都可以在同一个session中拿到认证信息。
 * 一个请求之间都可以在SecurityContextHolder中拿到认证信息。
 * 获取用户信息
 * SecurityContextHolder.getContext().getAuthentication();
 */
//@Component
public class TimeFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("my timeFilter init!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        logger.info("my timeFilter start!");
        long startTime = new Date().getTime();
        //执行下一个过滤器
        //在没有新的filter的情况下，doFilter（）返回当前的请求
        filterChain.doFilter(request,response);
        logger.info("耗时:" + (new Date().getTime() - startTime));
        logger.info("my timeFilter end!");
    }

    @Override
    public void destroy() {
        logger.info("my timeFilter destroy!");
    }
}
