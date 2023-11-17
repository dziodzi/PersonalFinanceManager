package ru.rfma.core.entities;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "spendLimit")
    private Float spendLimit;

    public Category(final String name, final Float spendLimit) {
        this.name = name;
        this.spendLimit = spendLimit;
    }
}
