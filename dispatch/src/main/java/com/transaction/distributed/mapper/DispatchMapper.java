package com.transaction.distributed.mapper;

import com.transaction.distributed.entity.DispatchEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * DispatchMapper.java
 *
 * @author Ponder Yao
 * @version 1.0.0  2021/5/24 22:19
 */
@Mapper
public interface DispatchMapper {

    /**
     * 新增派单任务
     */
    @Insert("INSERT into platoon values (null,#{orderId},#{takeoutUserId});")
    public int insertDistribute(DispatchEntity distributeEntity);

}
