package mevmax.mapper;

import mevmax.entity.Pool;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PoolMapper {
    @Select("SELECT * FROM \"Pool\";")
    List<Pool> findAll();

    @Select("SELECT * FROM \"Pool\" WHERE pool_address = #{poolAddress}")
    List<Pool> findByPoolAddress(@Param("poolAddress") String poolAddress);

    @Select("SELECT COUNT(*) FROM \"Pool\"")
    long countTotalPools();
}
