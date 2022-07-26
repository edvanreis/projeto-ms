package oi.avaliadorcredito.infra.clients.card;

import oi.avaliadorcredito.domain.Card;
import oi.avaliadorcredito.domain.ClientCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes",path = "/card")
public interface CardResourcesClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<ClientCard>> getCardsByCpf(@RequestParam("cpf") String cpf);

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCards(@RequestParam("income") Long income);
}
