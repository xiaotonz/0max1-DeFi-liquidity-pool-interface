package mevmax.mapper;

import mevmax.entity.Pair;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PairMapper {
    @Select("SELECT COUNT(*) FROM \"Pair\"")
    long countTotalPairs();

    @Select("SELECT * FROM \"Pair\" WHERE token1_address = #{token1_address} AND token2_address = #{token2_address}")
    List<Pair> findByTokenAddresses(@Param("token1_address") String token1_address,
                                    @Param("token2_address") String token2_address);

    @Select("SELECT * FROM \"Pair\" WHERE token1_address = #{token_address} OR token2_address = #{token_address}")
    List<Pair> findByATokenAddress(@Param("token_address") String token_address);
}
