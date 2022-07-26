package oi.mscartoes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CardFlag cardFlag;
    private BigDecimal income;
    private BigDecimal basicLimit;

   /* @ManyToOne
    @JoinColumn(name = "client_card_id")
    private ClientCard clientCard;*/


}
