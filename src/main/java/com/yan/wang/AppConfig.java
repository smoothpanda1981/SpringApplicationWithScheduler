package com.yan.wang;

import com.yan.wang.entity.TickerHourBtcUsd;
import com.yan.wang.entity.TickerHourEthUsd;
import com.yan.wang.service.TickerHourService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by ywang on 15.08.17.
 */
@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages="com.yan.wang")
@EnableTransactionManagement
//@EnableScheduling
public class AppConfig {

    @Autowired
    private Environment env;

    @Autowired
    private TickerHourService tickerHourService;

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        System.out.println(env.getProperty("db.driver"));
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }


    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());

        Properties props=new Properties();
        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

        sessionFactory.setHibernateProperties(props);
        sessionFactory.setAnnotatedClasses(TickerHourBtcUsd.class);
        sessionFactory.setAnnotatedClasses(TickerHourEthUsd.class);
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }

//    @Scheduled(fixedRate=5000)
//    public void doTask() {
//        System.out.println("Do Task...");
//        if (tickerHourService == null) {
//            System.out.println("null");
//        } else {
//            System.out.println("not null");
//            tickerHourService.getValueForTest();
//        }
//    }
}
