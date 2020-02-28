package com.slx.boot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

/**
 *
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.slx.boot.mapper")
public class MybatisPlusConfig {

    @Resource
    private DruidConfig druidConfig;

    @Bean
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidConfig.config(dataSource);
        return dataSource;
    }

    /**
     * 配置mybatis-plus的分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
