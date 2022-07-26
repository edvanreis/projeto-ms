package oi.mscartoes.application.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DataIssuanceOfCard {

    private Long cardId;
    private String cpf;
    private String address;
    private BigDecimal limitApproved;
}
