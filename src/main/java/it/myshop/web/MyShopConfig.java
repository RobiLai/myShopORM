package it.myshop.web;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import it.myshop.dao.ClienteDao;
import it.myshop.dao.impl.ClienteDaoImpl;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "it.myshop.controller")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "it.myshop-orm.repository", entityManagerFactoryRef = "emf", transactionManagerRef = "tmf")
public class MyShopConfig {

	@Bean
	public FreeMarkerViewResolver configureResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		
		resolver.setCache(true);
		resolver.setPrefix("");
		resolver.setSuffix(".ftl");
		
		return resolver;
	}
	
	@Bean
	public FreeMarkerConfigurer configureFreeMarker() {
	
	FreeMarkerConfigurer config = new FreeMarkerConfigurer();
	
	config.setTemplateLoaderPath("/WEB-INF/view/");
	
	return config;
	
	
	}
	
	@Bean
	public DataSource getDbConnection() {
		
		DriverManagerDataSource ds = new DriverManagerDataSource();
		
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUsername("root");
		//ds.setPassword(null);//non c'è
		ds.setUrl("jdbc:mysql://127.0.0.1/corso_spring");
		
		return ds;
		
	}
	
	@Bean(name = "emf")
	public LocalContainerEntityManagerFactoryBean getEntityManager() {
		
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		
		adapter.setDatabase(Database.MYSQL);
		//adapter.setGenerateDdl(true);//aggiorna le tabelle collegate alle entity
		
		factory.setDataSource(getDbConnection());
		factory.setJpaVendorAdapter(adapter);
		factory.setPackagesToScan(getClass().getPackage().getName());
		
		return factory;
		
	}
	
	@Bean(name = "tmf")
	public PlatformTransactionManager getTransactionManager() {
		JpaTransactionManager jtm = new JpaTransactionManager(getEntityManager().getObject());//abilita il nostro manager a fare transazioni
		
		//jtm.setEntityManagerFactory(getEntityManager().getObject()); un altro modo di quello sopra 
		
		return jtm;
	}
	
	
	@Bean
	public ClienteDao getClienteService() {
		return new ClienteDaoImpl();
	}
	
//	@Bean
//	public ProdottoDao getProdottoService() {
//		return new ProdottoDaoImpl(getDbConnection());
//	}
//	
//	@Bean
//	public CategoriaDao getCategoriaService() {
//		return new CategoriaDaoImpl(getDbConnection());
//	}
	
	
}
