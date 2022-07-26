package oi.avaliadorcredito.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.emissao-cartao}")
    private String topic;

    @Bean
    public Queue pushRequestIssuanceOfCard(){
        return new Queue(topic,true);
    }
}
