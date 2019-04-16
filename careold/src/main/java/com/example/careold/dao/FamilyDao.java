package com.example.careold.dao;

import com.example.careold.domain.Family;
import com.example.careold.domain.FamilyDto;

import java.util.List;

public interface FamilyDao extends CrudRepository<Family,String>{

    int update2(Family family);

    public List<FamilyDto> findFamily(String s);

    Family findFamilyByPhone(String phone);
}
