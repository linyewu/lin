package com.example.careold.dao;

import com.example.careold.domain.Said;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SaidJdbcDao implements SaidDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Said> getSaid(int textid) {
        String sql="";
        List<Said> saids=null;
        if(textid==0){
            sql="SELECT * FROM said ";
            saids=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Said.class));
        }else{
            sql="SELECT * FROM said WHERE text_id=?";
            saids=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Said.class),textid);
        }
        return saids;
    }

    @Override
    public int addSaid(Said said) {
        String sql="INSERT INTO said(said,text_id,said_time,personname,said_phone,tou_pic) VALUES(?,?,?,?,?,?)";
        int rows=jdbcTemplate.update(sql,said.getSaid(),said.getTextId(),said.getSaidTime(),said.getPersonName(),said.getSaidPhone(),said.getTouPic());
        return rows;
    }

    @Override
    public int deleteSaid(int saidId) {
        String sql="DELETE FROM said WHERE said_id=?";
        int rows=jdbcTemplate.update(sql,saidId);
        return rows;
    }

    @Override
    public int updateSaid(Said said) {
        String sql="UPDATE said SET back=?,back_time=?,nice_count=? WHERE said_id=?";
        int rows=jdbcTemplate.update(sql,said.getBack(),said.getBackTime(),said.getNiceCount(),said.getSaidId());
        return rows;
    }
}
