package oi.avaliadorcredito.infra.clients;

import oi.avaliadorcredito.domain.DataClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclients",path = "/client")
public interface ClientResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<DataClient> getClient(@RequestParam("cpf") String cpf);
}
