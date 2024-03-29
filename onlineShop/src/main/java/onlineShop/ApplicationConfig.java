package onlineShop;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class ApplicationConfig {

	@Bean(name = "sessionFactory")	// connect to DB
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("onlineShop.model");	// the address of the java classes, use them to generate DB tables
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");		// the driver to use to connect to DB (mysql jdbc)
		
		// TODO: Change port according to your database settings
		dataSource.setUrl("jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("root");

		return dataSource;
	}
	
    @Bean
    public MultipartResolver multipartResolver() {
		 CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		 multipartResolver.setMaxUploadSize(10240000);
		 return multipartResolver;
    }


	private final Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");  // create tables when Hibernate starts, drop them when Hibernate(the application) is turned off. Use "update" if don't want to drop
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");  // change to Oracle dialect if not using MySQL
		return hibernateProperties;
	}
}

