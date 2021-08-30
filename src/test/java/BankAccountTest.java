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

    @Test
    void generatesTheStatement() throws BankAccountException{
        BankAccount bankAccount = new BankAccount();
        bankAccount.deposit(1000, LocalDate.of(2021, 1, 10));
        bankAccount.deposit(2000, LocalDate.of(2021, 1, 13));
        bankAccount.withdraw(500, LocalDate.of(2021, 1, 14));
        String outputExample = "date || credit || debit || balance\n" +
                "2021-01-14 || - || 500.00 || 2500.00\n" +
                "2021-01-13 || 2000.00 || - || 3000.00\n" +
                "2021-01-10 || 1000.00 || - || 1000.00\n";
        assertEquals(outputExample, bankAccount.generateStatement());

    }

}
