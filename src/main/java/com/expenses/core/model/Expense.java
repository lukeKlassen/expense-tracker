package com.expenses.core.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "expense")
@Data
public class Expense {

    @Id
    private String uploadId;

    private String spendingCategory;
    private double amount;
    private String comment;
    private String date;
}
