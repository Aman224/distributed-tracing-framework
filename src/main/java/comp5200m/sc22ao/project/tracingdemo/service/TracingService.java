package comp5200m.sc22ao.project.tracingdemo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import comp5200m.sc22ao.project.tracingdemo.repository.TracingRepository;
import comp5200m.sc22ao.project.tracingdemo.model.Trace;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TracingService {
    final Logger logger = LoggerFactory.getLogger(TracingService.class);

    @Autowired
    private TracingRepository tracingRepository;

    public void saveSpans(Object spans) {
        logger.info("Storing Spans");
        try {
            logger.info(((ArrayList)spans).get(0).toString());
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage());
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            logger.info(spans.toString());
            List<Trace> traces = mapper.convertValue(spans, new TypeReference<>(){});
            logger.info("Trace generated in Trace object format");

            logger.info("Size: {}", traces.size());
            logger.info("Trace Id's");
            for (Trace trace : traces) {
                logger.info("Id: {} CanonicalService: {}",trace.getTraceId(), trace.getTags().getIstioCanonicalService());
            }

            save(traces);
            logger.info("Saved");
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage());
        }
    }

    public void save(Trace trace) {
        tracingRepository.save(trace);
    }

    public void save(List<Trace> traces) {
        tracingRepository.saveAll(traces);
    }

    public List<Trace> findAllTraces() {
        return tracingRepository.findAll();
    }
}
