package com.learning.babelbox;

import com.learning.babelbox.domain.Translation;
import com.learning.babelbox.domain.TranslationKnowledge;
import com.learning.babelbox.domain.User;
import com.learning.babelbox.repository.TranslationKnowledgeRepository;
import com.learning.babelbox.repository.TranslationRepository;
import com.learning.babelbox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.util.List;

import static java.util.Arrays.asList;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@Profile("server")
public class Application {

    @Value("${database.name}")
    private String databaseName;

    private String[] packagesToScanPath = {
            "com.learning.babelbox.features",
            "com.learning.babelbox.domain",
            "com.learning.babelbox.platform"
    };

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl("jdbc:postgresql://localhost:5432/" + databaseName);
        ds.setUsername("thomas");
        ds.setPassword("sa");
        ds.setDriverClassName("org.postgresql.Driver");
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(packagesToScanPath);
        factory.setDataSource(dataSource());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    public static void fixDB(ApplicationContext context) {
        List<Translation> translations = context.getBean(TranslationRepository.class).findAll();
        User user = context.getBean(UserRepository.class).findByEmail("test@yopmail.com");
        TranslationKnowledgeRepository repository = context.getBean(TranslationKnowledgeRepository.class);
        for (Translation translation : translations) {
            repository.save(new TranslationKnowledge(translation, user));
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        asList(context.getBeanDefinitionNames()).forEach(name -> System.out.println(name));
        //fixDB(context);
    }
}
