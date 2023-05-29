package com.example.test2.mappers;

import com.example.test2.entities.BusinessCardEntity;
import com.example.test2.vos.BusinessCardVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IMainMapper {
    BusinessCardEntity[] selectBusinessCardEntity(@Param(value = "userId") int userId,
                                                  @Param(value = "limit") int limit,
                                                  @Param(value = "offset") int offset);

    int selectTotalCount(@Param(value = "userId") int userId);

    int deleteBusinessCard(@Param(value = "index") int index);

    int insertBusinessCard(BusinessCardEntity businessCardEntity);

    BusinessCardEntity selectOneInfo(@Param(value = "index") int index);

    int updateBusinessCard(BusinessCardEntity businessCardEntity);
}
