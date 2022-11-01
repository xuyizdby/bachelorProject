package com.project1.service;
import com.project1.model.User;

import java.util.List;
import java.util.Map;

public interface IUserService {
    /**
     * 登陆验证
     * @return
     */
    public User loginUser(String loginName,String password);
    public int add(User user);
    public int update(User user);
    public int deleteOne(Long id);
    public int deleteMany(String ids);
    public String findUserinfo(String username);
    public List<User> findList(Map<String,Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
}