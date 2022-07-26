package oi.avaliadorcredito.appication;

import lombok.RequiredArgsConstructor;
import oi.avaliadorcredito.domain.DataEvaluation;
import oi.avaliadorcredito.domain.DataIssuanceOfCard;
import oi.avaliadorcredito.ex.DataClientNotFound;
import oi.avaliadorcredito.ex.ErroComunicationMicroserviceExeption;
import oi.avaliadorcredito.ex.issueOfCardException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluator-to-credit")
@RequiredArgsConstructor
public class EvaluatorToCreditController {

    private final EvaluatorToCreditService service;

    @GetMapping
    private String status(){
        return "ok";
    }

    @GetMapping(value = "situacion-client",params = "cpf")
    public ResponseEntity getSituacionClient(@RequestParam("cpf") String cpf){

        try{
            var situacionClient = service.creditAppraiser(cpf);
            return ResponseEntity.ok(situacionClient);

        }catch (DataClientNotFound e){
            return ResponseEntity.notFound().build();
        }catch (ErroComunicationMicroserviceExeption e){
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity carryOutEvaluation(@RequestBody DataEvaluation data){

        try{
            return ResponseEntity.ok(service.carryOutEvaluation(data.getCpf(),data.getIncome()));
        }catch (DataClientNotFound e){
            return ResponseEntity.notFound().build();
        }catch (ErroComunicationMicroserviceExeption e){
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping("issuance-card")
    public ResponseEntity issuanceOfCard(@RequestBody DataIssuanceOfCard data){

        try{
            return ResponseEntity.ok(service.issuanceOfCard(data));
        }catch (issueOfCardException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
