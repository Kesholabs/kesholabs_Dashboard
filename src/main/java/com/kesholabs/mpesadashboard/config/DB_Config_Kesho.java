//package com.kesholabs.mpesadashboard.config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//@PropertySource({ "classpath:application.properties" })
//@EnableJpaRepositories(
//        basePackages = "com.kesholabs.mpesadashboard.repo.Kesho",
//        entityManagerFactoryRef = "keshoEntityManagerFactory",
//        transactionManagerRef = "keshoTransactionManager"
//)
//public class DB_Config_Kesho {
//
//    @Primary
//    @Bean(name = "dataSource")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    // keshoEntityManagerFactory bean
//    @Primary
//    @Bean(name = "keshoEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("dataSource") DataSource dataSource
//    ) {
//        return builder
//                .dataSource(dataSource)
//                .packages("com.kesholabs.mpesadashboard.entity.Kesho")
//                .persistenceUnit("Kesho")
//                .build();
//    }
//
//
//    // keshoTransactionManager bean
//    @Primary
//    @Bean(name = "keshoTransactionManager")
//    public PlatformTransactionManager transactionManager(
//            @Qualifier("keshoEntityManagerFactory") EntityManagerFactory keshoEntityManagerFactory) {
//        return new JpaTransactionManager(keshoEntityManagerFactory);
//    }
//}
