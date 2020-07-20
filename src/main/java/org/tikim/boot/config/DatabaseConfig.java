package org.tikim.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

	@Bean
	public DataSourceTransactionManager mybatisTransactionManager(DataSource dataSource) {
	  return new DataSourceTransactionManager(dataSource);
	}

}
