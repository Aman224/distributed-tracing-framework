package comp5200m.sc22ao.project.tracingdemo.repository;

import comp5200m.sc22ao.project.tracingdemo.model.Trace;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TracingRepository extends MongoRepository<Trace, String> {
}
