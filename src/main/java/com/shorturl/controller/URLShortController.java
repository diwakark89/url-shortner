package com.shorturl.controller;

import com.shorturl.service.URLShortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/url")
public class URLShortController {
    @Autowired
    private URLShortService urlShortService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestParam String originalUrl) {
        String shortenUrl = urlShortService.shortenUrl(originalUrl);
        return ResponseEntity.ok(shortenUrl);
    }

    @GetMapping
    public ResponseEntity<String> getOriginalUrl(@RequestParam String shortUrl) {
        Optional<String> urlMapping = urlShortService.getOriginalUrl(shortUrl);
        return urlMapping.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
