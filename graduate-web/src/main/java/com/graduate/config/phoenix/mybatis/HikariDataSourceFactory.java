package com.graduate.config.phoenix.mybatis;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-15  15:14
 * @Description：数据源
 */
@Configuration
public class HikariDataSourceFactory extends UnpooledDataSourceFactory {
    public HikariDataSourceFactory() {
        this.dataSource = new HikariDataSource();
    }
}
