package comp5200m.sc22ao.project.tracingdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpanTags {
    @JsonProperty("guid:x-request-id")
    private String guid;

    @JsonProperty("response_flags")
    private String responseFlags;

    @JsonProperty("upstream_cluster.name")
    private String upstreamClusterName;

    @JsonProperty("http.url")
    private String httpUrl;

    @JsonProperty("http.protocol")
    private String httpProtocol;

    @JsonProperty("istio.canonical_service")
    private String istioCanonicalService;

    @JsonProperty("peer.address")
    private String peerAddress;

    @JsonProperty("istio.namespace")
    private String istioNamespace;

    @JsonProperty("node_id")
    private String nodeId;

    @JsonProperty("response_size")
    private Integer responseSize;

    @JsonProperty("http.status_code")
    private Integer httpStatusCode;

    @JsonProperty("request_size")
    private Integer requestSize;

    @JsonProperty("istio.mesh_id")
    private String istioMeshId;

    @JsonProperty("istio.canonical_revision")
    private String istioCanonicalRevision;

    @JsonProperty("user_agent")
    private String userAgent;

    @JsonProperty("downstream_cluster")
    private String downstreamCluster;

    @JsonProperty("component")
    private String component;

    @JsonProperty("upstream_cluster")
    private String upstreamCluster;

    @JsonProperty("http.method")
    private String httpMethod;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getResponseFlags() {
        return responseFlags;
    }

    public void setResponseFlags(String responseFlags) {
        this.responseFlags = responseFlags;
    }

    public String getUpstreamClusterName() {
        return upstreamClusterName;
    }

    public void setUpstreamClusterName(String upstreamClusterName) {
        this.upstreamClusterName = upstreamClusterName;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getHttpProtocol() {
        return httpProtocol;
    }

    public void setHttpProtocol(String httpProtocol) {
        this.httpProtocol = httpProtocol;
    }

    public String getIstioCanonicalService() {
        return istioCanonicalService;
    }

    public void setIstioCanonicalService(String istioCanonicalService) {
        this.istioCanonicalService = istioCanonicalService;
    }

    public String getPeerAddress() {
        return peerAddress;
    }

    public void setPeerAddress(String peerAddress) {
        this.peerAddress = peerAddress;
    }

    public String getIstioNamespace() {
        return istioNamespace;
    }

    public void setIstioNamespace(String istioNamespace) {
        this.istioNamespace = istioNamespace;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getResponseSize() {
        return responseSize;
    }

    public void setResponseSize(Integer responseSize) {
        this.responseSize = responseSize;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public Integer getRequestSize() {
        return requestSize;
    }

    public void setRequestSize(Integer requestSize) {
        this.requestSize = requestSize;
    }

    public String getIstioMeshId() {
        return istioMeshId;
    }

    public void setIstioMeshId(String istioMeshId) {
        this.istioMeshId = istioMeshId;
    }

    public String getIstioCanonicalRevision() {
        return istioCanonicalRevision;
    }

    public void setIstioCanonicalRevision(String istioCanonicalRevision) {
        this.istioCanonicalRevision = istioCanonicalRevision;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getDownstreamCluster() {
        return downstreamCluster;
    }

    public void setDownstreamCluster(String downstreamCluster) {
        this.downstreamCluster = downstreamCluster;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getUpstreamCluster() {
        return upstreamCluster;
    }

    public void setUpstreamCluster(String upstreamCluster) {
        this.upstreamCluster = upstreamCluster;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }
}
