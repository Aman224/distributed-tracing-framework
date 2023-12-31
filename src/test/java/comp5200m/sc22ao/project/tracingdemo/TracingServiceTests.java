package comp5200m.sc22ao.project.tracingdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import comp5200m.sc22ao.project.tracingdemo.model.TraceSpan;
import comp5200m.sc22ao.project.tracingdemo.service.TracingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

@SpringJUnitConfig
@SpringBootTest
public class TracingServiceTests {
    final String TEST_SPANS = "[{\"traceId\":\"a509109edb71a7e5b001f040effb8821\",\"name\":\"productpage.default.svc.cluster.local:9080/productpage\",\"timestamp\":\"1691269396825829\",\"parentId\":null,\"kind\":\"CLIENT\",\"id\":\"b001f040effb8821\",\"localEndpoint\":{\"port\":0,\"serviceName\":\"istio-ingressgateway.istio-system\",\"ipv4\":\"10.244.0.38\"},\"tags\":{\"guid:x-request-id\":\"f1433378-ab77-9676-8c21-00fbb260b8ad\",\"response_flags\":\"-\",\"upstream_cluster.name\":\"outbound|9080||productpage.default.svc.cluster.local\",\"http.url\":\"http://127.0.0.1/productpage\",\"http.protocol\":\"HTTP/1.1\",\"istio.canonical_service\":\"istio-ingressgateway\",\"peer.address\":\"10.244.0.1\",\"istio.namespace\":\"istio-system\",\"node_id\":\"router~10.244.0.38~istio-ingressgateway-dc7bfc496-t289z.istio-system~istio-system.svc.cluster.local\",\"response_size\":5290,\"http.status_code\":200,\"request_size\":0,\"istio.mesh_id\":\"cluster.local\",\"istio.canonical_revision\":\"latest\",\"user_agent\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36\",\"downstream_cluster\":\"-\",\"component\":\"proxy\",\"upstream_cluster\":\"outbound|9080||productpage.default.svc.cluster.local\",\"http.method\":\"GET\"},\"annotations\":[{\"value\":\"cr\",\"timestamp\":1691269397591959}],\"duration\":761557},{\"traceId\":\"a509109edb71a7e5b001f040effb8821\",\"name\":\"reviews.default.svc.cluster.local:9080/*\",\"timestamp\":\"1691269397210486\",\"parentId\":\"d188adeb62585024\",\"kind\":\"CLIENT\",\"id\":\"d2cd0bd052faf563\",\"localEndpoint\":{\"port\":0,\"serviceName\":\"productpage.default\",\"ipv4\":\"10.244.0.44\"},\"tags\":{\"guid:x-request-id\":\"f1433378-ab77-9676-8c21-00fbb260b8ad\",\"response_flags\":\"-\",\"upstream_cluster.name\":\"outbound|9080||reviews.default.svc.cluster.local\",\"http.url\":\"http://reviews:9080/reviews/0\",\"http.protocol\":\"HTTP/1.1\",\"istio.canonical_service\":\"productpage\",\"peer.address\":\"10.244.0.44\",\"istio.namespace\":\"default\",\"node_id\":\"sidecar~10.244.0.44~productpage-v1-58b4c9bff8-snzzg.default~default.svc.cluster.local\",\"response_size\":438,\"http.status_code\":200,\"request_size\":0,\"istio.mesh_id\":\"cluster.local\",\"istio.canonical_revision\":\"v1\",\"user_agent\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36\",\"downstream_cluster\":\"-\",\"component\":\"proxy\",\"upstream_cluster\":\"outbound|9080||reviews.default.svc.cluster.local\",\"http.method\":\"GET\"},\"annotations\":[{\"value\":\"cr\",\"timestamp\":1691269397571075}],\"duration\":360442},{\"traceId\":\"a509109edb71a7e5b001f040effb8821\",\"name\":\"productpage.default.svc.cluster.local:9080/productpage\",\"timestamp\":\"1691269396851946\",\"parentId\":\"b001f040effb8821\",\"kind\":\"SERVER\",\"id\":\"d188adeb62585024\",\"localEndpoint\":{\"port\":0,\"serviceName\":\"productpage.default\",\"ipv4\":\"10.244.0.44\"},\"tags\":{\"guid:x-request-id\":\"f1433378-ab77-9676-8c21-00fbb260b8ad\",\"response_flags\":\"-\",\"upstream_cluster.name\":\"inbound|9080||\",\"http.url\":\"http://127.0.0.1/productpage\",\"http.protocol\":\"HTTP/1.1\",\"istio.canonical_service\":\"productpage\",\"peer.address\":\"10.244.0.38\",\"istio.namespace\":\"default\",\"node_id\":\"sidecar~10.244.0.44~productpage-v1-58b4c9bff8-snzzg.default~default.svc.cluster.local\",\"response_size\":5290,\"http.status_code\":200,\"request_size\":0,\"istio.mesh_id\":\"cluster.local\",\"istio.canonical_revision\":\"v1\",\"user_agent\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36\",\"downstream_cluster\":\"-\",\"component\":\"proxy\",\"upstream_cluster\":\"inbound|9080||\",\"http.method\":\"GET\"},\"annotations\":[{\"value\":\"ss\",\"timestamp\":1691269397586570}],\"duration\":731193},{\"traceId\":\"a509109edb71a7e5b001f040effb8821\",\"name\":\"ratings.default.svc.cluster.local:9080/*\",\"timestamp\":\"1691269397477165\",\"parentId\":\"4c27e8b8c9e80f85\",\"kind\":\"SERVER\",\"id\":\"5b52530c0f4b3f62\",\"localEndpoint\":{\"port\":0,\"serviceName\":\"ratings.default\",\"ipv4\":\"10.244.0.39\"},\"tags\":{\"guid:x-request-id\":\"f1433378-ab77-9676-8c21-00fbb260b8ad\",\"response_flags\":\"-\",\"upstream_cluster.name\":\"inbound|9080||\",\"http.url\":\"http://ratings:9080/ratings/0\",\"http.protocol\":\"HTTP/1.1\",\"istio.canonical_service\":\"ratings\",\"peer.address\":\"10.244.0.37\",\"istio.namespace\":\"default\",\"node_id\":\"sidecar~10.244.0.39~ratings-v1-b8f8fcf49-gr9dk.default~default.svc.cluster.local\",\"response_size\":48,\"http.status_code\":200,\"request_size\":0,\"istio.mesh_id\":\"cluster.local\",\"istio.canonical_revision\":\"v1\",\"user_agent\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36\",\"downstream_cluster\":\"-\",\"component\":\"proxy\",\"upstream_cluster\":\"inbound|9080||\",\"http.method\":\"GET\"},\"annotations\":[{\"value\":\"ss\",\"timestamp\":1691269397536939}],\"duration\":58373},{\"traceId\":\"a509109edb71a7e5b001f040effb8821\",\"name\":\"ratings.default.svc.cluster.local:9080/*\",\"timestamp\":\"1691269397470786\",\"parentId\":\"7e0df8d16f52d673\",\"kind\":\"CLIENT\",\"id\":\"4c27e8b8c9e80f85\",\"localEndpoint\":{\"port\":0,\"serviceName\":\"reviews.default\",\"ipv4\":\"10.244.0.37\"},\"tags\":{\"guid:x-request-id\":\"f1433378-ab77-9676-8c21-00fbb260b8ad\",\"response_flags\":\"-\",\"upstream_cluster.name\":\"outbound|9080||ratings.default.svc.cluster.local\",\"http.url\":\"http://ratings:9080/ratings/0\",\"http.protocol\":\"HTTP/1.1\",\"istio.canonical_service\":\"reviews\",\"peer.address\":\"10.244.0.37\",\"istio.namespace\":\"default\",\"node_id\":\"sidecar~10.244.0.37~reviews-v3-589cb4d56c-7s9fc.default~default.svc.cluster.local\",\"response_size\":48,\"http.status_code\":200,\"request_size\":0,\"istio.mesh_id\":\"cluster.local\",\"istio.canonical_revision\":\"v3\",\"user_agent\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36\",\"downstream_cluster\":\"-\",\"component\":\"proxy\",\"upstream_cluster\":\"outbound|9080||ratings.default.svc.cluster.local\",\"http.method\":\"GET\"},\"annotations\":[{\"value\":\"cr\",\"timestamp\":1691269397540744}],\"duration\":68693},{\"traceId\":\"a509109edb71a7e5b001f040effb8821\",\"name\":\"reviews.default.svc.cluster.local:9080/*\",\"timestamp\":\"1691269397221443\",\"parentId\":\"d2cd0bd052faf563\",\"kind\":\"SERVER\",\"id\":\"7e0df8d16f52d673\",\"localEndpoint\":{\"port\":0,\"serviceName\":\"reviews.default\",\"ipv4\":\"10.244.0.37\"},\"tags\":{\"guid:x-request-id\":\"f1433378-ab77-9676-8c21-00fbb260b8ad\",\"response_flags\":\"-\",\"upstream_cluster.name\":\"inbound|9080||\",\"http.url\":\"http://reviews:9080/reviews/0\",\"http.protocol\":\"HTTP/1.1\",\"istio.canonical_service\":\"reviews\",\"peer.address\":\"10.244.0.44\",\"istio.namespace\":\"default\",\"node_id\":\"sidecar~10.244.0.37~reviews-v3-589cb4d56c-7s9fc.default~default.svc.cluster.local\",\"response_size\":438,\"http.status_code\":200,\"request_size\":0,\"istio.mesh_id\":\"cluster.local\",\"istio.canonical_revision\":\"v3\",\"user_agent\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36\",\"downstream_cluster\":\"-\",\"component\":\"proxy\",\"upstream_cluster\":\"inbound|9080||\",\"http.method\":\"GET\"},\"annotations\":[{\"value\":\"ss\",\"timestamp\":1691269397565884}],\"duration\":342200},{\"traceId\":\"a509109edb71a7e5b001f040effb8821\",\"name\":\"details.default.svc.cluster.local:9080/*\",\"timestamp\":\"1691269397049003\",\"parentId\":\"d188adeb62585024\",\"kind\":\"CLIENT\",\"id\":\"4db840641f65a431\",\"localEndpoint\":{\"port\":0,\"serviceName\":\"productpage.default\",\"ipv4\":\"10.244.0.44\"},\"tags\":{\"guid:x-request-id\":\"f1433378-ab77-9676-8c21-00fbb260b8ad\",\"response_flags\":\"-\",\"upstream_cluster.name\":\"outbound|9080||details.default.svc.cluster.local\",\"http.url\":\"http://details:9080/details/0\",\"http.protocol\":\"HTTP/1.1\",\"istio.canonical_service\":\"productpage\",\"peer.address\":\"10.244.0.44\",\"istio.namespace\":\"default\",\"node_id\":\"sidecar~10.244.0.44~productpage-v1-58b4c9bff8-snzzg.default~default.svc.cluster.local\",\"response_size\":178,\"http.status_code\":200,\"request_size\":0,\"istio.mesh_id\":\"cluster.local\",\"istio.canonical_revision\":\"v1\",\"user_agent\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36\",\"downstream_cluster\":\"-\",\"component\":\"proxy\",\"upstream_cluster\":\"outbound|9080||details.default.svc.cluster.local\",\"http.method\":\"GET\"},\"annotations\":[{\"value\":\"cr\",\"timestamp\":1691269397201862}],\"duration\":152691},{\"traceId\":\"a509109edb71a7e5b001f040effb8821\",\"name\":\"details.default.svc.cluster.local:9080/*\",\"timestamp\":\"1691269397055316\",\"parentId\":\"4db840641f65a431\",\"kind\":\"SERVER\",\"id\":\"3d682637114fb437\",\"localEndpoint\":{\"port\":0,\"serviceName\":\"details.default\",\"ipv4\":\"10.244.0.42\"},\"tags\":{\"guid:x-request-id\":\"f1433378-ab77-9676-8c21-00fbb260b8ad\",\"response_flags\":\"-\",\"upstream_cluster.name\":\"inbound|9080||\",\"http.url\":\"http://details:9080/details/0\",\"http.protocol\":\"HTTP/1.1\",\"istio.canonical_service\":\"details\",\"peer.address\":\"10.244.0.44\",\"istio.namespace\":\"default\",\"node_id\":\"sidecar~10.244.0.42~details-v1-6997d94bb9-dj5w4.default~default.svc.cluster.local\",\"response_size\":178,\"http.status_code\":200,\"request_size\":0,\"istio.mesh_id\":\"cluster.local\",\"istio.canonical_revision\":\"v1\",\"user_agent\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36\",\"downstream_cluster\":\"-\",\"component\":\"proxy\",\"upstream_cluster\":\"inbound|9080||\",\"http.method\":\"GET\"},\"annotations\":[{\"value\":\"ss\",\"timestamp\":1691269397198172}],\"duration\":139824}]";
    private List<TraceSpan> spans;

    @Autowired
    private TracingService tracingService;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.spans = new ObjectMapper().readValue(TEST_SPANS, new TypeReference<>() {});
    }

    @Test
    void creation() throws JsonProcessingException {
        List<?> spansList = new ObjectMapper().readValue(TEST_SPANS, List.class);
        Assertions.assertEquals(this.spans, tracingService.convertObjectToSpans(spansList));
    }

    @Test
    void findAllTraces() {
        ResponseEntity<?> response = tracingService.findAllSpans(null);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void findAllSpans_Success() {
        String traceId = this.spans.get(0).getTraceId();
        ResponseEntity<?> response = tracingService.findAllSpans(traceId);
        tracingService.sortSpans(this.spans);
        String expectedResponse = "<pre>" + tracingService.convertSpansToString(spans) + "</pre>";

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void findAllSpans_NotFound() {
        String traceId = "traceIdThatDoesNotExist";
        ResponseEntity<?> response = tracingService.findAllSpans(traceId);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void generateTraceReport_Success() {
        String traceId = this.spans.get(0).getTraceId();
        ResponseEntity<?> reportResponse = tracingService.generateTraceReport(traceId);

        Assertions.assertEquals(reportResponse.getStatusCode(), HttpStatus.OK);
        Assertions.assertNotNull(reportResponse.getBody());
        Assertions.assertNotEquals(reportResponse.getBody(), new ArrayList<>());
    }

    @Test
    void generateTraceReport_NotFound() {
        String traceId = "traceIdThatDoesNotExist";
        ResponseEntity<?> reportResponse = tracingService.generateTraceReport(traceId);

        Assertions.assertEquals(reportResponse.getStatusCode(), HttpStatus.NOT_FOUND);
        Assertions.assertEquals(reportResponse.getBody(), "Trace Not Found");
    }

    @Test
    void verifyDataConsistency() {
        String spanId = spans.get(0).getTraceId();
        TraceSpan span = tracingService.findTraceSpan(spanId);

        Assertions.assertEquals(span, spans.get(0));
    }
}
