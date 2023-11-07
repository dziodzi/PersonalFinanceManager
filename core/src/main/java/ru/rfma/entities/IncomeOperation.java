package ru.rfma.entities;

public class IncomeOperation extends Operation {

    /**
     * Откуда получено
     */
    private String source;

    @Override
    public String toString() {
        return this.date + " была проведена входящая операция на сумму " + this.amount;
    }
}
