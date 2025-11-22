package com.MarcOnAMission.finance_tracker.Model;

import com.MarcOnAMission.finance_tracker.EnumClasses.TransactionCategory;
import com.MarcOnAMission.finance_tracker.EnumClasses.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Transactions")
public class FinanceTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long financeTransactionId;
    private TransactionCategory transactionCategory;
    private long amountTransacted;
    private TransactionType transactionType;
    private String descriptionOfTransaction;
    private LocalDateTime whenTransactionCreated;
}
