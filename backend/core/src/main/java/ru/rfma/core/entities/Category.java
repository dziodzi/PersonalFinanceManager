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
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "spend_limit")
    private Float spendLimit;

    @NonNull
    @Column(name = "user_id")
    private int userId;

    public Category(final String name, final Float spendLimit, final int userId) {
        this.name = name;
        this.spendLimit = spendLimit;
        this.userId = userId;
    }

}
