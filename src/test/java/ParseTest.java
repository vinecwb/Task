import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ParseTest {

    @Test
    void testParseBooleanTrue() {
        assertEquals(true, Parse.parse("true"));
    }

    @Test
    void testParseBooleanFalse() {
        assertEquals(false, Parse.parse("false"));
    }

    @Test
    void testParseInteger() {
        assertEquals(123, Parse.parse("123"));
    }

    @Test
    void testParseDouble() {
        assertEquals(3.14, Parse.parse("3.14"));
    }

    @Test
    void testParseLocalDate() {
        assertEquals(LocalDate.of(2024, 5, 1), Parse.parse("2024-05-01"));
    }

    @Test
    void testParseFallbackToString() {
        assertEquals("Estudar Java", Parse.parse("Estudar Java"));
    }

    @Test
    void testParseNull() {
        assertNull(Parse.parse(null));
    }

    @Test
    void testParseIntWithSpaces() {
        assertEquals(123, Parse.parse("   123   "));
    }

    @Test
    void testParseBooleanWithSpaces() {
        assertEquals(true, Parse.parse("   true   "));
    }


}
