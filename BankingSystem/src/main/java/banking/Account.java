package banking;

/**
 * Abstract bank account.
 */
public abstract class Account implements AccountInterface {
    private AccountHolder accountHolder;
    private Long accountNumber;
    private int pin;
    private double balance;

    protected Account(AccountHolder accountHolder, Long accountNumber, int pin, double startingDeposit) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = startingDeposit;
    }

    public AccountHolder getAccountHolder() {
        // TODO: complete the method
        return accountHolder;
    }

    public boolean validatePin(int attemptedPin) {
        // TODO: complete the method
        if (attemptedPin == pin) {
            return true;
        }
        return false;
    }

    public double getBalance() {
        // TODO: complete the method
        return balance;
    }

    public Long getAccountNumber() {
        // TODO: complete the method
        return accountNumber;
    }

    public void creditAccount(double amount) {
        // TODO: complete the method
        balance += amount;
    }

    public boolean debitAccount(double amount) {
        // TODO: complete the method
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }





}
