package by.bsu.automobile.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Sergey on 23.10.2016.
 */

@Configuration
@EnableTransactionManagement
@ComponentScan({"by.bsu.automobile.configuration"})
@PropertySource(value = {"classpath:application.properties"})
@Import({by.bsu.automobile.configuration.HibernateConfiguration.class})
public class HibernateConfigurationTest {

}
