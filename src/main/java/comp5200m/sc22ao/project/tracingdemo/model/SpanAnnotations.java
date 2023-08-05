package comp5200m.sc22ao.project.tracingdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpanAnnotations {
    @JsonProperty("value")
    private String value;

    @JsonProperty("timestamp")
    private Long timestamp;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
