package mevmax.controller;

import jakarta.annotation.Resource;
import mevmax.mapper.PoolMapper;
import mevmax.mapper.TokenMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tokens-count")
public class TokenCountController {
    @Resource
    TokenMapper tokenMapper;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getTotalPoolCount() {
        long totalTokens = tokenMapper.countTotalTokens();
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Success");
        response.put("total_tokens", totalTokens);
        return ResponseEntity.ok(response);
    }
}