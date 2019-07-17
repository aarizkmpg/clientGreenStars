package com.client.demo.spring.config;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("com.client.demo.spring")})
public class SystemConfig {

	@Autowired
	private ApplicationContext context;

	private LocalSessionFactoryBean getSessionFactoryBean(String conf){
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		String resourcePath = "classpath:" + conf;
		if(Config.getInstance().getConfig().containsKey("system_property")){
			resourcePath = "file:" + Config.getInstance().getConfig().getProperty("system_property") + File.separator + conf;
		}
		System.out.println("Load config for resource :"+resourcePath);
		factoryBean.setConfigLocation(context.getResource(resourcePath));
		return factoryBean;
	}

	private void setAnotatedClasses(LocalSessionFactoryBean factoryBean,String basePackage){
		ArrayList<Class> classes = new ArrayList<Class>();
		// the following will detect all classes that are annotated as @Entity
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(true);
		scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
		for (BeanDefinition bd : scanner.findCandidateComponents(basePackage)) {
			String name = bd.getBeanClassName();
			try {
				System.out.print(name + ".class");
				classes.add(Class.forName(name));
			} catch (Exception E) {
				E.printStackTrace();
			}
		} // for
		// register detected classes with AnnotationSessionFactoryBean
		System.out.println("---------------------:"+classes.size());
		factoryBean.setAnnotatedClasses(classes.toArray(new Class[classes.size()]));
	}

	@Bean(name="sessionFactory")
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = getSessionFactoryBean("hibernate.cfg8.xml");
		setAnotatedClasses(factoryBean,"com.client.demo.spring.model.demo");
		return factoryBean;
	}

	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}

	@Bean(name="mongoDB")
	public MongoDatabase mongoDbFactory(MongoProperty mongo) {
		MongoClientOptions options = MongoClientOptions.builder()
				.threadsAllowedToBlockForConnectionMultiplier(mongo.getMultiplier())

				.connectionsPerHost(mongo.getConnectionsPerHost())
				.connectTimeout(mongo.getConnectTimeout())
				.maxWaitTime(mongo.getMaxWaitTime())
				.socketTimeout(mongo.getSocketTimeout())
				.build();

		List<MongoCredential> credentials = new ArrayList<>();
		if (mongo.getUsername() != null && mongo.getPassword() != null && !mongo.getUsername().trim().isEmpty() && !mongo.getPassword().trim().isEmpty()) {
			credentials.add(MongoCredential.createCredential(mongo.getUsername(), mongo.getUsername(), mongo.getPassword().toCharArray()));
		}

		MongoClient client = new MongoClient(new ServerAddress(mongo.getHost(), mongo.getPort()),credentials, options);
		return client.getDatabase(mongo.getDbname());
	}
}
