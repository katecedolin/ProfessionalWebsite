package com.you.deid;
import java.util.Locale;
import java.util.regex.*;

public class Redactor {
    public static record Result(String redactedText, int names, int phones, int dates, int emails, int ids, int charsRedacted) {}

    private final Patterns p;
    public Redactor(Patterns p) { this.p = p; }

    public Result redact(String input) {
        int chars = 0;
        String t = input;

        var r1 = replaceAndCount(t, p.email(), "[EMAIL]"); t = r1.text; int emails = r1.count; chars += r1.chars;
        var r2 = replaceAndCount(t, p.phone(), "[PHONE]"); t = r2.text; int phones = r2.count; chars += r2.chars;
        var r3 = replaceAndCount(t, p.date(),  "[DATE]");  t = r3.text; int dates  = r3.count; chars += r3.chars;
        var r4 = replaceAndCount(t, p.idLike(),"[ID]");    t = r4.text; int ids    = r4.count; chars += r4.chars;

        int nameCount = 0; StringBuilder out = new StringBuilder(); int i = 0;
        Pattern namePat = Pattern.compile("\\b([A-Z][a-zA-Z\\-']+)\\s+([A-Z][a-zA-Z\\-']+)\\b");
        Matcher m = namePat.matcher(t);
        while (m.find()) {
            String first = m.group(1);
            if (p.firstNames().contains(first.toUpperCase(Locale.ROOT))) {
                out.append(t, i, m.start()).append("[NAME]");
                nameCount++; i = m.end(); chars += m.group().length();
            }
        }
        out.append(t.substring(i));
        t = out.toString();

        return new Result(t, nameCount, phones, dates, emails, ids, chars);
    }

    private static class ReplaceResult { String text; int count; int chars;
        ReplaceResult(String t, int c, int ch) { text=t; count=c; chars=ch; } }

    private static ReplaceResult replaceAndCount(String input, Pattern pattern, String token) {
        Matcher m = pattern.matcher(input); StringBuffer sb = new StringBuffer();
        int count = 0, chars = 0;
        while (m.find()) { count++; chars += m.group().length(); m.appendReplacement(sb, Matcher.quoteReplacement(token)); }
        m.appendTail(sb); return new ReplaceResult(sb.toString(), count, chars);
    }
}
