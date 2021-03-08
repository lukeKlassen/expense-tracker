package com.expenses.core.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "scheduled_payment")
@Data
public class ScheduledPayment {

    @Id
    private String uploadId;

    private String spendingCatagory;
    private double amount;
    private String comment;
    private String date;
    private Frequency frequency;
}