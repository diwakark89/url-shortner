package com.shorturl.service;

import com.shorturl.model.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.shorturl.constant.DBConstant.COUNTER_ID;
import static com.shorturl.constant.DBConstant.SEQUENCE_NAME;

@Service
public class CounterServiceImpl implements CounterService {

    @Value("${counter.start}")
    private long counterStartVal;

    private final MongoOperations mongoOperations;

    @Autowired
    public CounterServiceImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    /**
     * Get the new counter by incrementing the old Value by 1
     * */
    @Transactional
    public long getNextSequence() {
        Query query = new Query(Criteria.where("_id").is(COUNTER_ID));
        Update update = new Update().inc(SEQUENCE_NAME, 1);
        Counter counter = mongoOperations.findAndModify(query, update, Counter.class);

        if (counter == null) {
            Counter newCounter = new Counter();
            newCounter.setId(COUNTER_ID);
            newCounter.setSequence(counterStartVal);
            mongoOperations.save(newCounter);
            return counterStartVal;
        }

        return counter.getSequence();
    }
}
