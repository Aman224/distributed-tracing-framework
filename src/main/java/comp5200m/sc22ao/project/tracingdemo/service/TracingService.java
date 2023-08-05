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

import java.util.List;
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
        List<TraceSpan> traceSpans;

        if (traceId != null) {
            // For future improvements
            Query query = buildEqualsQuery("traceId", traceId);
            traceSpans = tracingRepository.findAll()
                    .stream()
                    .filter(traceSpan -> traceSpan.getTraceId().equals(traceId))
                    .collect(Collectors.toList());
        } else {
            traceSpans = tracingRepository.findAll();
        }

        return new ResponseEntity<>(traceSpans, HttpStatus.OK);
    }

    private List<TraceSpan> convertObjectToSpans(Object spans) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(spans, new TypeReference<>(){});
    }
}
