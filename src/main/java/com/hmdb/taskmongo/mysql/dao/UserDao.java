package com.hmdb.taskmongo.mysql.dao;

import com.hmdb.taskmongo.mysql.entity.MysqlUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    @Select("Select * from user where loginname = #{loginName}")
    List<MysqlUser> getUserById(@Param("loginName") String loginName);
}
