package com.example.mydegign.service;

import com.example.mydegign.entity.Users;
import com.example.mydegign.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersMapper mapper;

    public List<Users> selectAll() {
        return mapper.selectAll();
    }

    public int insert(Users users) {
        return mapper.insert(users);
    }

    public Users selectById(int usersId) {
        return mapper.selectByPrimaryKey(usersId);
    }

    public int updateByPrimaryKeySelective(Users record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

}
