package com.nilu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class TransactionConfig {
	
	 // Implementation PlatformTransactionManager add
		@Bean
	 PlatformTransactionManager add(MongoDatabaseFactory dbFactory) {
			return new MongoTransactionManager(dbFactory);
		}
		

}
