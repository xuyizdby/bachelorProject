package com.project1.dao.mapper;

import com.project1.model.Preexamer;

import java.util.List;
import java.util.Map;


public interface PreexamMapper {
    /**
     * 根据条件查询用户列表
     */
//    List<Preexamer> getUserList(Preexamer preexamer);
    public int add(Preexamer preexamer);
//    public int update(Preexamer preexamer);
    public int deleteOne(Long id);
    public int deleteMany(String ids);
    public List<Preexamer> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public Preexamer findDetail(Map<String, Object> queryMap);
    public Preexamer findPreexaminfo(String identifyID);
    public List<Map> sexStats();
    public List<Map> industryStats();
    public List<Map> incomeStats();
}
