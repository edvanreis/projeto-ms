package oi.mscartoes.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oi.mscartoes.application.domain.CardRequest;
import oi.mscartoes.application.domain.ClientCardResponse;
import oi.mscartoes.model.Card;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardResource {

    private final CardService service;
    private final ClientCardService clientCardService;

    @GetMapping
    private String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody CardRequest cardRequest){
        var card = cardRequest.toModel();
        service.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCards(@RequestParam("income") Long income){
        var list = service.getCards(income);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<ClientCardResponse>> getCards(@RequestParam("cpf") String cpf){
        var list = clientCardService.findByCpf(cpf);
        return ResponseEntity.ok(ClientCardResponse.fromModel(list));
    }



}
