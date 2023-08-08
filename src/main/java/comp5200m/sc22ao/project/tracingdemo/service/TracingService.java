package comp5200m.sc22ao.project.tracingdemo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import comp5200m.sc22ao.project.tracingdemo.visualisation.TraceTreeNode;
import comp5200m.sc22ao.project.tracingdemo.repository.TracingRepository;
import comp5200m.sc22ao.project.tracingdemo.model.TraceSpan;

import comp5200m.sc22ao.project.tracingdemo.visualisation.TraceTreeTraversal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TracingService {
    final Logger logger = LoggerFactory.getLogger(TracingService.class);

    @Autowired
    private TracingRepository tracingRepository;

    public void saveSpans(Object spans) {
        try {
            List<TraceSpan> traceSpans = convertObjectToSpans(spans);
            save(traceSpans);
            logger.info("Traces Saved Successfully");
        } catch (Exception e) {
            logger.error("Error saving Traces: {}", e.getMessage());
        }
    }

    public void save(List<TraceSpan> traceSpans) {
        tracingRepository.saveAll(traceSpans);
    }

    public ResponseEntity<?> findAllSpans(String traceId) {
        List<TraceSpan> traceSpans = findTraceSpans(traceId);

        return new ResponseEntity<>(traceSpans, HttpStatus.OK);
    }

    public ResponseEntity<?> generateTraceReport(String traceId) {
        List<TraceSpan> traceSpans = findTraceSpans(traceId);

        Map<String, Set<TraceSpan>> parentIdToChildSpansMap = new HashMap<>();
        TraceTreeNode rootNode = null;

        for (TraceSpan span : traceSpans) {
            if (span.getParentId() != null) {
                Set<TraceSpan> children = parentIdToChildSpansMap.get(span.getParentId());
                if (children == null) {
                    children = new LinkedHashSet<>();
                }
                children.add(span);
                parentIdToChildSpansMap.put(span.getParentId(), children);
            } else {
                rootNode = new TraceTreeNode(span.getId(), span.getName(), span.getDuration());
            }
        }

        assert rootNode != null;
        rootNode.populateTree(rootNode, parentIdToChildSpansMap);

        String traceDiagram = TraceTreeTraversal.dfsTraversal(rootNode, 0);

        logger.info("Trace Propagation Between Services\n {}", traceDiagram);
        return new ResponseEntity<>("<pre>" + traceDiagram + "</pre>", HttpStatus.OK);
    }

    public ResponseEntity<?> generateCompleteTraceReport() {
        List<TraceSpan> traceSpans = findTraceSpans(null);
        return new ResponseEntity<>(traceSpans, HttpStatus.OK);
    }

    private List<TraceSpan> convertObjectToSpans(Object spans) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(spans, new TypeReference<>(){});
    }

    private List<TraceSpan> findTraceSpans(String traceId) {
        if (traceId != null) {
            return tracingRepository.findAll()
                    .stream()
                    .filter(traceSpan -> traceSpan.getTraceId().equals(traceId))
                    .toList();
        } else {
            return tracingRepository.findAll();
        }
    }
}
