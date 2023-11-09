package ru.rfma.entities;

import lombok.*;
import org.springframework.lang.Nullable;
import ru.rfma.enums.OperationType;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Column(name = "amount")
    private float amount;

    @NonNull
    @Column(name = "date")
    private Date date;
    /**
     * Доп. информация
     */
    @Nullable
    @Column(name = "description")
    private String description;

    @NonNull
    @Column(name = "operationType")
    private OperationType operationType;
}
