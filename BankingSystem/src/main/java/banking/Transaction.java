package banking;

/**
 * A bank transaction implementation.
 */
public class Transaction implements TransactionInterface {
    private Long accountNumber;
    private BankInterface bank;

    private int attemptedPin;

    /**
     * @param bank          The bank where the account is housed.
     * @param accountNumber The customer's account number.
     * @param attemptedPin  The PIN entered by the customer.
     * @throws Exception Account validation failed.
     */
    public Transaction(BankInterface bank, Long accountNumber, int attemptedPin) throws Exception {
        // TODO: complete the constructor
        if (bank == null) {
            throw new Exception("Account validation failed");
        }
        if (accountNumber == null) {
            throw new Exception("Account validation failed");
        }
        int attemptedPinLen = String.valueOf(attemptedPin).length();
        if (attemptedPinLen > 4){
            throw new Exception("Pin validation failed");
        }
        Account account = bank.getAccount(accountNumber);
        if (!account.validatePin(attemptedPin)){
            throw new Exception("Pin validation failed");
        }
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.attemptedPin = attemptedPin;


    }

    public double getBalance() {
        return bank.getBalance(accountNumber);
    }

    public void credit(double amount) {
        Account account = bank.getAccount(accountNumber);
        account.creditAccount(amount);
    }

    public boolean debit(double amount) {
        Account account = bank.getAccount(accountNumber);
        return account.debitAccount(amount);
    }

}
