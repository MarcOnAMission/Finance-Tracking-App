package com.MarcOnAMission.finance_tracker.Services;

import com.MarcOnAMission.finance_tracker.DataTransferObjects.TransactionDataTransferObject;

public interface TransactionService {
        TransactionDataTransferObject saveFinanceTransaction(TransactionDataTransferObject transactionDTO);
        TransactionDataTransferObject findFinanceTransactionByIdOrThrowException(Long id);
        TransactionDataTransferObject updateFinanceTransactionById(Long id, TransactionDataTransferObject transactionDTO);
        void deleteFinanceTransactionById(Long id);
}
