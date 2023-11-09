package ru.rfma.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rfma.enums.OperationType;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationDto {
    private float amount;

    private Date date;

    private String description;

    private OperationType operationType;
}
