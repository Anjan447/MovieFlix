package com.anjan.MovieFlix.ServiceImpl;

import com.anjan.MovieFlix.Repo.ReviewRepo;
import com.anjan.MovieFlix.Service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalyticsServiceImpl implements AnalyticsService {

    @Autowired
    ReviewRepo reviewRepo;

    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        System.out.println("inside getCount");

        Map<String, Object> map = new HashMap<>();
        map.put("review", reviewRepo.count());

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
