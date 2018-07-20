package com.example.demo.config;

import com.example.demo.dao.UserDAO;
import com.example.demo.dao.UserDAOImpl;
import com.example.demo.model.User;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class AppConfig {
    @Bean
    public UserDAO userDAO() {
        return new UserDAOImpl();
    }

    @Bean
    public HibernateTemplate hibernateTemplate() {
        return new HibernateTemplate(sessionFactory());
    }

    @Bean
    public SessionFactory sessionFactory() {
//        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
//
//        // Create the MetadataSources
//        MetadataSources sources = new MetadataSources(registry);
//
//        // Create the Metadata
//        Metadata metadata = sources.getMetadataBuilder().build();
//
//        return metadata.getSessionFactoryBuilder().build();
        return new LocalSessionFactoryBuilder(dataSource())
                .addAnnotatedClasses(User.class)
                .buildSessionFactory();
    }


    @Bean
    DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/rating");
        dataSource.setUsername("postgres");
        dataSource.setPassword("6031_PostgreSQL_1994_java");

        return dataSource;
    }

    @Bean
    public HibernateTransactionManager hibTransMan(){
        return new HibernateTransactionManager(sessionFactory());
    }
}