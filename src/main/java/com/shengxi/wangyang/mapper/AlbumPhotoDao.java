package com.shengxi.wangyang.mapper;

import com.shengxi.wangyang.entity.AlbumPhoto;
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


    List<AlbumPhoto> selectAlbumDatailByAlbumId(Integer albumId);

    int deleteByAlbumIdList(Integer[] albumIds);

    int replaceAlbumPhoto(@Param("albumId") Integer albumId, @Param("photoIds") Integer[] photoIds);

}