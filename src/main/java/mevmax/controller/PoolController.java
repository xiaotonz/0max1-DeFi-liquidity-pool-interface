package mevmax.controller;

import jakarta.annotation.Resource;
import mevmax.entity.Pool;
import mevmax.mapper.PoolMapper;
import org.apache.ibatis.type.NStringTypeHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// use @RestController makes all data render into json format
@RestController
@RequestMapping("/pools")
public class PoolController {
    @Resource
    PoolMapper poolMapper;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPools() {
        List<Pool> pools = poolMapper.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Success");
        response.put("pools", pools);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{poolAddress}")
    public ResponseEntity<Map<String, Object>> getPoolByAddress(@PathVariable String poolAddress) {
        List<Pool> pools = poolMapper.findByPoolAddress(poolAddress);
        if (pools.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("message", "Pool not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Success");
        response.put("pools", pools);
        return ResponseEntity.ok(response);
    }
}

