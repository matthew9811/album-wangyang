package com.shengxi.wangyang.mapper;

import com.shengxi.wangyang.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
}