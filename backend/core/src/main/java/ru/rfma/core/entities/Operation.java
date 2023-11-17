package ru.rfma.core.entities;

import lombok.*;
import org.springframework.lang.Nullable;
import ru.rfma.core.enums.OperationType;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
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

    @Nullable
    @Column(name="categoryId")
    private int categoryId;
}
