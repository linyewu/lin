package com.example.careold.controller;

import com.example.careold.dao.RatepreDao;
import com.example.careold.domain.RatepreDto;
import org.apache.commons.collections.map.ListOrderedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ratepre")
public class RatepreController {

    @Autowired
    RatepreDao ratepreDao;

    private static final Logger LOG = LoggerFactory.getLogger(RatepreController.class);

    @ResponseBody
    @PostMapping("/selectRatepre")
    public ListOrderedMap selectRatepre(@RequestBody ListOrderedMap param){
        ListOrderedMap result=new ListOrderedMap();
        String name=param.get("name").toString();
        String timeFirst=param.get("timeFirst").toString();
        String timeLast=param.get("timeLast").toString();
        LOG.info(name+"----"+timeFirst+"----"+timeLast);
        List<RatepreDto> ratepreDtoList=ratepreDao.getRatepre(name,timeFirst,timeLast);
        result.put("ratepreDtoList",ratepreDtoList);
        return result;
    }

    @ResponseBody
    @PostMapping("/selectRatepreDetail")
    public ListOrderedMap selectRatepreDetail(@RequestBody ListOrderedMap param){
        ListOrderedMap result=new ListOrderedMap();
        String name=param.get("name").toString();
        List<RatepreDto> ratepreDtoListDetial=ratepreDao.getRatepreDetial(name);
        ArrayList hrs=new ArrayList();
        ArrayList bps=new ArrayList();
        ArrayList createTimes=new ArrayList();
        for(int i=0;i<ratepreDtoListDetial.size();i++){
            hrs.add(ratepreDtoListDetial.get(i).getHr());
            bps.add(ratepreDtoListDetial.get(i).getBp());
            createTimes.add(ratepreDtoListDetial.get(i).getCreateTime().substring(14,16));
        }
        result.put("time",ratepreDtoListDetial.get(0).getCreateTime().substring(0,13));
        result.put("ratepreDtoListDetial",ratepreDtoListDetial);
        result.put("hrs",hrs);
        result.put("bps",bps);
        result.put("createTimes",createTimes);
        return result;
    }

    @ResponseBody
    @PostMapping("/selectRatepreCount")
    public ListOrderedMap selectRatepreCount(@RequestBody ListOrderedMap param){
        ListOrderedMap result=new ListOrderedMap();
        List<String> rates=ratepreDao.getRateCount();
        List<String> pres=ratepreDao.getPreCount();
        result.put("rates",rates);
        result.put("pres",pres);
        return result;
    }
}
