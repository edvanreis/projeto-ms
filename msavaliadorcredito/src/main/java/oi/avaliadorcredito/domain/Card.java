package oi.avaliadorcredito.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Card {

    private Long id;
    private String name;
    private String cardFlag;
    private BigDecimal basicLimit;
}
