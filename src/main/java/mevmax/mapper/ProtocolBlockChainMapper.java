package mevmax.mapper;

import mevmax.entity.BlockChain;
import mevmax.entity.Protocol;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProtocolBlockChainMapper {
    @Select("SELECT * FROM \"Protocol\"")
    List<Protocol> findAllProtocols();

    @Select("SELECT * FROM \"BlockChain\"")
    List<BlockChain> findAllBlockChains();
    @Select("SELECT SUM(tvl) FROM \"Pool\"")
    double findTotalTvl();
    @Select("SELECT * FROM \"Protocol\" WHERE factory_address = #{factoryAddress}")
    List<Protocol> findProtocolByAddress(@Param("factoryAddress") String factoryAddress);
    @Select("SELECT * FROM \"Protocol\" WHERE protocol_name = #{protocolName}")
    List<Protocol> findProtocolByName(@Param("protocolName") String protocolName);
}
