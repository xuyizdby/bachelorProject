package com.project1.service.impl;

import com.project1.dao.mapper.PreexamMapper;
import com.project1.model.Preexamer;
import com.project1.model.User;
import com.project1.service.PreexamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PreexamServiceImpl implements PreexamService {
    @Resource
    private PreexamMapper preexamMapper;

    /**
     * 登录
     * @return
     */

//    public User loginUser(String loginName, String password) {
//        User user = new User();
//        user.setUsername(loginName);
//        List<User> userList =preexamMapper.getUserList(user);
//        if(userList.isEmpty()){
//            return null;
//        }else{//进行密码比对
//            String ecnryptPassword = userList.get(0).getPassword();
//            if(ecnryptPassword.equals(password)) {
//                return userList.get(0);
//            }
//            else {
//                return null;
//            }
//        }
//    }
//    public String findUserinfo(String name){
//        Preexamer preexamer=new Preexamer();
//        preexamer.setName(name);
//        List<Preexamer> userList =preexamMapper.getUserList(preexamer);
//        if(userList.isEmpty()){
//            return null;
//        }else{
//            return userList.get(0).getId().toString();
//        }
//
//        }

    @Override
    public List<Preexamer> findList(Map<String,Object> queryMap) {
        return preexamMapper.findList(queryMap);
    }

    @Override
    public Preexamer findDetail(Map<String, Object> queryMap) {
        return preexamMapper.findDetail(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return preexamMapper.getTotal(queryMap);
    }

    @Override
    public List<Map> sexStats() {
        return preexamMapper.sexStats();
    }

    @Override
    public List<Map> industryStats() {
        return preexamMapper.industryStats();
    }

    @Override
    public List<Map> incomeStats() {
        return preexamMapper.incomeStats();
    }

    @Override
    public int add(Preexamer preexamer) {
        return preexamMapper.add(preexamer);
    }
//
//    @Override
//    public int update(Preexamer preexamer) {
//        return preexamMapper.update(preexamer);
//    }
//
    @Override
    public int deleteOne(Long id) {
        return preexamMapper.deleteOne(id);
    }

    @Override
    public int deleteMany(String ids) {
        return preexamMapper.deleteMany(ids);
    }

    @Override
    public Preexamer findPreexaminfo(String identifyID) {
        return preexamMapper.findPreexaminfo(identifyID);

    }

}
