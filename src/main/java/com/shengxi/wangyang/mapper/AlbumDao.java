package com.shengxi.wangyang.mapper;

import com.shengxi.wangyang.entity.Album;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AlbumDao {


    int deleteByPrimaryKey(Integer id);

    Album selectByPrimaryKey(Integer id);


    /**
     * Get all albums with a unique openId
     *
     * @param openId openId
     * @return All albums of selected
     */
    List<Album> selectAlbumListByOpenId(String openId);

    /**
     * insert a data that is a new album
     *
     * @param openId
     * @param albumName
     * @return
     */
    int insertByName(@Param("openId") String openId, @Param("albumName") String albumName);

    int deleteByIdList(Integer[] albumIds);

    Integer selectIdByName(String albumName);

    int selectByName(String albumName);
}