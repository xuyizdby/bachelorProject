package com.project1.model;

import org.springframework.stereotype.Component;
import sun.util.resources.ga.LocaleNames_ga;

import java.util.Date;
@Component
public class User {
    //字段名要和数据库字段一致 不区分大小写
    private Long id;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String password;
    private String username;
    private String role;

//    private int status;
//    public Long getUserId() {
//    return id;
//}
//    public void setUserId(Long id) {
//        this.id= id;
//    }
//    public String getPassword() {
//        return password;
//    }
//    public void setPassword(String password) {
//        this.password = password;
//    }
//    public String getUserName() {
//        return username;
//    }
//    public void setUserName(String username) {
//        this.username = username;
//    }
//    public String getEmail() {
//        return email;
//    }
//    public void setEmail(String email) {
//        this.email = email;
//    }
//    public String getRole() {
//        return role;
//    }
//    public void setRole(String role) {
//        this.role = role;
//    }
//    public int getStatus() {
//        return status;
//    }
//    public void setStatus(int status) {
//        this.status = status;
//    }
//    public Date getRegTime() {
//        return regTime;
//    }
//    public void setRegTime(Date regTime) {
//        this.regTime = regTime;
//    }
//
//    public String getRegIp() {
//        return regIp;
//    }
//    public void setRegIp(String regIp) {
//        this.regIp = regIp;
//    }
//    private Date regTime;
//    private String regIp;
}