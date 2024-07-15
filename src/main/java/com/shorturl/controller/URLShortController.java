package com.shorturl.controller;

import com.shorturl.model.URLMapping;
import com.shorturl.service.URLShortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url")
public class URLShortController {
    @Autowired
    private URLShortService urlShortService;

    @PostMapping("/shorten")
    public ResponseEntity<URLMapping> shortenUrl(@RequestParam String originalUrl) {
        URLMapping urlMapping = urlShortService.shortenUrl(originalUrl);
        return ResponseEntity.ok(urlMapping);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<URLMapping> getOriginalUrl(@PathVariable String shortUrl) {
        URLMapping urlMapping = urlShortService.getOriginalUrl(shortUrl);
        if (urlMapping != null) {
            return ResponseEntity.ok(urlMapping);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
