import java.time.LocalDate;

public class Transaction {
    private float credit;
    private float debit;
    private float balance;
    private LocalDate date;

    public Transaction(float credit, float debit, float balance, LocalDate date) {
        this.debit = debit;
        this.credit = credit;
        this.date = date;
        this.balance = balance;
    }

    public float getCredit() {
        return credit;
    }

    public float getDebit() {
        return debit;
    }

    public float getBalance() {
        return balance;
    }

    public LocalDate getDate() {
        return date;
    }
}
