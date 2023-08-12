package comp5200m.sc22ao.project.tracingdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document("trace")
public class TraceSpan {
    @JsonProperty("traceId")
    private String traceId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("timestamp")
    private Long timestamp;

    @JsonProperty("parentId")
    private String parentId;

    @JsonProperty("kind")
    private Kind kind;

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("localEndpoint")
    private SpanLocalEndpoint localEndpoint;

    @JsonProperty("tags")
    private SpanTags tags;

    @JsonProperty("annotations")
    private List<SpanAnnotations> annotations;

    @JsonProperty("duration")
    private Long duration;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SpanLocalEndpoint getLocalEndpoint() {
        return localEndpoint;
    }

    public void setLocalEndpoint(SpanLocalEndpoint localEndpoint) {
        this.localEndpoint = localEndpoint;
    }

    public SpanTags getTags() {
        return tags;
    }

    public void setTags(SpanTags tags) {
        this.tags = tags;
    }

    public List<SpanAnnotations> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<SpanAnnotations> annotations) {
        this.annotations = annotations;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        TraceSpan compare = (TraceSpan) o;
        return Objects.equals(compare.getId(), this.id)
                && Objects.equals(compare.getTraceId(), this.traceId)
                && Objects.equals(compare.getName(), this.name)
                && Objects.equals(compare.getParentId(), this.parentId)
                && Objects.equals(compare.getKind(), this.kind)
                && Objects.equals(compare.getTimestamp(), this.timestamp)
                && Objects.equals(compare.getDuration(), this.duration);
    }

    @Override
    public String toString() {
        return this.id +
                "\t" +
                this.traceId +
                "\t" +
                this.duration +
                "\t" +
                this.tags.getIstioCanonicalService() +
                "\t\t\t" +
                this.parentId +
                "\t\t" +
                this.name;
    }
}