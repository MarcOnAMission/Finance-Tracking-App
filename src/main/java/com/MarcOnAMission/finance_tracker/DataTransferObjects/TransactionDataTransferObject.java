package com.MarcOnAMission.finance_tracker.DataTransferObjects;

import com.MarcOnAMission.finance_tracker.EnumClasses.TransactionCategory;
import com.MarcOnAMission.finance_tracker.EnumClasses.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
public class FinanceTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long financeTransactionId;
    private TransactionCategory transactionCategory;-
    private long amountTransacted;-
    private TransactionType transactionType;-
    private String descriptionOfTransaction;-
    private LocalDateTime whenTransactionCreated;
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Data transfer object that helps create a transaction")
public class TransactionDataTransferObject {
    @Schema(description = "Category for the transaction.",examples = {"FOOD","BILLS","ENTERTAINMENT","JOB","CLOTHES"})
    private TransactionCategory dtoTransactionCategory;
    @Schema(description = "Type of transaction.",examples = {"EXPENSE","INCOME"})
    private TransactionType dtoTransactionType;
    @Schema(description = "Amount of currency transacted.",examples = {"2.5","13"})
    private long dtoAmountTransacted;
    @Schema(description = "Description for the transaction.",examples = {"Food bills.","Tyler Durden Outfit","Books"})
    private String dtoDescriptionOfTransaction;
}
