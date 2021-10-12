package com.itrexgroup.model;

import lombok.*;

import java.util.NavigableMap;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString @EqualsAndHashCode
public class Customer {
    private int id;
    private NavigableMap<Long, Integer> warehouseDistanceMap;
}
