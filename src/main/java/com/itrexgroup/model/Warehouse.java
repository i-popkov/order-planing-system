package com.itrexgroup.model;

import lombok.*;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString @EqualsAndHashCode
public class Warehouse {
    int id;
    private String warehouseName;
    private Map<Integer, Integer> warehouseSupplies;
}
