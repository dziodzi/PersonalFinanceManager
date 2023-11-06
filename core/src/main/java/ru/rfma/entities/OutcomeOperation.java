package ru.rfma.entities;

public class OutcomeOperation extends Operation {

    /**
     * На что потратили
     */
    private String purpose;

    @Override
    public String toString() {
        return this.date + " была проведена исходящая операция на сумму " + this.amount;
    }
}
