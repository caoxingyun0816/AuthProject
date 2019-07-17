package com.cxy.security.cxysecuritydemo.dataAuthority;



import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Sonic Wang
 * @create 2019-04-16 17:38
 * /***
 *  * Created by Caoxingyun on 2019/07/17
 *  * SQL执行拦截器，在执行前，增强SQL，加入对应id的in条件 egg brand_id in ('1','2')
 *
 *  使用的是mybatis
 *  *
 *  */
//@Component
//@Intercepts({@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class})})
//public class DataInterceptor implements Interceptor {
//
//    protected final Logger logger = LogManager.getLogger(this.getClass());
//
//    @Autowired
//    private SelectVisitor selectVisitor;
//
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        StatementHandler handler = (StatementHandler) invocation.getTarget();
//        //由于mappedStatement为protected的，所以要通过反射获取
//        MetaObject statementHandler = SystemMetaObject.forObject(handler);
//        //mappedStatement中有我们需要的方法id
//        MappedStatement mappedStatement = (MappedStatement) statementHandler.getValue("delegate.mappedStatement");
//        //获取sql
//        String sql = handler.getBoundSql().getSql();
//        //获得方法类型
//        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
//
//
//        if ("SELECT".equals(sqlCommandType.toString())) {
//
//            try {
//                String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//                if (StringUtils.isBlank(userId) || "APP".equals(userId)) {
//                    return invocation.proceed();
//                }
//            } catch (Exception e) {
//                this.logger.info("加载用户数据权限时，获取不到当前用户id！");
//                return invocation.proceed();
//            }
//
//            //获取方法id
//            String id = mappedStatement.getId();
//            String classFullName = id.substring(0, id.lastIndexOf("."));
//            String methodName = id.substring(id.lastIndexOf(".")).replace(".", "");
//
//            Proxy mapperProxy = (Proxy) SpringContextUtils.getBean(Class.forName(classFullName));
//            MapperProxy proxy = (MapperProxy) Proxy.getInvocationHandler(mapperProxy);
//            Field methodCache = proxy.getClass().getDeclaredField("methodCache");
//            methodCache.setAccessible(true);
//            ConcurrentHashMap map = (ConcurrentHashMap) methodCache.get(proxy);
//
//            boolean isCollection = false;
//            for (Object obj : map.keySet()) {
//                Method method = (Method) obj;
//                if (!method.getName().equals(methodName.replace("_COUNT", ""))) {
//                    continue;
//                }
//                Class<?> returnType = method.getReturnType();
//                if (Collection.class.isAssignableFrom(returnType)) {
//                    isCollection = true;
//                }
//            }
//
//            if (!isCollection || id.contains("getProjectBySideNames") || id.contains("getBySideNames")) {
//                return invocation.proceed();
//            }
//            Select select = (Select) CCJSqlParserUtil.parse(sql);
//            //调用selectVisitor增强SQL
//            select.getSelectBody().accept(selectVisitor);
//            //将增强后的sql放回
//            statementHandler.setValue("delegate.boundSql.sql", select.toString());
//        }
//        return invocation.proceed();
//    }
//
//    @Override
//    public Object plugin(Object target) {
//        return Plugin.wrap(target, this);
//    }
//
//    @Override
//    public void setProperties(Properties properties) {
//
//    }
//}
