package com.example.mydegign.mapper;

import com.example.mydegign.entity.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersMapper {
    int deleteByPrimaryKey(Integer usersId);

    int insert(Users record);

    Users selectByPrimaryKey(Integer usersId);

    List<Users> selectAll();

    int updateByPrimaryKey(Users record);

    int updateByPrimaryKeySelective(Users record);

}