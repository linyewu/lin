package com.example.careold.dao;

import com.example.careold.domain.Family;
import com.example.careold.domain.FamilyDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FamilyJdbcDao extends JdbcCom implements FamilyDao {


    @Override
    public List<Family> findById(String s) {
        String sql="";
        List<Family> families=null;
        if(s==null || "".equals(s)){
            sql="select * from family";
            families=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Family.class));
        }else{
            sql="select * from family where family_name like ?";
            families=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Family.class),"%"+s+"%");
        }
        return families;
    }

//    @Override
//    public List<Family> findById(String s) {
//        String sql="";
//        List<Family> families=null;
//        if(s==null || "".equals(s)){
//            sql="select old_id,name,oldperson.family_id,family_name,family_relation,family_phone,family_sex,family_address from oldperson INNER JOIN family on family.family_id=oldperson.family_id";
//            families=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Family.class));
//        }else{
//            sql="select old_id,name,oldperson.family_id,family_name,family_relation,family_phone,family_sex,family_address from oldperson INNER JOIN family on family.family_id=oldperson.family_id where family_name like ?";
//            families=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Family.class),"%"+s+"%");
//        }
//        return families;
//    }

    @Override
    public List<FamilyDto> findFamily(String s) {
        String sql="";
        List<FamilyDto> familyDtos=null;
        if(s==null || "".equals(s)){
            sql="select old_id,name,oldperson.family_id,family_name,family_relation,family_phone,family_sex,family_address from oldperson INNER JOIN family on family.family_id=oldperson.family_id";
            familyDtos=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(FamilyDto.class));
        }else{
            sql="select old_id,name,oldperson.family_id,family_name,family_relation,family_phone,family_sex,family_address from oldperson INNER JOIN family on family.family_id=oldperson.family_id where family_name like ?";
            familyDtos=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(FamilyDto.class),"%"+s+"%");
        }
        return familyDtos;
    }

    @Override
    public Family findFamilyByPhone(String phone) {
        String sql="select * from family where family_phone = ?";
        Family family=jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Family.class),phone);
        return family;
    }

    @Override
    public int save(Family family) {
        String sql="insert into family(family_name,family_relation,family_phone,family_sex,family_address) VALUES(?,?,?,?,?) ";
        int rows=jdbcTemplate.update(sql,family.getFamilyName(),family.getFamilyRelation(),family.getFamilyPhone(),family.getFamilySex(),family.getFamilyAddress());
        return rows;
    }

    @Override
    public int update(Family family) {
        String sql="UPDATE family SET family_name=?,family_relation=?,family_phone=?,family_sex=?,family_address=? WHERE family_id=?";
        int rows=jdbcTemplate.update(sql,family.getFamilyName(),family.getFamilyRelation(),family.getFamilyPhone(),family.getFamilySex(),family.getFamilyAddress(),family.getFamilyId());
        return rows;
    }

    @Override
    public int update2(Family family) {
        String sql="UPDATE family SET family_password=?,family_tou_pic=? WHERE family_id=?";
        int rows=jdbcTemplate.update(sql,family.getFamilyPassword(),family.getFamilyTouPic(),family.getFamilyId());
        return rows;
    }

    @Override
    public int delete(String s) {
        String sql="DELETE FROM family WHERE family_id=?";
        int rows=jdbcTemplate.update(sql,s);
        return rows;
    }
}