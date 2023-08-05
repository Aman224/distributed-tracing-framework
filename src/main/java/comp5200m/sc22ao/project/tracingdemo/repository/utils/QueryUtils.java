package comp5200m.sc22ao.project.tracingdemo.repository.utils;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class QueryUtils {
    public static Query buildEqualsQuery(String key, String value) {
        return new Query(Criteria.where(key).is(value));
    }
}
