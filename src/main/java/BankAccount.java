import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class BankAccount {
    private float balance;

    private ArrayList<Transaction> transactions = new ArrayList();

    public void deposit(float amount, LocalDate date) throws BankAccountException{
        if (amount <= 0){
            throw new BankAccountException("Invalid deposit");
        }
        balance += amount;
        Transaction transaction = new Transaction(amount, 0, balance, date);
        this.transactions.add(transaction);
    }

    public void withdraw(float amount, LocalDate date) throws BankAccountException {
        if (amount > balance){
            throw new BankAccountException("Insufficient balance");
        } else if (amount <= 0){
            throw new BankAccountException("Invalid withdrawal");
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

    public String generateStatement() {
        ArrayList<Transaction> transactionsToPrint = transactions;
        Collections.reverse(transactionsToPrint);
        String header = "date || credit || debit || balance\n";
        String statement = header;
        for (int i = 0; i < transactionsToPrint.size(); i++) {
            statement += transactionsToPrint.get(i).getDate().toString() + " || " + String.format("%.2f", transactions.get(i).getCredit()) + " || " + String.format("%.2f", transactions.get(i).getDebit()) + " || " + String.format("%.2f", transactions.get(i).getBalance()) + "\n";
        }
        return statement.replaceAll(" 0\\.00 ", " - ");
    }
}
