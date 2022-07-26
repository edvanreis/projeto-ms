package io.msclientes.application;

import io.msclientes.domain.Client;
import io.msclientes.infra.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    @Transactional
    public Client save(Client client){
        return repository.save(client);
    }


    public Optional<Client> findByCpf(String cpf){
        return repository.findByCpf(cpf);
    }

}
