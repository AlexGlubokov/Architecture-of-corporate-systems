package com.architecture.config;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.Topic;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan(basePackages = "com.architecture")
public class AppConfig implements WebMvcConfigurer {

    
    @Bean
    public DataSource dataSource() {
        return lookupJndi("java:comp/env/jdbc/CarDS", DataSource.class);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return lookupJndi("java:comp/env/jms/ConnectionFactory", ConnectionFactory.class);
    }

    @Bean
    public Topic auditTopic() {
        return lookupJndi("java:comp/env/jms/AuditTopic", Topic.class);
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate(connectionFactory());
        template.setDefaultDestination(auditTopic());
        template.setPubSubDomain(true); 
        return template;
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.architecture.model");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        em.setJpaProperties(properties);
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    private <T> T lookupJndi(String name, Class<T> type) {
        JndiObjectFactoryBean jndi = new JndiObjectFactoryBean();
        jndi.setJndiName(name);
        jndi.setResourceRef(true);
        jndi.setProxyInterface(type);
        try {
            jndi.afterPropertiesSet();
            return (T) jndi.getObject();
        } catch (Exception e) {
            throw new RuntimeException("JNDI lookup failed" + name, e);
        }
    }
}
