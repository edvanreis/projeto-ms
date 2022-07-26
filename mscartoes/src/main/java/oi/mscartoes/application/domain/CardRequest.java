package oi.mscartoes.application.domain;

import lombok.Getter;
import lombok.Setter;
import oi.mscartoes.model.Card;
import oi.mscartoes.model.CardFlag;

import java.math.BigDecimal;

@Getter
@Setter
public class CardRequest {

    private String name;
    private CardFlag cardFlag;
    private BigDecimal income;
    private BigDecimal basicLimit;

    public Card toModel(){
        return Card.builder()
                .name(name)
                .cardFlag(cardFlag)
                .income(income)
                .basicLimit(basicLimit).build();
    }
}
