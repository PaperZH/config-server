package com.ucar.qtc.admin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author cong.zhou01
 * @name DruidConfig
 * @description druid 配置
 * @date 2018-09-06 14:20
 */
@Configuration
public class DruidConfig {

    /**
     * druid属性配置
     * @param propertityConfig
     * @return
     */
    @Bean
    @Primary
    public DataSource druidDataSource(DruidPropertityConfig propertityConfig){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(propertityConfig.getUrl());
        dataSource.setUsername(propertityConfig.getUsername());
        dataSource.setPassword(propertityConfig.getPassword());
        dataSource.setDriverClassName(propertityConfig.getDriverClassName());
        dataSource.setInitialSize(propertityConfig.getInitialSize());
        dataSource.setMinIdle(propertityConfig.getMinIdle());
        dataSource.setMaxActive(propertityConfig.getMaxActive());
        dataSource.setMaxWait(propertityConfig.getMaxWait());
        dataSource.setTimeBetweenEvictionRunsMillis(propertityConfig.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(propertityConfig.getMinEvictableIdleTimeMillis());
        dataSource.setTestWhileIdle(propertityConfig.isTestWhileIdle());
        dataSource.setTestOnBorrow(propertityConfig.isTestOnBorrow());
        dataSource.setTestOnReturn(propertityConfig.isTestOnReturn());
        dataSource.setPoolPreparedStatements(propertityConfig.isPoolPreparedStatements());
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(propertityConfig.getMaxPoolPreparedStatementPerConnectionSize());

        try {
            dataSource.setFilters(propertityConfig.getFilters());
        } catch (SQLException e) {
            System.err.println("druid configuration initialization filter: "+ e);
        }
        return dataSource;
    }

    /**
     * 注册一个druidStatViewServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        servletRegistrationBean.addInitParameter("allow","");
        servletRegistrationBean.addInitParameter("resetEnable","true");
        return servletRegistrationBean;
    }

    /**
     * 注册一个druidStatFilter
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
