package com.example.mydegign.mapper;

import com.example.mydegign.entity.UsersAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersAccountMapper {
    int deleteByPrimaryKey(Integer usersAccountId);

    int insert(UsersAccount record);

    UsersAccount selectByPrimaryKey(Integer usersAccountId);

    List<UsersAccount> selectAll();

    int updateByPrimaryKey(UsersAccount record);

    UsersAccount selectByAccount(String account);

    int updateByPrimaryKeySelective(UsersAccount record);


}