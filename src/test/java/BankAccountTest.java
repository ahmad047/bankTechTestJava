import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountTest {
    BankAccount subject;
    @BeforeEach
    void createBankAccount(){
        subject = new BankAccount();
        subject.deposit(500, LocalDate.of(2021, 8, 1));
    }
    @Test
    @DisplayName("Test can increase the balance on deposit")
    void depositsFunds(){
        assertEquals(500.00, subject.getBalance());
    }

    @Test
    @DisplayName("Test can reduce the balance on withdrawal")
    void withdrawFunds(){
        subject.withdraw(200, LocalDate.of(2021, 8, 1));
        assertEquals(300.00, subject.getBalance());
    }

    @Test
    @DisplayName("Test can create a transaction object on deposit")
    void savesObjectOnDesposit(){
        assertEquals(500, subject.getTransactions().get(0).getCredit());
    }

    @Test
    @DisplayName("Test can create a transaction object on withdrawal")
    void savesObjectOnWithdrawal(){
        subject.withdraw(100, LocalDate.of(2021, 8, 1));
        assertEquals(100, subject.getTransactions().get(1).getDebit());

    }
}
