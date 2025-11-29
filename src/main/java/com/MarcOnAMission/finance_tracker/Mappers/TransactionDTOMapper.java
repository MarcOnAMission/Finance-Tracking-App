    package com.MarcOnAMission.finance_tracker.Mappers;

    import com.MarcOnAMission.finance_tracker.DataTransferObjects.TransactionDataTransferObject;
    import com.MarcOnAMission.finance_tracker.EnumClasses.TransactionCategory;
    import com.MarcOnAMission.finance_tracker.EnumClasses.TransactionType;
    import com.MarcOnAMission.finance_tracker.Model.FinanceTransaction;

    public class TransactionDTOMapper {

        public static FinanceTransaction createTransactionEntity(TransactionDataTransferObject inputDTO) {
            TransactionType matchedType = inputDTO.getDtoTransactionType();
            TransactionCategory matchedCategory = inputDTO.getDtoTransactionCategory();
            return new FinanceTransaction(matchedCategory, matchedType, inputDTO.getDtoAmountTransacted(), inputDTO.getDtoDescriptionOfTransaction());
        }

        public static TransactionDataTransferObject createTransactionDataTransferObject(FinanceTransaction passedTransactionToCreateFrom) {
            TransactionType type = passedTransactionToCreateFrom.getTransactionType();
            TransactionCategory category = passedTransactionToCreateFrom.getTransactionCategory();
            long amount = passedTransactionToCreateFrom.getAmountTransacted();
            String description = passedTransactionToCreateFrom.getTransactionDescription();
            return new TransactionDataTransferObject(category,type,amount,description);
        }
    }
