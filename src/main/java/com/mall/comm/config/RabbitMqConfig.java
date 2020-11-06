package com.mall.comm.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
/**
 * @author haonan
 * create on 2020/4/15 10:32
 */
@Configuration
public class RabbitMqConfig {

    @Bean("firstRabbitConnectionFactory")
    @ConfigurationProperties(prefix = "spring.rabbitmq.first")
    @Primary
    public ConnectionFactory firstRabbitConnectionFactory() {
        return new CachingConnectionFactory();
    }

    @Bean(name = "firstRabbitTemplate")
    public RabbitTemplate firstRabbitTemplate(
            @Qualifier("firstRabbitConnectionFactory") ConnectionFactory connectionFactory
    ) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean(name="firstFactory")
    public SimpleRabbitListenerContainerFactory hospSyncFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("firstRabbitConnectionFactory") ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean("secondRabbitConnectionFactory")
    public ConnectionFactory secondRabbitConnectionFactory(
            @Value("${spring.rabbitmq.second.host}") String host,
            @Value("${spring.rabbitmq.second.port}") int port,
            @Value("${spring.rabbitmq.second.username}") String username,
            @Value("${spring.rabbitmq.second.password}") String password,
            @Value("${spring.rabbitmq.second.virtual-host}") String virtualHost) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        return connectionFactory;
    }

    @Bean(name="secondRabbitTemplate")
    public RabbitTemplate secondRabbitTemplate(
            @Qualifier("secondRabbitConnectionFactory") ConnectionFactory connectionFactory
    ){
        return new RabbitTemplate(connectionFactory);
    }

    @Bean(name="secondFactory")
    public SimpleRabbitListenerContainerFactory hPayFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("secondRabbitConnectionFactory") ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }
}
