package web.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "web.model")
public class HibernateConfig {

    // vl from video: you can replace it on @Resource (like in video)
    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        // VL (changed on video) DriverManagerDataSource dataSource = new DriverManagerDataSource();
        BasicDataSource ds = new BasicDataSource(); // check: from org.apache.commons dpcp2 ...apache.dbcp2
        ds.setUrl(env.getRequiredProperty("db.url")); // see in video 5:31
        ds.setDriverClassName(env.getProperty("db.driver"));
        ds.setUsername(env.getProperty("db.username"));
        ds.setPassword(env.getProperty("db.password"));

        ds.setInitialSize(Integer.valueOf(env.getRequiredProperty("db.initialSize")));
        ds.setMinIdle(Integer.valueOf(env.getRequiredProperty("db.minIdle")));
        ds.setMaxIdle(Integer.valueOf(env.getRequiredProperty("db.maxIdle")));
        ds.setTimeBetweenEvictionRunsMillis(Long.valueOf(env.getRequiredProperty("db.timeBetweenEvictionRunsMillis")));
        ds.setMinEvictableIdleTimeMillis(Long.valueOf(env.getRequiredProperty("db.minEvictableIdleTimeMillis")));
        ds.setTestOnBorrow(Boolean.valueOf(env.getRequiredProperty("db.testOnBorrow")));
        ds.setValidationQuery(env.getRequiredProperty("db.validationQuery"));
        return ds;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getDataSource());
        em.setPackagesToScan("web.model");
        // time 11:15 +-
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(getHiberyateProperties());
        return em;
    }

    public Properties getHiberyateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.connection.driver_class", env.getProperty("hibernate.connection.driver_class"));
        properties.put("hibernate.connection.characterEncoding", env.getProperty("hibernate.connection.characterEncoding"));
        properties.put("hibernate.connection.CharSet", env.getProperty("hibernate.connection.CharSet"));
        properties.put("hibernate.connection.useUnicode", env.getProperty("hibernate.connection.useUnicode"));

        return properties;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory().getObject());
        return manager;
    }

}
