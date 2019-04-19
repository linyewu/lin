package com.example.careold.controller;

import com.example.careold.common.ReturnCodeUtil;
import com.example.careold.dao.FamilyDao;
import com.example.careold.dao.UserDao;
import com.example.careold.dao.WatchInfoDao;
import com.example.careold.domain.Family;
import com.example.careold.domain.FamilyDto;
import com.example.careold.domain.Users;
import com.example.careold.domain.WatchDto;
import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/userLogin")
public class LoginController {

    @Autowired
    FamilyDao familyDao;

    @Autowired
    UserDao userDao;

    @ResponseBody
    @PostMapping("/login")
    public ListOrderedMap select(@RequestBody ListOrderedMap param){
        ListOrderedMap result=new ListOrderedMap();
        String phone=param.get("phone").toString();
        String password=param.get("password").toString();
        List<Users> users = userDao.findById(null);
        for(int i=0;i<users.size();i++){
            if(users.get(i).getUserPhone().equals(phone)){
                if(users.get(i).getPassword().equals(password)){
                    Users users1=userDao.getUsersByPhone(phone);
                    result.put("users1",users1);
                    result.put(ReturnCodeUtil.returnCode,ReturnCodeUtil.successCode);
                    return result;
                }else {
                    result.put(ReturnCodeUtil.returnCode,"密码不正确");
                    return result;
                }
            }
        }
        List<Family> families=familyDao.findById(null);
        for(int i=0;i<families.size();i++){
            if(families.get(i).getFamilyPhone().equals(phone)){
                if(families.get(i).getFamilyPassword().equals(password)){
                    FamilyDto family=familyDao.findFamilyByPhone(phone);
                    result.put("family",family);
                    result.put(ReturnCodeUtil.returnCode,ReturnCodeUtil.successCode);
                    return result;
                }else {
                    result.put(ReturnCodeUtil.returnCode,"密码不正确");
                    return result;
                }
            }
        }
        result.put(ReturnCodeUtil.returnCode,ReturnCodeUtil.falseCode);
        return result;
    }
}
