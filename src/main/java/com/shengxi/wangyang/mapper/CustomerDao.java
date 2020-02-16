package com.shengxi.wangyang.mapper;

import com.shengxi.wangyang.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CustomerDao {
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据
     *
     * @param openid String
     * @return 插入的条数
     */
    int insert(String openid);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
}