package oi.avaliadorcredito.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class CardApproved {

    private String card;
    private String flag;
    private BigDecimal limitApproved;
}
