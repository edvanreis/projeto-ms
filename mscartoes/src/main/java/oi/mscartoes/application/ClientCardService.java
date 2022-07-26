package oi.mscartoes.application;

import lombok.RequiredArgsConstructor;
import oi.mscartoes.infra.repository.ClientCardRepository;
import oi.mscartoes.model.ClientCard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCardService {

    private final ClientCardRepository repository;

    public List<ClientCard>findByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
