import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parse {
    public static Object parse(String input) {
        if (input == null) return null;

        input = input.trim();

        if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
            return Boolean.parseBoolean(input);
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ignored) {
        }

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException ignored) {
        }

        try {
            return LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException ignored) {
        }

        return input;
    }
}
