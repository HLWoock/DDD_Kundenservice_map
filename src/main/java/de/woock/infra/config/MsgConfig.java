package de.woock.infra.config;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@EnableJms
@Configuration
public class MsgConfig {

	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
	                                      DefaultJmsListenerContainerFactoryConfigurer configurer) throws JMSException {
	    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	    factory.setConnectionFactory(connectionFactory);
//	    factory.setConcurrency("5-10");
	    factory.setPubSubDomain(true);
	    factory.setClientId("Kundenservice");
	    factory.setSubscriptionDurable(false);
	    configurer.configure(factory, connectionFactory);
	    return factory;
	}

	@Bean
	@Profile("windows")
	public ActiveMQConnectionFactory connectionFactory_windows() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
		factory.setTrustAllPackages(true);
		return factory;
	}
	
	@Bean
	@Profile("linux")
	public ActiveMQConnectionFactory connectionFactory_linux() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://activemq:61616");
		factory.setTrustAllPackages(true);
		return factory;
	}
	
	@Bean
	public JmsTemplate jmsTemplate(ActiveMQConnectionFactory connectionFactory) {
	    JmsTemplate template = new JmsTemplate();
	    template.setConnectionFactory(connectionFactory);
	    template.setPubSubDomain(false);
	    return template;
	}

}
