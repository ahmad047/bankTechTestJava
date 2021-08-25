import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class TransactionTest {
    @Test
    void depositsFunds(){
        Transaction subject = new Transaction(500.00f, 0.00f, 500.00f, LocalDate.of(21, 1, 10));

        assertEquals(500.00, subject.getCredit());
        assertEquals(0.00, subject.getDebit());
        assertEquals(500.00, subject.getBalance());
        assertEquals(LocalDate.of(21, 1, 10), subject.getDate());
    }


}
