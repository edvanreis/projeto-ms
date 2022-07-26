package oi.avaliadorcredito.appication;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import oi.avaliadorcredito.domain.*;
import oi.avaliadorcredito.ex.DataClientNotFound;
import oi.avaliadorcredito.ex.ErroComunicationMicroserviceExeption;
import oi.avaliadorcredito.ex.issueOfCardException;
import oi.avaliadorcredito.infra.clients.ClientResourceClient;
import oi.avaliadorcredito.infra.clients.card.CardResourcesClient;
import oi.avaliadorcredito.msqueue.RequestIssuanceOfCardPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EvaluatorToCreditService {

    private final ClientResourceClient resourceClient;

    private final CardResourcesClient resourcesCard;

    private final RequestIssuanceOfCardPublisher publisher;

    public SituacionClient creditAppraiser(String cpf) throws DataClientNotFound, ErroComunicationMicroserviceExeption {

        try{

            ResponseEntity<DataClient> responseEntity = resourceClient.getClient(cpf);

            ResponseEntity<List<ClientCard>> responseCards = resourcesCard.getCardsByCpf(cpf);

            return SituacionClient.builder()
                                     .client(responseEntity.getBody())
                                     .cards(responseCards.getBody())
                                     .build();
        }catch (FeignException.FeignClientException e){
            int status = e.status();

            if(HttpStatus.NOT_FOUND.value()==status){
                throw new DataClientNotFound();
            }
            throw new ErroComunicationMicroserviceExeption(e.getMessage(), status);

        }
    }

    public ReturnClientReview carryOutEvaluation(String cpf,Long income) throws DataClientNotFound, ErroComunicationMicroserviceExeption {
        try{

            ResponseEntity<DataClient> responseEntity = resourceClient.getClient(cpf);

            ResponseEntity<List<Card>> responseCards = resourcesCard.getCards(income);

            var cards = responseCards.getBody();

            var listCardsAp = cards.stream().map(c->{

                var dataClient = responseEntity.getBody();

                BigDecimal limitBasic = c.getBasicLimit();
                BigDecimal incomeBD = BigDecimal.valueOf(income);
                BigDecimal ageBD = BigDecimal.valueOf(dataClient.getAge());

                var fator = ageBD.divide(BigDecimal.valueOf(10));
                var limitAp = fator.multiply(limitBasic);


               return  CardApproved.builder()
                        .card(c.getName())
                        .flag(c.getCardFlag())
                        .limitApproved(limitAp)
                        .build();

            }).collect(Collectors.toList());

            return ReturnClientReview.builder().cards(listCardsAp).build();
        }catch (FeignException.FeignClientException e){
            int status = e.status();

            if(HttpStatus.NOT_FOUND.value()==status){
                throw new DataClientNotFound();
            }
            throw new ErroComunicationMicroserviceExeption(e.getMessage(), status);

        }
    }

    public ProtocolCard issuanceOfCard(DataIssuanceOfCard data){
        try{
            publisher.IssuanceCard(data);
            var protocol = UUID.randomUUID().toString();
            return new ProtocolCard(protocol);
        }catch (Exception e){
            throw new issueOfCardException(e.getMessage());
        }
    }
}
