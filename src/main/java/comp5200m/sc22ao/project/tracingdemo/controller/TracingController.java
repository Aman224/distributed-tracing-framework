package comp5200m.sc22ao.project.tracingdemo.controller;

import comp5200m.sc22ao.project.tracingdemo.model.Trace;
import comp5200m.sc22ao.project.tracingdemo.service.TracingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class TracingController {
    final Logger logger = LoggerFactory.getLogger(TracingController.class);

    @Autowired
    private TracingService tracingService;

    @PostMapping("/api/v2/spans")
    public void getSpansV2JSONMapping(@RequestBody Object request) {
        tracingService.saveSpans(request);
    }

    @PostMapping("/api/v1/spans")
    public void getSpansV1JSONMapping(@RequestBody Object request) {
        logger.info("Put Spans V1");
    }

    @GetMapping("/traces/find-all")
    public List<Trace> getAllTraces() {
        return tracingService.findAllTraces();
    }

    @GetMapping("/test")
    public String test() {
        logger.info("Test");
        return "Hello";
    }

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}
