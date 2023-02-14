package banking;

/**
 * Account implementation for consumer (domestic) customers.
 * The account's holder is a {@link Person}.
 */
public class ConsumerAccount extends Account {

    private Person person;
    private Long accountNumber;
    private int pin;
    private double currentBalance;

    public ConsumerAccount(Person person, Long accountNumber, int pin, double currentBalance) {
        super(person, accountNumber, pin, currentBalance);
        this.person = person;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.currentBalance = currentBalance;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }


    public int getPin(){
        return pin;
    }
}

