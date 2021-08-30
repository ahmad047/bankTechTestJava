import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankAccountTest {
    BankAccount subject;
    @BeforeEach
    void createBankAccount() throws BankAccountException {
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
    void withdrawFunds() throws BankAccountException {
        subject.withdraw(200, LocalDate.of(2021, 8, 1));
        assertEquals(300.00, subject.getBalance());
    }

    @Test
    @DisplayName("Test can create a transaction object on deposit")
    void savesObjectOnDeposit(){
        assertEquals(500, subject.getTransactions().get(0).getCredit());
    }

    @Test
    @DisplayName("Test can create a transaction object on withdrawal")
    void savesObjectOnWithdrawal() throws BankAccountException{
        subject.withdraw(100, LocalDate.of(2021, 8, 1));
        assertEquals(100, subject.getTransactions().get(1).getDebit());

    }

    @Test
    @DisplayName("Test should throw an error for insufficient balance")
    void raiseExceptionForInsufficientBalance() {
        Exception exception = assertThrows(BankAccountException.class, () ->
                subject.withdraw(600, LocalDate.of(21, 8, 1)));
        assertEquals("Insufficient balance", exception.getMessage());
    }

    @Test
    void raiseExceptionForNegativeDeposit() throws BankAccountException{
        Exception exception = assertThrows(BankAccountException.class, () -> {
            subject.deposit(-50, LocalDate.of(2021, 8, 11));
        });

        assertEquals(exception.getMessage(), "Invalid deposit");
    }

    @Test
    void raiseExceptionForNegativeWithdrawal() throws BankAccountException{
        Exception exception = assertThrows(BankAccountException.class, () -> {
            subject.withdraw(-50, LocalDate.of(2021, 8, 11));
        });

        assertEquals(exception.getMessage(), "Invalid withdrawal");
    }

}
