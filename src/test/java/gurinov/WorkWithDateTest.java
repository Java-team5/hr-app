package gurinov;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class WorkWithDateTest {

    @Test
    public void isValidDate() {
        assertTrue(WorkWithDate.isValidDate("12/12/12"));
        assertTrue(WorkWithDate.isValidDate("02/02/12"));
        assertTrue(WorkWithDate.isValidDate("12-12-2010"));
        assertTrue(WorkWithDate.isValidDate("2-12-10"));
        assertTrue(WorkWithDate.isValidDate("29-2-2000"));
        assertFalse(WorkWithDate.isValidDate("29-2-99"));
        assertFalse(WorkWithDate.isValidDate("0-0-0"));
        assertFalse(WorkWithDate.isValidDate("10-13-2000"));
        assertFalse(WorkWithDate.isValidDate("32-10-2000"));
        assertFalse(WorkWithDate.isValidDate("10-10-9999"));
    }

    @Test
    public void calculateAge() {
        int different = 8;
        String date = LocalDate.now().getDayOfMonth() + "/" + LocalDate.now().getMonthValue() + "/" + (LocalDate.now().getYear() - different);
        assertEquals(different + "", WorkWithDate.calculateAge(date));
        assertEquals("Error in date", WorkWithDate.calculateAge("32/32/32"));
    }

    @Test
    public void calculateDaysBeforeBirthday() {
        String date = LocalDate.now().getDayOfMonth() + "/" + LocalDate.now().getMonthValue() + "/" + LocalDate.now().getYear();
        assertEquals("Error in date", WorkWithDate.calculateDaysBeforeBirthday(date));
        assertEquals("Error in date", WorkWithDate.calculateDaysBeforeBirthday("32/32/32"));
    }
}