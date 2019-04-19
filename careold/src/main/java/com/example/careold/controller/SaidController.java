package com.example.careold.controller;

import com.example.careold.common.DateConst;
import com.example.careold.common.ReturnCodeUtil;
import com.example.careold.dao.SaidDao;
import com.example.careold.domain.Said;
import com.example.careold.domain.Said2;
import com.kenai.jaffl.annotations.In;
import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/said")
public class SaidController {

    @Autowired
    SaidDao saidDao;

    @ResponseBody
    @PostMapping("/select")
    public ListOrderedMap select(@RequestBody ListOrderedMap param){
        ListOrderedMap result=new ListOrderedMap();
        String textId=param.get("textId").toString();
        String familyPhone=param.get("familyPhone").toString();
        List<Said2> said2s=saidDao.getSaid(Integer.parseInt(textId));
        for(int i=0;i<said2s.size();i++){
            if(saidDao.isSaid(said2s.get(i).getSaidId(),familyPhone).size()>0){
                said2s.get(i).setGood("1");
            }
            else{
                said2s.get(i).setGood("0");
            }
        }
        result.put("said2s",said2s);
        return result;

    }


    @ResponseBody
    @PostMapping("/delete")
    public ListOrderedMap delete(@RequestBody ListOrderedMap param){
        ListOrderedMap result=new ListOrderedMap();
        String textId=param.get("saidId").toString();
        int rows=saidDao.deleteSaid(Integer.parseInt(textId));
        if(rows==1){
            result.put(ReturnCodeUtil.returnCode,ReturnCodeUtil.successCode);
            return result;
        }
        result.put(ReturnCodeUtil.returnCode,ReturnCodeUtil.falseCode);
        return result;

    }

    @ResponseBody
    @PostMapping("/insert")
    public ListOrderedMap insert(@RequestBody ListOrderedMap param){
        ListOrderedMap result=new ListOrderedMap();
//        String said=param.get("said").toString();
//        String textId=param.get("textId").toString();
//        String personName=param.get("personName").toString();
//        Said said1=new Said();
//        said1.setSaid(said);
//        said1.setTextId(Integer.parseInt(textId));
//        said1.setBack("");
//        said1.setPersonName(personName);
//        String saidTime= DateConst.dateToString(new Date(),DateConst.DATE_MODEL_1);
//        said1.setSaidTime(saidTime);
//        said1.setBackTime("");
//        int rows=saidDao.addSaid(said1);
//        if(rows==1){
//            result.put(ReturnCodeUtil.returnCode,ReturnCodeUtil.successCode);
//            return result;
//        }
//        result.put(ReturnCodeUtil.returnCode,ReturnCodeUtil.falseCode);
        return result;

    }


    @ResponseBody
    @PostMapping("/update")
    public ListOrderedMap update(@RequestBody ListOrderedMap param){
        ListOrderedMap result=new ListOrderedMap();
//        String back=param.get("back").toString();
//        String saidId=param.get("saidId").toString();
//        String niceCount=param.get("niceCount").toString();
//        Said said1=new Said();
//        said1.setSaidId(Integer.parseInt(saidId));
//        said1.setBack(back);
//        said1.setNiceCount(Integer.parseInt(niceCount));
//        String backTime= DateConst.dateToString(new Date(),DateConst.DATE_MODEL_1);
//        said1.setBackTime(backTime);
//        int rows=saidDao.updateSaid(said1);
//        if(rows==1){
//            result.put(ReturnCodeUtil.returnCode,ReturnCodeUtil.successCode);
//            return result;
//        }
//        result.put(ReturnCodeUtil.returnCode,ReturnCodeUtil.falseCode);
        return result;

    }
}
