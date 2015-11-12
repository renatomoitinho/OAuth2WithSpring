package br.inf.audasi.domain.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.validation.Validation;
import javax.validation.Validator;
import java.beans.PropertyVetoException;

/**
 * @author renatomoitinhodias@gmail.com
 */
@Configuration
@ComponentScan("br.inf.audasi.domain")
@EnableJpaRepositories("br.inf.audasi.domain.repository")
@PropertySource({"classpath:jdbc.properties"})
@EnableTransactionManagement
public class ApplicationConfig {

    @Autowired
    private Environment env;

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.valueOf(env.getProperty("jdbc.database").toUpperCase()));
        jpaVendorAdapter.setDatabasePlatform(env.getProperty("jdbc.dialect"));
        jpaVendorAdapter.setShowSql(env.getProperty("jdbc.showSql", Boolean.class));
        jpaVendorAdapter.setGenerateDdl(false);

        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {
        LocalContainerEntityManagerFactoryBean managerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        managerFactoryBean.setDataSource(dataSource());
        managerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        managerFactoryBean.setPackagesToScan(env.getProperty("jdbc.scan.entity"));
        managerFactoryBean.afterPropertiesSet();

        return managerFactoryBean;
    }

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("jdbc.driverClassName"));
        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        dataSource.setUser(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setAcquireIncrement(Integer.valueOf(env.getProperty("jdbc.acquireIncrement")));
        dataSource.setMaxPoolSize(Integer.valueOf(env.getProperty("jdbc.maxPoolSize")));
        dataSource.setMinPoolSize(Integer.valueOf(env.getProperty("jdbc.minPoolSize")));
        dataSource.setInitialPoolSize(Integer.valueOf(env.getProperty("jdbc.initialPoolSize")));
        dataSource.setCheckoutTimeout(Integer.valueOf(env.getProperty("jdbc.timeout")));
        dataSource.setPreferredTestQuery(env.getProperty("jdbc.preferredTestQuery"));
        dataSource.setIdleConnectionTestPeriod(Integer.valueOf(env.getProperty("jdbc.idleConnectionTestPeriod")));
        return dataSource;
    }
}
