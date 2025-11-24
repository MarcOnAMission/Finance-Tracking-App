    package com.MarcOnAMission.finance_tracker.Mappers;

    import com.MarcOnAMission.finance_tracker.DataTransferObjects.TransactionDataTransferObject;
    import com.MarcOnAMission.finance_tracker.EnumClasses.TransactionCategory;
    import com.MarcOnAMission.finance_tracker.EnumClasses.TransactionType;
    import com.MarcOnAMission.finance_tracker.Model.FinanceTransaction;

    /*
    @Schema(description = "Category for the transaction.",examples = {"FOOD","BILLS","ENTERTAINMENT","JOB","CLOTHES"})
        private TransactionCategory dtoTransactionCategory;
        @Schema(description = "Type of transaction.",examples = {"EXPENSE","INCOME"})
        private TransactionType dtoTransactionType;
        @Schema(description = "Amount of currency transacted.",examples = {"2.5","13"})
        private long dtoAmountTransacted;
        @Schema(description = "Description for the transaction.",examples = {"Food bills.","Tyler Durden Outfit","Books"})
        private String dtoDescriptionOfTransaction;
     */
    public class TransactionDTOMapper {
        public static FinanceTransaction createTransactionEntity(String category, String type, long amount, String description) {
            TransactionType matchedType = TransactionType.valueOf(type.toUpperCase());
            TransactionCategory matchedCategory = TransactionCategory.valueOf(category.toUpperCase());
            return new FinanceTransaction(matchedCategory, amount, matchedType, description);
        }

        public static TransactionDataTransferObject createTransactionDataTransferObject(FinanceTransaction passedTransactionToCreateFrom) {
            TransactionType type = passedTransactionToCreateFrom.getTransactionType();
            TransactionCategory category = passedTransactionToCreateFrom.getTransactionCategory();
            long amount = passedTransactionToCreateFrom.getAmountTransacted();
            String description = passedTransactionToCreateFrom.getDescriptionOfTransaction();
            return new TransactionDataTransferObject(category,type,amount,description);
        }
    }
