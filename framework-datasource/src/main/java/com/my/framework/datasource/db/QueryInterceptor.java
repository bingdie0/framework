package com.my.framework.datasource.db;

import com.github.pagehelper.Dialect;
import com.github.pagehelper.Page;
import com.github.pagehelper.dialect.helper.MySqlDialect;
import com.my.framework.common.util.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-17
 **/
public class QueryInterceptor implements Interceptor, Serializable {
    private static final long serialVersionUID = 4986333021793702268L;

    private Dialect dialect = new MySqlDialect();

    /**
     * 拦截的Id，在mapper中的id，可以匹配正则
     */
    private String sqlPattern = ".*query.*";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
            BaseStatementHandler delegate = (BaseStatementHandler) ReflectionUtils.getFieldValue(statementHandler,
                    "delegate");
            MappedStatement mappedStatement = (MappedStatement) ReflectionUtils.getFieldValue(delegate,
                    "mappedStatement");
            // 拦截需要分页的sql
            if (mappedStatement.getId().matches(sqlPattern)) {
                BoundSql boundSql = delegate.getBoundSql();
                String originalSql = boundSql.getSql();
                if (StringUtils.isBlank(originalSql)) {
                    return invocation.proceed();
                }

                Map parameterObject = (Map) boundSql.getParameterObject();
                Page page = SQLHelp.getPage(parameterObject);
                if (Objects.nonNull(page)) {
//                    String pageSql = SQLHelp.generatePageSql(originalSql, page, );
                }
            }

        }
        return null;
    }

    @Override
    public Object plugin(Object o) {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
