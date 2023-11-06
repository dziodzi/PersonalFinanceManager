package ru.rfma.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class Operation {

    int id;
    float amount;
    Date date;
    /**
     * Доп. информация
     */
    String description;
}
