package comp5200m.sc22ao.project.tracingdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpanLocalEndpoint {
    @JsonProperty("port")
    private Integer port;

    @JsonProperty("serviceName")
    private String serviceName;

    @JsonProperty("ipv4")
    private String ipv4;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }
}
