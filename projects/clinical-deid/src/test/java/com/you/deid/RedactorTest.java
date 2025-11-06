package com.you.deid;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

public class RedactorTest {
    private Redactor redactor() throws Exception {
        Patterns p = Patterns.loadDefault(Paths.get("dictionaries/names_shortlist.txt"));
        return new Redactor(p);
    }
    @Test void emailRedacts() throws Exception {
        var r = redactor().redact("Contact me at jane.doe@example.org.");
        assertTrue(r.redactedText().contains("[EMAIL]"));
    }
}
