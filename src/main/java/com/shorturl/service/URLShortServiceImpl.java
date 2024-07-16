package com.shorturl.service;

import com.shorturl.model.URLMapping;
import com.shorturl.repository.URLMappingRepository;
import com.shorturl.utility.StringUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class URLShortServiceImpl implements URLShortService {

    private final URLMappingRepository urlMappingRepository;
    private final CounterService counterService;

    private final StringUtility stringUtility;


    @Autowired
    public URLShortServiceImpl(URLMappingRepository urlMappingRepository, CounterServiceImpl counterService, StringUtility stringUtility) {
        this.urlMappingRepository = urlMappingRepository;
        this.counterService = counterService;
        this.stringUtility = stringUtility;
    }

    /**
     * Save the Shorten URL
     * */
    @Override
    public String shortenUrl(String originalUrl) {
        Optional<URLMapping> mappingOptional= urlMappingRepository.findByOriginalUrl(originalUrl);

        if(mappingOptional.isPresent()){
            return stringUtility.getFullShortURL(mappingOptional.get().getShortUrl());
        }

        String shortUrl = generateUniqueShortUrl();

        URLMapping urlMapping = new URLMapping();

        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(shortUrl);
        URLMapping saveMapping = urlMappingRepository.save(urlMapping);

        return stringUtility.getFullShortURL(saveMapping.getShortUrl());

    }

    public Optional<String> getOriginalUrl(String shortUrl) {
        Optional<URLMapping> byShortUrl = urlMappingRepository.findByShortUrl(stringUtility.getShortURLString(shortUrl));
        return byShortUrl.map(URLMapping::getOriginalUrl);
    }

    /**
     * Method to generate the Unique URL if it already exists generate new one
     * */
    private String generateUniqueShortUrl() {
        String shortUrl;
        do {
            String shortUrlCounter = String.valueOf(counterService.getNextSequence());
            shortUrl = stringUtility.getShortString(shortUrlCounter);
        } while (urlMappingRepository.findByShortUrl(shortUrl).isPresent());
        return shortUrl;
    }


}
