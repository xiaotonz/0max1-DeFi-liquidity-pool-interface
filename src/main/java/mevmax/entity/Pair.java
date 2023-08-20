package mevmax.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Pair {
    @Id
    private String pair_address;
    private String token1_address;
    private String token2_address;
    private String routes_data;
    private Boolean pair_flag;
}
