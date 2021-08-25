import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountTest {
    @Test
    void depositsFunds(){
        BankAccount subject = new BankAccount();

        subject.deposit(500);
        assertEquals(500.00, subject.getBalance());
    }

    @Test
    void withdrawFunds(){
        BankAccount subject = new BankAccount();

        subject.deposit(500);
        subject.withdraw(200);
        assertEquals(300.00, subject.getBalance());
    }
}
