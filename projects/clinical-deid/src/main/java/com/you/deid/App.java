package com.you.deid;
import java.nio.file.*; import java.util.*; import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
    private static void usageAndExit(int code) {
        System.out.println("""
        Usage:
          java -jar clinical-deid-all.jar --in data/sample_1.txt --out out/redacted.txt --report out/report.json
          Flags: --in <file>  --out <file>  --report <file>  --dry-run
        """); System.exit(code);
    }
    public static void main(String[] args) throws Exception {
        Map<String,String> kv = new HashMap<>(); boolean dry=false;
        for (int i=0;i<args.length;i++) {
            switch (args[i]) {
                case "--dry-run" -> dry = true;
                case "--in","--out","--report" -> { if (i+1>=args.length) usageAndExit(1); kv.put(args[i], args[++i]); }
                case "--help","-h" -> usageAndExit(0);
                default -> usageAndExit(1);
            }
        }
        if (!kv.containsKey("--in")) usageAndExit(1);
        String input = Files.readString(Path.of(kv.get("--in")));
        Patterns patterns = Patterns.loadDefault(Paths.get("dictionaries/names_shortlist.txt"));
        Redactor.Result result = new Redactor(patterns).redact(input);

        if (!dry) {
            String outPath = kv.getOrDefault("--out", kv.get("--in").replace(".txt","_redacted.txt"));
            Files.createDirectories(Path.of(outPath).getParent()); Files.writeString(Path.of(outPath), result.redactedText());
        }
        Report report = Report.from(kv.get("--in"), result);
        if (kv.containsKey("--report")) {
            String rp = kv.get("--report"); Files.createDirectories(Path.of(rp).getParent());
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(Path.of(rp).toFile(), report);
        } else { System.out.println(report.toPrettyJson()); }
    }
}
