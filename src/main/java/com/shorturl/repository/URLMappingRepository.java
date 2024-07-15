package com.shorturl.repository;

import com.shorturl.model.URLMapping;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface URLMappingRepository extends MongoRepository<URLMapping, Long> {
    URLMapping findByShortUrl(String shortUrl);
}
