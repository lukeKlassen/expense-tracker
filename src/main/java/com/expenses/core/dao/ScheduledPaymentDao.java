package com.expenses.core.dao;

import com.expenses.core.model.ScheduledPayment;
import org.springframework.data.repository.CrudRepository;

public interface ScheduledPaymentDao extends CrudRepository<ScheduledPayment, String> {
}
