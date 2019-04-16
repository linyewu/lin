package com.example.careold.controller;

import com.example.careold.common.ReturnCodeUtil;
import com.example.careold.dao.FamilyDao;
import com.example.careold.dao.UserDao;
import com.example.careold.domain.Family;
import com.example.careold.domain.FamilyDto;
import com.example.careold.domain.Users;
import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/family")
public class FamilyController {

    @Autowired
    FamilyDao familyDao;

    @ResponseBody
    @PostMapping("/select")
    public ListOrderedMap select(@RequestBody ListOrderedMap param){
        ListOrderedMap result=new ListOrderedMap();
        String familyName=param.get("familyName").toString();
        List<FamilyDto> familyDtos= familyDao.findFamily(familyName);
        result.put("familyDtos",familyDtos);
        return result;
    }

    @ResponseBody
    @PostMapping("/add")
    public ListOrderedMap add(@RequestBody ListOrderedMap params){
        ListOrderedMap result=new ListOrderedMap();
        String familyName=params.get("familyName").toString();
        String familyRelation=params.get("familyRelation").toString();
        String familySex=params.get("familySex").toString();
        String familyPhone=params.get("familyPhone").toString();
        String familyAddress=params.get("familyAddress").toString();
        Family family=new Family();
        family.setFamilyAddress(familyAddress);
        family.setFamilyPhone(familyPhone);
        family.setFamilySex(familySex);
        family.setFamilyName(familyName);
        family.setFamilyRelation(familyRelation);
        int row=familyDao.save(family);
        if(row==1){
            result.put(ReturnCodeUtil.returnCode,ReturnCodeUtil.successCode);
            return result;
        }
        result.put(ReturnCodeUtil.returnCode,ReturnCodeUtil.falseCode);
        return result;
    }
}
