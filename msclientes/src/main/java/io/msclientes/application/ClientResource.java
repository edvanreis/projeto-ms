package io.msclientes.application;

import io.msclientes.application.request.ClientRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientResource {

    private final ClientService service;

    @GetMapping
    private String status(){
        log.info("Obtendo dados do microserviço de cliente");
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClientRequest request){
        var client = request.toModel();
         service.save(client);
        URI headerLocation = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .query("cpf={cpf}")
                            .buildAndExpand(client.getCpf())
                            .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity getClient(@RequestParam("cpf") String cpf){
        var client = service.findByCpf(cpf);
        if(!client.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }
}
