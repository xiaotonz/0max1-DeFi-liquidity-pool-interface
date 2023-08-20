package mevmax.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Pool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pool_id;
    private String pool_address;
    private Integer protocol_id;
    private Integer blockchain_id;
    private BigDecimal tvl;
    private BigDecimal fee;
    private Boolean flag;
}

