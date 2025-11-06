package com.you.deid;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class Report {
    public String file;
    public Map<String,Integer> counts;
    @JsonProperty("chars_redacted") public int charsRedacted;

    public static Report from(String file, Redactor.Result r) {
        Report rep = new Report();
        rep.file = file == null ? "" : file;
        rep.counts = Map.of("NAME", r.names(),"PHONE", r.phones(),"DATE", r.dates(),"EMAIL", r.emails(),"ID", r.ids());
        rep.charsRedacted = r.charsRedacted();
        return rep;
    }
    public String toPrettyJson() throws JsonProcessingException {
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }
}
