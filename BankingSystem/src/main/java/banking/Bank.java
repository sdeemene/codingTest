package banking;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The Bank implementation.
 */
public class Bank implements BankInterface {
    private LinkedHashMap<Long, Account> accounts;

    public Bank(LinkedHashMap<Long, Account> accounts) {
        // TODO: complete the constructor
        this.accounts = accounts;
    }

    public Account getAccount(Long accountNumber) {
        return accounts.get(accountNumber);
    }

    public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
        // TODO: complete the method
        Account account = new CommercialAccount(company, (long) company.getIdNumber(), pin, startingDeposit);
        CommercialAccount commercialAccount = new CommercialAccount(company, account.getAccountNumber(), pin, startingDeposit);
        Long id = Long.parseLong(String.valueOf(company.getIdNumber()));
        accounts.put(id, account);
        return commercialAccount.getAccountNumber();
    }

    public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
        // TODO: complete the method
        Account account = new ConsumerAccount(person, (long) person.getIdNumber(), pin, startingDeposit);
        ConsumerAccount consumerAccount = new ConsumerAccount(person, account.getAccountNumber(), pin, startingDeposit);
        Long id = Long.parseLong(String.valueOf(person.getIdNumber()));
        accounts.put(id, account);
        return consumerAccount.getAccountNumber();
    }

    public double getBalance(Long accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            return account.getBalance();
        } else {
            return -1;
        }

    }

    public void credit(Long accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        account.creditAccount(amount);
    }


    public void validatePin(Long accountNumber, int customerPin) {
        Account account = accounts.get(accountNumber);
        account.validatePin(customerPin);
    }

    public boolean debit(Long accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        return account.debitAccount(amount);
    }

    public boolean authenticateUser(Long accountNumber, int pin) {
        // TODO: complete the method
        return false;
    }

    public void addAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        Account account = accounts.get(accountNumber);
        if(account != null){
            if (account.getAccountHolder() instanceof Company) {
                CommercialAccount commercialAccount = (CommercialAccount) account;
                commercialAccount.addAuthorizedUser(authorizedPerson);
                System.out.println("commercialAccount {}" + commercialAccount);
            }
        }

    }

    public boolean checkAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        Account account = accounts.get(accountNumber);
        if(account == null){
            return false;
        }
        if (account.getAccountHolder() instanceof Company) {
            CommercialAccount commercialAccount = (CommercialAccount) account;
            return commercialAccount.isAuthorizedUser(authorizedPerson);
        }
        return false;
    }

    public Map<String, Double> getAverageBalanceReport() {
        // TODO: complete the method

        List<Account> newAccounts = new ArrayList<>(accounts.values());
        Map<String, Double> averageBalancesByType = new HashMap<>();


        List<Account> commercialAccounts = newAccounts.stream()
                .filter(a -> a instanceof CommercialAccount)
                .collect(Collectors.toList());
        double commercialAverage = commercialAccounts.stream()
                .mapToDouble(Account::getBalance)
                .average()
                .orElse(Double.NaN);
        averageBalancesByType.put("CommercialAccount", commercialAverage);


        List<Account> consumerAccounts = newAccounts.stream()
                .filter(a -> a instanceof ConsumerAccount)
                .collect(Collectors.toList());
        double consumerAverage = consumerAccounts.stream()
                .mapToDouble(Account::getBalance)
                .average()
                .orElse(Double.NaN);
        averageBalancesByType.put("ConsumerAccount", consumerAverage);
        return averageBalancesByType;
    }
}
