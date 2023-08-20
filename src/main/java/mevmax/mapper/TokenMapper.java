package mevmax.mapper;

import mevmax.entity.Token;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TokenMapper {
    @Select("SELECT * FROM \"Token\" WHERE token_address = #{tokenAddress}")
    List<Token> findTokenByAddress(@Param("tokenAddress") String tokenAddress);

    @Select("SELECT * FROM \"Token\" WHERE token_symbol = #{tokenName}")
    List<Token> findTokenByName(@Param("tokenName") String tokenName);

    @Select("SELECT * FROM \"Token\"")
    List<Token> findAllTokens();

    @Select("SELECT COUNT(*) FROM \"Token\"")
    long countTotalTokens();

}

