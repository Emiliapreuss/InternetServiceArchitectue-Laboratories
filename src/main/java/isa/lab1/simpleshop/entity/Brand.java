package isa.lab1.simpleshop.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Brand {
    private String name;
    private String country;
    private int yearOfFoundation;
}
