package oi.avaliadorcredito.msqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import oi.avaliadorcredito.domain.DataIssuanceOfCard;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestIssuanceOfCardPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queueIssuanceOfCard;

    public void IssuanceCard(DataIssuanceOfCard data) throws JsonProcessingException {
        var json = convertToJason(data);
        rabbitTemplate.convertAndSend(queueIssuanceOfCard.getName(),json);
    }

    private String convertToJason(DataIssuanceOfCard data) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
    }
}
