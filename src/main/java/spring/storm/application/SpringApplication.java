package spring.storm.application;

import java.util.Properties;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * 
 * @author agarg
 * This is the main Configuration Class where all Spring Configurations are defined.
 * Equivalent to spring-context.xml if using Spring XML configuration
 */
@Configuration
@ComponentScan(basePackages={"spring.storm"})
public class SpringApplication {

	
	  @Bean
	  public static PropertySourcesPlaceholderConfigurer properties() {
	    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
	    YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
	    yaml.setResources(new ClassPathResource("application-common_mysql_db.yml"),new ClassPathResource("application-common_mongo_db.yml"),new ClassPathResource("application-common_rabbitmq.yml"));
	    yaml.afterPropertiesSet();
	    Properties prop = yaml.getObject();
	    propertySourcesPlaceholderConfigurer.setProperties(prop);
	    return propertySourcesPlaceholderConfigurer;
	  }
}
