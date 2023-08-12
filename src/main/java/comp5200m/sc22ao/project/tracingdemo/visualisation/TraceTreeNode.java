package comp5200m.sc22ao.project.tracingdemo.visualisation;

import comp5200m.sc22ao.project.tracingdemo.model.TraceSpan;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TraceTreeNode {
    private String spanId;
    private String spanName;
    private String service;
    private Long duration;
    private List<TraceTreeNode> children = new LinkedList<>();

    public TraceTreeNode(String spanId) {
        this.spanId = spanId;
    }

    public TraceTreeNode(String spanId, String spanName) {
        this.spanId = spanId;
        this.spanName = spanName;
    }

    public TraceTreeNode(String spanId, String spanName, String service, Long duration) {
        this.spanId = spanId;
        this.spanName = spanName;
        this.service = service;
        this.duration = duration;
    }

    public TraceTreeNode(String spanId, List<TraceTreeNode> children) {
        this.spanId = spanId;
        this.children = children;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public List<TraceTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TraceTreeNode> children) {
        this.children = children;
    }

    public String getSpanName() {
        return this.spanName;
    }

    public void setSpanName(String spanName) {
        this.spanName = spanName;
    }

    public Long getDuration() {
        return this.duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void populateTree(TraceTreeNode node,
                             Map<String, Set<TraceSpan>> parentToChildrenSpans) {
        if (parentToChildrenSpans.containsKey(node.getSpanId())) {
            Set<TraceSpan> childSpans = parentToChildrenSpans.get(node.getSpanId());
            for (TraceSpan span : childSpans) {
                TraceTreeNode childNode = new TraceTreeNode(span.getId(), span.getName(),
                        span.getTags().getIstioCanonicalService(), span.getDuration());
                node.children.add(childNode);
                populateTree(childNode, parentToChildrenSpans);
            }
        }
    }
}
