package com.pathan;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ManageDateTest {
    private final ManageDate manageDate = new ManageDate();

    @Test
    @DisplayName("Identify Date")
    void isDate() {
        String[] dates = {"1756-01-27", "1955/04/18", "04-07-1934", "20/2/2016"};
        for (String date:dates){
            assertEquals(true, manageDate.isDate(date), "Invalid date: "+date);
        }
    }

    @Test
    @DisplayName("String to Date parse")
    void stringToDate() throws ParseException {
        String[] dates = {"1756-01-27", "1955/04/18", "04-07-1934", "20/2/2016"};

        assertEquals("1756-01-27", manageDate.stringToDate("1756-01-27"));
        assertEquals("1955-04-18", manageDate.stringToDate("1955/04/18"));
        assertEquals("1934-07-04", manageDate.stringToDate("04-07-1934"));
        assertEquals("2016-02-20", manageDate.stringToDate("20/02/2016"));
    }
}