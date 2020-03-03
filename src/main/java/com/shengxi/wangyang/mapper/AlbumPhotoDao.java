package com.shengxi.wangyang.mapper;

import com.shengxi.wangyang.entity.AlbumPhoto;
import com.shengxi.wangyang.entity.Photo;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AlbumPhotoDao {
    int deleteByPrimaryKey(Integer id);


    int insertSelective(AlbumPhoto record);

    int updateByPrimaryKeySelective(AlbumPhoto record);


    List<Photo> selectAlbumDatailByAlbumId(@Param("albumId") Integer albumId,
                                           @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    int deleteByAlbumIdList(@Param("albumIds") Integer[] albumIds);

    int replaceAlbumPhoto(@Param("albumId") Integer albumId, @Param("photoIds") Integer[] photoIds);

    List<Date> selectPhotoFimingTime(@Param("albumId") Integer albumId);

}