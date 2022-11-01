package com.project1.dao.mapper;

import com.project1.model.User;

import java.util.List;
import java.util.Map;


public interface UserMapper {
    /**
     * 根据条件查询用户列表
     * @param user
     * @return
     */
    List<User> getUserList(User user);
    public int add(User user);
    public int update(User user);
    public int deleteOne(Long id);
    public int deleteMany(String ids);
    public List<User> findList(Map<String,Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
//    public User findUserinfo(String username);
}
