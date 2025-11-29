package com.MarcOnAMission.finance_tracker;


import com.MarcOnAMission.finance_tracker.DataTransferObjects.TransactionDataTransferObject;
import com.MarcOnAMission.finance_tracker.EnumClasses.TransactionCategory;
import com.MarcOnAMission.finance_tracker.EnumClasses.TransactionType;
import com.MarcOnAMission.finance_tracker.Model.FinanceTransaction;
import com.MarcOnAMission.finance_tracker.Repositories.TransactionRepository;
import com.MarcOnAMission.finance_tracker.ServiceImplementations.TransactionServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplementationTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImp transactionService;

    private FinanceTransaction transactionTestObject;

    @BeforeEach
    void setUp() {
        transactionTestObject = new FinanceTransaction(TransactionCategory.BILLS, TransactionType.EXPENSE, 10, "Random bill");
    }

    @Test
    void shouldSaveFinanceTransactionGivenInputDTO() {
        FinanceTransaction savedEntity = new FinanceTransaction(TransactionCategory.BILLS, TransactionType.EXPENSE, 10, "Random Bill");
        TransactionDataTransferObject inputDataTransferObject = new TransactionDataTransferObject(TransactionCategory.BILLS, TransactionType.EXPENSE, 10, "Random Bill");

        when(transactionRepository.save(any(FinanceTransaction.class))).thenReturn(savedEntity);
        TransactionDataTransferObject resultDataTransferObject = transactionService.saveFinanceTransaction(inputDataTransferObject);

        //New idea of this ArgumentCaptor
        ArgumentCaptor<FinanceTransaction> capturePassedEntity = ArgumentCaptor.forClass(FinanceTransaction.class);

        verify(transactionRepository).save(capturePassedEntity.capture());

        FinanceTransaction passedEntityToRepository = capturePassedEntity.getValue();

        //Assertions input->passed entity
        assertEquals(inputDataTransferObject.getDtoAmountTransacted(), passedEntityToRepository.getAmountTransacted());
        assertEquals(inputDataTransferObject.getDtoTransactionCategory(), passedEntityToRepository.getTransactionCategory());
        assertEquals(inputDataTransferObject.getDtoTransactionType(), passedEntityToRepository.getTransactionType());
        assertEquals(inputDataTransferObject.getDtoDescriptionOfTransaction(), passedEntityToRepository.getTransactionDescription());
        //Assertions saved->result
        assertEquals(savedEntity.getTransactionCategory(), resultDataTransferObject.getDtoTransactionCategory());
        assertEquals(savedEntity.getTransactionType(), resultDataTransferObject.getDtoTransactionType());
        assertEquals(savedEntity.getAmountTransacted(), resultDataTransferObject.getDtoAmountTransacted());
        assertEquals(savedEntity.getTransactionDescription(), resultDataTransferObject.getDtoDescriptionOfTransaction());
    }

    @Test
    void shouldReturnFinanceTransactionWhenGivenId() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionTestObject));

        TransactionDataTransferObject resultDataTransferObject = transactionService.findFinanceTransactionByIdOrThrowException(1L);

        verify(transactionRepository).findById(1L);

        assertEquals(transactionTestObject.getTransactionCategory(), resultDataTransferObject.getDtoTransactionCategory());
        assertEquals(transactionTestObject.getTransactionType(), resultDataTransferObject.getDtoTransactionType());
        assertEquals(transactionTestObject.getAmountTransacted(), resultDataTransferObject.getDtoAmountTransacted());
        assertEquals(transactionTestObject.getTransactionDescription(), resultDataTransferObject.getDtoDescriptionOfTransaction());
    }

    @Test
    void shouldThrowExceptionWhenTransactionNotFoundById() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> transactionService.findFinanceTransactionByIdOrThrowException(1L));
        verify(transactionRepository).findById(any());
    }

    @Test
    void shouldReturnUpdatedTransactionBasedOnInput() {
        FinanceTransaction updatedTransaction = new FinanceTransaction(TransactionCategory.CLOTHES, TransactionType.EXPENSE, 100, "Lovely sweater");
        TransactionDataTransferObject inputDataTransferObject = new TransactionDataTransferObject(TransactionCategory.JOB, TransactionType.INCOME, 500, "Lovely internship");

        when(transactionRepository.save(any(FinanceTransaction.class))).thenReturn(updatedTransaction);
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionTestObject));

        TransactionDataTransferObject resultDataTransferObject = transactionService.updateFinanceTransactionById(1L, inputDataTransferObject);
        verify(transactionRepository).findById(1L);
        verify(transactionRepository).save(any(FinanceTransaction.class));

        //Assert result->saved
        assertEquals(updatedTransaction.getTransactionCategory(), resultDataTransferObject.getDtoTransactionCategory());
        assertEquals(updatedTransaction.getTransactionType(), resultDataTransferObject.getDtoTransactionType());
        assertEquals(updatedTransaction.getAmountTransacted(), resultDataTransferObject.getDtoAmountTransacted());
        assertEquals(updatedTransaction.getTransactionDescription(), resultDataTransferObject.getDtoDescriptionOfTransaction());
    }

    @Test
    void shouldCallDeleteFunctionWhenTransactionExists() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionTestObject));
        transactionService.deleteFinanceTransactionById(1L);
        verify(transactionRepository).findById(1L);
        verify(transactionRepository).delete(any(FinanceTransaction.class));
    }
}
