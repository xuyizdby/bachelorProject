package com.project1.service.impl;
import com.project1.dao.mapper.UserMapper;
import com.project1.model.User;
import com.project1.service.IUserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 登录
     * @param loginName
     * @param password
     * @return
     */

    public User loginUser(String loginName, String password) {
        User user = new User();
        user.setUsername(loginName);
        List<User> userList =userMapper.getUserList(user);
        if(userList.isEmpty()){
            return null;
        }else{//进行密码比对
            String ecnryptPassword = userList.get(0).getPassword();
            if(ecnryptPassword.equals(password)) {
                return userList.get(0);
            }
            else {
                return null;
            }
        }
    }
    public String findUserinfo(String username){
        User user=new User();
        user.setUsername(username);
        List<User> userList =userMapper.getUserList(user);
        if(userList.isEmpty()){
            return null;
        }else{
            return userList.get(0).getId().toString();
        }

        }

    @Override
    public List<User> findList(Map<String,Object> queryMap) {
        return userMapper.findList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return userMapper.getTotal(queryMap);
    }

    @Override
    public int add(User user) {
        return userMapper.add(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int deleteOne(Long id) {
        return userMapper.deleteOne(id);
    }

    @Override
    public int deleteMany(String ids) {
        return userMapper.deleteMany(ids);
    }

}
