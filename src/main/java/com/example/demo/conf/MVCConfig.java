package com.example.demo.conf;

import com.example.demo.model.User;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages="com.example.demo")
@EnableWebMvc
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
public class MVCConfig {
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setSuffix(".jsp");
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.example.demo.model");

        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put(
                "hibernate.dialect",
                "org.hibernate.dialect.PostgreSQL91Dialect"
        );
        hibernateProperties.put(
                "hibernate.show_sql",
                "true"
        );
        hibernateProperties.put(
                "hibernate.hbm2ddl.auto",
                "update"
        );
        hibernateProperties.setProperty(
                "hibernate.current.session.context.class",
                "org.springframework.orm.hibernate5.SpringSessionContext"
        );
        hibernateProperties.setProperty(
                "hibernate.jdbc.lob.non_contextual_creation",
                "true"
        );
        return hibernateProperties;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/rating");
        dataSource.setUsername("postgres");
        dataSource.setPassword("6031_PostgreSQL_1994_java");

        return dataSource;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }
}
