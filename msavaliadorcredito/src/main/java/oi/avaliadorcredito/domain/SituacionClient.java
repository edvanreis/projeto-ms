package oi.avaliadorcredito.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SituacionClient {

    private DataClient client;
    private List<ClientCard> cards;
}
