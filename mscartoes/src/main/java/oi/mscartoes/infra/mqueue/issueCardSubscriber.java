package oi.mscartoes.infra.mqueue;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import oi.mscartoes.application.domain.DataIssuanceOfCard;
import oi.mscartoes.infra.repository.CardRepository;
import oi.mscartoes.infra.repository.ClientCardRepository;
import oi.mscartoes.model.ClientCard;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class issueCardSubscriber {

    private final CardRepository repository;

    private final ClientCardRepository clientCardRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartao}")
    public void receiveRequest(String payload){
       try{
        var mapper = new ObjectMapper();
       var data = mapper.readValue(payload, DataIssuanceOfCard.class);
       var card = repository.findById(data.getCardId()).orElseThrow();
          var clientcard = ClientCard.builder()
                        .card(card)
                        .cpf(data.getCpf())
                        .limitCard(data.getLimitApproved())
                   .build();
           clientCardRepository.save(clientcard);
       }catch (Exception e){
            e.printStackTrace();
       }

    }
}
