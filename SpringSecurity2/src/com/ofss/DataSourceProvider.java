package com.ofss;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:db.properties")
public class DataSourceProvider {

	@Autowired
	private Environment env;

	@Bean
	public DataSource getDataSource()
	{
		System.out.println("Entering getDataSource method");
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("oracle.driver"));
		dataSource.setUrl(env.getProperty("oracle.jdbcUrl"));
		dataSource.setUsername(env.getProperty("oracle.username"));
		dataSource.setPassword(env.getProperty("oracle.password"));
		return dataSource;
	}

}
