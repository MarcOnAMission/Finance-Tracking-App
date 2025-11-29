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
    private long transactionId;
    @Enumerated(EnumType.STRING)
    private TransactionCategory transactionCategory;
    private long amountTransacted;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private String transactionDescription;
    private LocalDateTime transactionCreatedAt;

    public FinanceTransaction(TransactionCategory category,TransactionType type, long amount,String description){
        this.transactionCategory=category;
        this.transactionType=type;
        this.amountTransacted=amount;
        this.transactionDescription =description;
        this.transactionCreatedAt=LocalDateTime.now();
    }

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private ApplicationUser userThatOwnsTransaction;
}
