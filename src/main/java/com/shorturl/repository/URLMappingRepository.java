package com.shorturl.repository;

import com.shorturl.model.URLMapping;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface URLMappingRepository extends MongoRepository<URLMapping, Long> {
    Optional<URLMapping> findByShortUrl(String shortUrl);
    Optional<URLMapping> findByOriginalUrl(String originalUrl);
}
