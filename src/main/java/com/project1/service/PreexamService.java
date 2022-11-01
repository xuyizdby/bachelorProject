package com.project1.service;
import com.project1.model.Preexamer;
import com.project1.model.User;

import java.util.List;
import java.util.Map;

public interface PreexamService {
    /**
     * 提前面试
     * @return
     */
//    public User loginUser(String loginName, String password);
    public int add(Preexamer preexamer);
//    public int update(Preexamer preexamer);
    public int deleteOne(Long id);
    public int deleteMany(String ids);
    public Preexamer findPreexaminfo(String identifyID);
    public List<Preexamer> findList(Map<String, Object> queryMap);
    public Preexamer findDetail(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public List<Map> sexStats();
    public List<Map> industryStats();
    public List<Map> incomeStats();
}