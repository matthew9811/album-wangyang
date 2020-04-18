package com.shengxi.wangyang.mapper;

import com.shengxi.wangyang.entity.Photo;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PhotoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Photo record);

    int insertSelective(Photo record);

    Photo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Photo record);

    int updateByPrimaryKey(Photo record);

    /**
     * 以openId作为第一限定条件，获取时间区间内的所有数据
     *
     * @param startTime 搜索开始时间
     * @param endTime   搜索结束时间
     * @param openId    openId
     * @return list Of selected Photo
     */
    List<Photo> selectPhotoList(@Param("startTime") Date startTime,
                                @Param("endTime") Date endTime, @Param("openId") String openId);

    /**
     * Delete the corresponding data in the selected list
     *
     * @param ids ids[]
     * @return a result is number of deleted
     */
    int deleteByIdList(@Param("ids") Integer[] ids);

    /**
     * Delete the corresponding data in the selected list
     *
     * @param ids ids[]
     * @return a result is number of deleted
     */
    int deleteByAlbumsIdList(@Param("ids") Integer[] ids);

    Integer insertByList(List<Photo> list);

    /**
     * get all date for table
     * @return list
     */
    List<Date> selectPhotoFimingTime();

    List<Photo> search(@Param("temp") String temp, @Param("openid") String openid);
}