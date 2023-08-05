package comp5200m.sc22ao.project.tracingdemo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import comp5200m.sc22ao.project.tracingdemo.repository.TracingRepository;
import comp5200m.sc22ao.project.tracingdemo.model.TraceSpan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static comp5200m.sc22ao.project.tracingdemo.repository.utils.QueryUtils.*;

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

        Map<String, Set<String>> parentToChildMap = new HashMap<>();
        String parent = null;
        for (TraceSpan traceSpan : traceSpans) {
            if (traceSpan.getParentId() == null) {
                parent = traceSpan.getId();
            }
            if (traceSpan.getParentId() != null) {
                Set<String> children = parentToChildMap.get(traceSpan.getParentId());
                if (children == null) {
                    children = new LinkedHashSet<>();
                }
                children.add(traceSpan.getId());
                parentToChildMap.put(traceSpan.getParentId(), children);
            }
        }

        Map<String, String> spanIdToServiceNameMap = new HashMap<>();

        for (TraceSpan traceSpan : traceSpans) {
            spanIdToServiceNameMap.put(traceSpan.getId(), traceSpan.getTags().getIstioCanonicalService());
        }


        String message = parent + " -> ";
        Set<String> children = parentToChildMap.get(parent);

        logger.info(message);

        return new ResponseEntity<>(traceSpans, HttpStatus.OK);
    }

    private void dfsPreorder(String node, Map<String, Set<String>> parentToChildMap) {
        if (node == null) {
            return;
        }
        logger.info(node + "->");

        List<String> children = new ArrayList<>(parentToChildMap.get(node));

        if (children.size() > 0) {
            dfsPreorder(children.get(0), parentToChildMap);
        }
        if (children.size() > 1) {
            dfsPreorder(children.get(1), parentToChildMap);
        }
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
