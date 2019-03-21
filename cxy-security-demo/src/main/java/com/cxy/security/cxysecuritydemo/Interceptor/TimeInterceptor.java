package com.cxy.security.cxysecuritydemo.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/***
 * Created by Caoxingyun on 2019/03/15
 * 过滤器只能知道请求的开始和返回 并不知道具体是哪个方法执行的，方法执行的参数等
 *  这时候就需要拦截器，拦截获取相关信息并进行逻辑处理
 *  过滤器是在doFilter中 filterChain.doFilter(request,response)前后处理方法执行前后的逻辑的
 *  而Interceptor是在preHandle执行方法执行前的逻辑
 *  在postHandle执行方法执行后的逻辑
 */
//执行顺序 过滤器初始化init 过滤器doFilter前 拦截器preHandle
//执行请求方法  拦截器postHandle 拦截器afterCompletion 过滤器doFilter后  过滤器destroy

@Component  //只加component 注解并不能使拦截器起作用，还需要在config中配置
public class TimeInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    //执行方法前调用
    //object 中包含要执行的方法的信息
    //true 表示继续调用方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle ");
        log.info(((HandlerMethod) handler).getMethodParameters().toString());
        log.info(((HandlerMethod) handler).getBean().getClass().getName());
        log.info(((HandlerMethod) handler).getMethod().getName());
        log.info(((HandlerMethod) handler).getReturnType().toString());
        request.setAttribute("startTime", new Date().getTime());
        return true;
    }

    //执行方法后调用,如果方法抛出异常该方法就不会被调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
        Long startTime = (Long) request.getAttribute("startTime");
        log.info("耗时:" + (new Date().getTime() - startTime) + "ms");
    }

    //该方法在方法执行后被调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
        //exception 是方法抛出异常时的异常信息
        //如果自定义了异常处理器那么异常就不会在这被捕获
        log.info("afterCompletion");
        Long startTime = (Long) request.getAttribute("startTime");
        log.info("耗时:" + (new Date().getTime() - startTime) + "ms");
    }
}
