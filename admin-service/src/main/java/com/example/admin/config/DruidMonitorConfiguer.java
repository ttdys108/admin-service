package com.example.admin.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//@Configuration
public class DruidMonitorConfiguer {

    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean beanFilter = new FilterRegistrationBean();
        beanFilter.setFilter(new WebStatFilter());
        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");
        beanFilter.setInitParameters(initParams);
        beanFilter.setUrlPatterns(Arrays.asList("/*"));
        return  beanFilter;
    }

    @Bean
    public ServletRegistrationBean setStatViewServlet(){
        ServletRegistrationBean beanServlet = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>();
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        initParams.put("allow","");    /**默认就是允许所有访问*/
        beanServlet.setInitParameters(initParams);
        return  beanServlet;
    }

}
