package com.jrsofty.web.feeder.persistence;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("file:${feeder.home}/conf/feeder.properties")
@EnableTransactionManagement
public class DataSourceConfig {

    @Value("${app.hikari.minIdle}")
    int minIdle;
    @Value("${app.hikari.maxPoolSize}")
    int maxPoolSize;
    @Value("${app.hibernate.dialect}")
    String hibernateDialect;

    @Bean
    @ConfigurationProperties("app.datasource")
    public DataSource getDataSource() {
        final HikariDataSource datasource = new HikariDataSource();

        datasource.setMinimumIdle(this.minIdle);
        datasource.setMaximumPoolSize(this.maxPoolSize);
        return datasource;

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

        emf.setDataSource(this.getDataSource());
        emf.setPackagesToScan("com.jrsofty.web.feeder.models.domain");
        emf.setJpaVendorAdapter(this.getJpaAdapter());
        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager(final EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    private JpaVendorAdapter getJpaAdapter() {
        final HibernateJpaVendorAdapter jpaAdapter = new HibernateJpaVendorAdapter();
        jpaAdapter.setShowSql(true);
        jpaAdapter.setDatabasePlatform(this.hibernateDialect);
        return jpaAdapter;
    }

}
