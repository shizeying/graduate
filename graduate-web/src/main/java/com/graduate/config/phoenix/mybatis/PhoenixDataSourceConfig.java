package com.graduate.config.phoenix.mybatis;


import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;


/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-15  15:15
 * @Description：<描述>
 */
@Configuration
@MapperScan(basePackages = PhoenixDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "PhoenixSqlSessionFactory")
public class PhoenixDataSourceConfig {
    static final String PACKAGE = "com.graduate.mapper.*";

    @Bean(name = "PhoenixDataSource")
    @Primary
    public DataSource phoenixDataSource() throws IOException {
        ResourceLoader loader = new DefaultResourceLoader();
        InputStream inputStream = loader.getResource("classpath:application.properties")
                .getInputStream();
        Properties properties = new Properties();
        properties.setProperty("phoenix.query.timeoutMs", "12000");
        properties.setProperty("hbase.rpc.timeout", "12000");
        properties.setProperty("hbase.client.scanner.timeout.period", "12000");
        properties.setProperty("hbase.client.scanner.caching","100000");
        properties.setProperty("zookeeper.session.timeout","12000");
        properties.load(inputStream);
        Set<Object> keys = properties.keySet();
        Properties dsProperties = new Properties();
        for (Object key : keys) {
            if (key.toString().startsWith("datasource")) {
                dsProperties.put(key.toString().replace("datasource.", ""), properties.get(key));
            }
        }
        HikariDataSourceFactory factory = new HikariDataSourceFactory();
        factory.setProperties(dsProperties);
        inputStream.close();
        return factory.getDataSource();
    }

    @Bean(name = "PhoenixSqlSessionFactory")
    @Primary
    public SqlSessionFactory phoenixSqlSessionFactory(
            @Qualifier("PhoenixDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        ResourceLoader loader = new DefaultResourceLoader();
        String resource = "classpath:mybatis-config.xml";
        factoryBean.setConfigLocation(loader.getResource(resource));
        factoryBean.setSqlSessionFactoryBuilder(new SqlSessionFactoryBuilder());
        return factoryBean.getObject();
    }
}
