import java.time.LocalDate;
import java.util.ArrayList;

public class BankAccount {
    private float balance;

    private ArrayList<Transaction> transactions = new ArrayList();

    public void deposit(float amount, LocalDate date) {
        balance += amount;
        Transaction transaction = new Transaction(amount, 0, balance, date);
        this.transactions.add(transaction);
    }

    public void withdraw(float amount, LocalDate date) throws BankAccountException {
        if (amount > balance){
            throw new BankAccountException("Insufficient balance");
        }
        balance -= amount;
        Transaction transaction = new Transaction(0, amount, balance, date);
        this.transactions.add(transaction);
    }

    public float getBalance () {
        return balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
