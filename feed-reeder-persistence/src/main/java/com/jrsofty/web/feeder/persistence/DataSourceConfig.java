package com.jrsofty.web.feeder.persistence;

import javax.sql.DataSource;

import org.hibernate.dialect.MariaDB103Dialect;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("app.datasource")
    public DataSource getDataSource() {
        final HikariDataSource datasource = new HikariDataSource();

        datasource.setMinimumIdle(2);
        datasource.setMaximumPoolSize(5);
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

    private JpaVendorAdapter getJpaAdapter() {
        final HibernateJpaVendorAdapter jpaAdapter = new HibernateJpaVendorAdapter();
        jpaAdapter.setShowSql(true);
        jpaAdapter.setDatabasePlatform(MariaDB103Dialect.class.getName());
        return jpaAdapter;
    }

}
