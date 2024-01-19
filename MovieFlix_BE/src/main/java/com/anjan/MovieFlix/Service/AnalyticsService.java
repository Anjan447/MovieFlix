package com.anjan.MovieFlix.Service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AnalyticsService {
    ResponseEntity<Map<String, Object>> getCount();
}
