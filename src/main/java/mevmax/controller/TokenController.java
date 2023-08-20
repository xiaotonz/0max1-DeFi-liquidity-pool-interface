package mevmax.controller;

import jakarta.annotation.Resource;
import mevmax.entity.Token;
import mevmax.mapper.TokenMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tokens")
public class TokenController {
    @Resource
    TokenMapper tokenMapper;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTokens() {
        List<Token> tokens = tokenMapper.findAllTokens();
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Success");
        response.put("tokens", tokens);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{tokenAddress}")
    public ResponseEntity<Map<String, Object>> getTokenByAddress(@PathVariable String tokenAddress) {
        List<Token> token = tokenMapper.findTokenByAddress(tokenAddress);
        if (token.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("message", "Token not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Success");
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{tokenName}")
    public ResponseEntity<Map<String, Object>> getTokenByName(@PathVariable String tokenName) {
        List<Token> token = tokenMapper.findTokenByName(tokenName);
        if (token.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("message", "Token not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Success");
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}

