package com.shengxi.wangyang.mapper;

import com.shengxi.wangyang.entity.Photo;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Photo record);

    int insertSelective(Photo record);

    Photo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Photo record);

    int updateByPrimaryKey(Photo record);
}