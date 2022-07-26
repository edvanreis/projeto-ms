package oi.avaliadorcredito.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ClientCard {

    private String name;
    private String flag;
    private BigDecimal releaseLimit;
}
