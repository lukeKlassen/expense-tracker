package com.expenses.core.service.impl;

import com.expenses.core.model.ScheduledPayment;
import com.expenses.core.service.SchedulingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulingServiceImpl implements SchedulingService {
    @Override
    public void addScheduledPayment(ScheduledPayment payment) {

    }

    @Override
    public List<ScheduledPayment> getScheduledPayments() {
        return null;
    }
}
