package com.itrexgroup.model;

import lombok.*;

@RequiredArgsConstructor
@Getter @Setter
@ToString @EqualsAndHashCode
public class Order {
    private int id;
    private final Customer customer;
    private final Product product;
    private final int quantity;
    private Warehouse warehouse;
}
