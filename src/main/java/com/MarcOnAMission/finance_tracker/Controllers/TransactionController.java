package com.MarcOnAMission.finance_tracker.Controllers;

import com.MarcOnAMission.finance_tracker.DataTransferObjects.TransactionDataTransferObject;
import com.MarcOnAMission.finance_tracker.Services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Finance Transaction Controller",description = "CRUD Operations for the Finance Transaction Entities")
@RestController
@RequestMapping("api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Operation(summary = "Create Financial Transaction",description = "Registers a Financial Transaction in the system.")
    @PostMapping
    public ResponseEntity<TransactionDataTransferObject> createFinanceTransaction(@RequestBody TransactionDataTransferObject inputTransactionDTO){
        return ResponseEntity.ok(transactionService.saveFinanceTransaction(inputTransactionDTO));
    }

    @Operation(summary = "Update Financial Transaction by id",description = "Takes in id and updates the data of a Financial Transaction at a given id if found.")
    @PatchMapping("/{id}")
    public ResponseEntity<TransactionDataTransferObject> updateFinanceTransaction(@PathVariable Long id, @RequestBody TransactionDataTransferObject inputTransactionDTO){
        return ResponseEntity.ok(transactionService.updateFinanceTransactionById(id,inputTransactionDTO));
    }

    @Operation(summary = "Finds Finance Transaction by id",description="Takes in id and finds the details for a Finance Transaction for a given id if found.")
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDataTransferObject> fetchTransactionById(@PathVariable Long id){
            return ResponseEntity.ok(transactionService.findFinanceTransactionByIdOrThrowException(id));
    }

    @Operation(summary = "Deletes Financial Transaction by id",description = "Takes in id and deletes the Financial Transaction by id if found.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable Long id){
        transactionService.deleteFinanceTransactionById(id);
        return ResponseEntity.noContent().build();
    }
}
