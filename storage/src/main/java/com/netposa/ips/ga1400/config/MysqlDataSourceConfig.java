package com.netposa.ips.ga1400.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * Author: zhangtao@netposa.com on 2019/3/7
 */
@Configuration
@MapperScan(basePackages = MysqlDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "mysqlSqlSessionFactory")
@Slf4j
public class MysqlDataSourceConfig {

    static final String PACKAGE = "com.netposa.ips.ga1400.mapper.mysql";
    static final String MAPPER_LOCATION = "classpath:mapper/mysql/*.xml";

    @Value("${mysql.datasource.url}")
    private String url;

    @Value("${mysql.datasource.username}")
    private String user;

    @Value("${mysql.datasource.password}")
    private String password;

    @Value("${mysql.datasource.driver-class-name}")
    private String driverClass;

    @Bean(name = "mysqlDataSource")
    @Primary
    public DataSource mysqlDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setPoolName("HikariPool-mysql");
        return dataSource;
    }

    @Bean(name = "mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager mysqlTransactionManager() {
        return new DataSourceTransactionManager(mysqlDataSource());
    }

    @Bean(name = "mysqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource mysqlDataSource)
            throws Exception {
        try {
            final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(mysqlDataSource);
//        //如需要写较为复杂的SQL必须要在XML文件中写的话可以放开此处
            sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MysqlDataSourceConfig.MAPPER_LOCATION));
            return sessionFactory.getObject();
        } catch (Exception e) {
            log.info("------->mysql connect exception");
        }
        return null;
    }

}
