package comp5200m.sc22ao.project.tracingdemo.controller;

import comp5200m.sc22ao.project.tracingdemo.service.TracingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TracingController {
    @Autowired
    private TracingService tracingService;

    @PostMapping("/api/v2/spans")
    public void getSpansV2JSONMapping(@RequestBody Object request) {
        tracingService.saveSpans(request);
    }

    @PostMapping("/api/v1/spans")
    public void getSpansV1JSONMapping(@RequestBody Object request) {
        tracingService.saveSpans(request);
    }

    @GetMapping("/trace/spans")
    public ResponseEntity<?> findAllSpans() {
        return tracingService.findAllSpans(null);
    }

    @GetMapping("/trace/{traceId}/spans")
    public ResponseEntity<?> getAllSpansForTrace(@PathVariable String traceId) {
        return tracingService.findAllSpans(traceId);
    }

    @GetMapping("/trace/{traceId}/spans/{spanId}")
    public ResponseEntity<?> getAllSpansForTrace(@PathVariable String traceId,
                                                 @PathVariable String spanId) {
        return tracingService.findSpan(traceId, spanId);
    }

    @GetMapping("/trace/{traceId}/visualise")
    public ResponseEntity<?> generateTraceReport(@PathVariable String traceId) {
        return tracingService.generateTraceReport(traceId);
    }
}
