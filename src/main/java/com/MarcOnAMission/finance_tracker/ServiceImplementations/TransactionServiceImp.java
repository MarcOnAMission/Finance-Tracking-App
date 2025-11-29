package com.MarcOnAMission.finance_tracker.ServiceImplementations;

import com.MarcOnAMission.finance_tracker.DataTransferObjects.TransactionDataTransferObject;
import com.MarcOnAMission.finance_tracker.Mappers.TransactionDTOMapper;
import com.MarcOnAMission.finance_tracker.Model.FinanceTransaction;
import com.MarcOnAMission.finance_tracker.Repositories.TransactionRepository;
import com.MarcOnAMission.finance_tracker.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TransactionServiceImp implements TransactionService {
    @Autowired
    private TransactionRepository transactions;

    public TransactionDataTransferObject saveFinanceTransaction(TransactionDataTransferObject inputTransactionDTO) {
        FinanceTransaction savedFinanceTransaction = transactions.save(TransactionDTOMapper.createTransactionEntity(inputTransactionDTO));
        return TransactionDTOMapper.createTransactionDataTransferObject(savedFinanceTransaction);
    }

    public TransactionDataTransferObject findFinanceTransactionByIdOrThrowException(Long id) {
            FinanceTransaction foundTransaction = findAndReturnFinanceTransactionById(id);
            return TransactionDTOMapper.createTransactionDataTransferObject(foundTransaction);
    }

    public TransactionDataTransferObject updateFinanceTransactionById(Long id, TransactionDataTransferObject inputDTO){
        FinanceTransaction updatedTransaction = findAndReturnFinanceTransactionById(id);
        updatedTransaction.setAmountTransacted(inputDTO.getDtoAmountTransacted());
        updatedTransaction.setTransactionCategory(inputDTO.getDtoTransactionCategory());
        updatedTransaction.setTransactionType(inputDTO.getDtoTransactionType());
        updatedTransaction.setTransactionDescription(inputDTO.getDtoDescriptionOfTransaction());
        return TransactionDTOMapper.createTransactionDataTransferObject(transactions.save(updatedTransaction));
    }

    private FinanceTransaction findAndReturnFinanceTransactionById(Long id){
        Optional<FinanceTransaction> unknownIfPresentTransaction =  transactions.findById(id);
        if(unknownIfPresentTransaction.isPresent()){
            return unknownIfPresentTransaction.get();
        }
        else{
            throw new RuntimeException("Finance transaction at this id not found"+id);
        }
    }

    @Override
    public void deleteFinanceTransactionById(Long id) {
        Optional<FinanceTransaction> unknownIfPresentFinanceTransaction = transactions.findById(id);
        if(unknownIfPresentFinanceTransaction.isPresent()){
            transactions.delete(unknownIfPresentFinanceTransaction.get());
        }
        else{
            throw new RuntimeException("Could not delete finance transaction because not found of id"+id);
        }
    }
}
