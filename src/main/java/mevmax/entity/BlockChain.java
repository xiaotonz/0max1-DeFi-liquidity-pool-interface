package mevmax.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BlockChain {
    @Id
    private String blockchain_name;
}