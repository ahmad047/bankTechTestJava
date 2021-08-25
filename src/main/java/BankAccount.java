public class BankAccount {
    private float balance;

    public void deposit(float amount) {
        balance += amount;
    }

    public float getBalance () {
        return balance;
    }
}
