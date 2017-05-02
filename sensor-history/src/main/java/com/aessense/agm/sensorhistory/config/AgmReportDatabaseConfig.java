package com.aessense.agm.sensorhistory.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.aessense.agm.sensorhistory.persistence.CustomRoutingDataSource;


/**
 * Configure JPA for the sensor history database.  This database is multitenant.
 * @author John Long
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.aessense.agm.sensorhistory.repository.agmreport", entityManagerFactoryRef = "sensorHistoryEntityManagerFactory")
@EnableTransactionManagement
@ImportResource("classpath:data-context.xml")
public class AgmReportDatabaseConfig {
   
    @Bean(name = "sensorHistoryEntityManagerFactory")
    public EntityManagerFactory sensorHistoryEntityManagerFactory(@Autowired @Qualifier("sensorHistoryDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPersistenceUnitName("sensor_history");
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.aessense");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.afterPropertiesSet();
        return em.getObject();
    }    
}
