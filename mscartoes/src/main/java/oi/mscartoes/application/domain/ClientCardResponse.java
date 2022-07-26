package oi.mscartoes.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import oi.mscartoes.model.ClientCard;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@Builder
public class ClientCardResponse {

    private String name;
    private String flag;
    private BigDecimal releaseLimit;

    public static List<ClientCardResponse> fromModel(List<ClientCard> clientCards){
        return clientCards.stream()
                            .map(c->ClientCardResponse.builder()
                                    .name(c.getCard().getName())
                                    .flag(c.getCard().getCardFlag().toString())
                                    .releaseLimit(c.getLimitCard())
                                    .build()).collect(Collectors.toList());
    }
}
