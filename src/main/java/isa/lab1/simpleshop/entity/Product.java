package isa.lab1.simpleshop.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {
    private long GTIN;
    private int size;
    private String type;
    private Brand brand;
}
