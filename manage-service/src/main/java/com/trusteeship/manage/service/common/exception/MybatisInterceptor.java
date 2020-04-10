package com.trusteeship.manage.service.common.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class MybatisInterceptor implements Interceptor {
    private static final String CREATE_TIME_NAME="createTime";
    private static final String UPDATE_TIME_NAME="updateTime";
    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object object = invocation.getArgs()[1];
        //sql类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        if (SqlCommandType.INSERT.equals(sqlCommandType)) {
            //插入操作时，自动更新 create_time update_time
            updateTimeField(object,CREATE_TIME_NAME);
            updateTimeField(object,UPDATE_TIME_NAME);
        }
        if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
            //update时，自动更新update_time
            updateTimeField(object,UPDATE_TIME_NAME);
        }
        return invocation.proceed();
    }

    private void updateTimeField(Object object,String fieldTimeName) throws Throwable{
        Field[] fields = object.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if(fields[i].getName().equals(fieldTimeName)){
                fields[i].setAccessible(true);
                fields[i].set(object, new Date());
                break;
            }
        }

    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}