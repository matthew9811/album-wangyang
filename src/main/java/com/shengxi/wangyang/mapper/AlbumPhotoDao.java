package com.shengxi.wangyang.mapper;

import com.shengxi.wangyang.entity.AlbumPhoto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AlbumPhotoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AlbumPhoto record);

    int insertSelective(AlbumPhoto record);

    AlbumPhoto selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AlbumPhoto record);

    int updateByPrimaryKey(AlbumPhoto record);
}