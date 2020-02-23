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


    Customer selectByPrimaryKey(Integer id);


    int judgeIsExist(String openid);

    Integer selectIdByJsCode(String openId);
}
