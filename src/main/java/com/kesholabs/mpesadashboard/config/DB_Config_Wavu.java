//package com.kesholabs.mpesadashboard.config;
//
//
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.*;
//
//@Configuration
//@EnableTransactionManagement
//@PropertySource({ "classpath:application.properties" })
//@EnableJpaRepositories(
//        basePackages = "com.kesholabs.mpesadashboard.repo.Wavu",
//        entityManagerFactoryRef = "wavuEntityManagerFactory",
//        transactionManagerRef = "wavuTransactionManager"
//)
//public class DB_Config_Wavu {
//
//    @Bean(name = "wavuDataSource")
//    @ConfigurationProperties(prefix="spring.wavu")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    // wavuEntityManagerFactory bean
//    @Bean(name = "wavuEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean
//    barEntityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("wavuDataSource") DataSource dataSource
//    ) {
//        return
//                builder
//                        .dataSource(dataSource)
//                        .packages("com.kesholabs.mpesadashboard.entity.Wavu")
//                        .persistenceUnit("Wavu")
//                        .build();
//    }
//
//    // wavuTransactionManager bean
//    @Bean(name = "wavuTransactionManager")
//    public PlatformTransactionManager barTransactionManager(
//            @Qualifier("wavuEntityManagerFactory") EntityManagerFactory
//                    wavuEntityManagerFactory
//    ) {
//        return new JpaTransactionManager(wavuEntityManagerFactory);
//    }
//
//}
