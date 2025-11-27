package com.MarcOnAMission.finance_tracker.Repositories;

import com.MarcOnAMission.finance_tracker.Model.FinanceTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<FinanceTransaction,Long> {
}
