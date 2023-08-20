package mevmax.controller;

import jakarta.annotation.Resource;
import mevmax.mapper.PoolMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pools-count")
public class PoolCountController {
    @Resource
    PoolMapper poolMapper;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getTotalPoolCount() {
        long totalPools = poolMapper.countTotalPools();
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Success");
        response.put("total_pools", totalPools);
        return ResponseEntity.ok(response);
    }
}

