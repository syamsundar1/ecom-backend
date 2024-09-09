package com.ecom.search_service.config;

import com.ecom.search_service.constants.MessageConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue queue(){
        return new Queue(MessageConstants.QUEUE);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(MessageConstants.EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(MessageConstants.ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
