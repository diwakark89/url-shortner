package com.shorturl.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="URLMapping")
@Data
public class URLMapping {
    @Id
    private String id;
    private long counter;
    private String originalUrl;
    private String shortUrl;
}
