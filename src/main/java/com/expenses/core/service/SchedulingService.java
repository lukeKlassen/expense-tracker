package com.expenses.core.service;

import com.expenses.core.model.ScheduledPayment;

import java.util.List;

public interface SchedulingService {

    public void addScheduledPayment(ScheduledPayment payment);

    public List<ScheduledPayment> getScheduledPayments();
}
