package com.you.deid;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Pattern;

public record Patterns(
        Pattern email, Pattern phone, Pattern date, Pattern idLike, Set<String> firstNames
) {
    public static Patterns loadDefault(Path namesPath) throws IOException {
        Pattern phone = Pattern.compile("\\b(?:\\+?\\d{1,3}[\\s.-]?)?(?:\\(\\d{3}\\)|\\d{3})[\\s.-]?\\d{3}[\\s.-]?\\d{4}\\b");
        Pattern date  = Pattern.compile("\\b(?:\\d{1,2}[/-]\\d{1,2}[/-]\\d{2,4}|[A-Z][a-z]{2,9}\\s+\\d{1,2},\\s*\\d{4}|\\d{4}-\\d{2}-\\d{2})\\b");
        Pattern email = Pattern.compile("\\b[a-zA-Z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b");
        Pattern id    = Pattern.compile("\\b(?:MRN|ID|PatientID)[:\\s]*[A-Za-z0-9\\-]{6,10}\\b");

        Set<String> names = new HashSet<>();
        if (namesPath != null && Files.exists(namesPath)) {
            for (String line : Files.readAllLines(namesPath)) {
                line = line.strip();
                if (!line.isEmpty() && !line.startsWith("#")) names.add(line.toUpperCase(Locale.ROOT));
            }
        }
        return new Patterns(email, phone, date, id, names);
    }
}
