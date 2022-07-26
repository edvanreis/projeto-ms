package io.msclientes.application.request;

import io.msclientes.domain.Client;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequest {

    private String cpf;
    private String name;
    private Integer age;

    public Client toModel(){
        return Client.builder()
                .name(name)
                .cpf(cpf)
                .age(age).build();
    }
}
