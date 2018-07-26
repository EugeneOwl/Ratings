package com.example.demo.conf;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:application.properties"})
public class MVCConfig {
    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setJpaProperties(hibernateProperties());
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.example.demo.model");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        return em;
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setSuffix(".jsp");
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put(
                "hibernate.dialect",
                env.getRequiredProperty("hibernate.dialect")
        );
        hibernateProperties.put(
                "hibernate.show_sql",
                env.getRequiredProperty("hibernate.show_sql")
        );
        hibernateProperties.put(
                "hibernate.hbm2ddl.auto",
                env.getRequiredProperty("hibernate.hbm2ddl.auto")
        );
        hibernateProperties.setProperty(
                "hibernate.current.session.context.class",
                env.getRequiredProperty("hibernate.current.session.context.class")
        );
        hibernateProperties.setProperty(
                "hibernate.jdbc.lob.non_contextual_creation",
                env.getRequiredProperty("hibernate.jdbc.lob.non_contextual_creation")
        );
        return hibernateProperties;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("datasource.driver"));
        dataSource.setUrl(env.getRequiredProperty("datasource.url"));
        dataSource.setUsername(env.getProperty("datasource.username"));
        dataSource.setPassword(env.getRequiredProperty("datasource.password"));

        return dataSource;
    }

//    @Bean
//    public HibernateTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean sessionFactory) {
//        HibernateTransactionManager txManager = new HibernateTransactionManager();
//        txManager..setSessionFactory(sessionFactory);
//        return txManager;
//    }
}
