package com.example.careold.dao;

import com.example.careold.domain.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TextJdbcDao implements TextDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Text> selectTextAll(String title) {
        String sql=null;
        List<Text> texts=null;
        if(title!=null && !"".equals(title)){
            sql="SELECT * FROM TEXT WHERE title like ?";
            texts=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Text.class),"%"+title+"%");
        }else {
            sql="SELECT * FROM TEXT ";
            texts=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Text.class));
        }
        return texts;
    }

    @Override
    public Text selectTextDetail(int textId) {
        String sql="SELECT * FROM TEXT WHERE text_id=?";
        Text text=jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Text.class),textId);
        return text;
    }

    @Override
    public int addText(Text text) {
        String sql="INSERT INTO TEXT(title,text_main,TIME,pic_id) VALUE(?,?,?,?)";
        int rows=jdbcTemplate.update(sql,text.getTitle(),text.getTextMain(),text.getTime(),text.getPicId());
        return rows;
    }

    @Override
    public int deleteText(int id) {
        String sql="DELETE FROM TEXT WHERE text_id=?";
        int rows=jdbcTemplate.update(sql,id);
        return rows;
    }

    @Override
    public int update(Text text) {
        String sql="UPDATE TEXT SET title=?,text_main=?,TIME=? WHERE text_id=?";
        int rows=jdbcTemplate.update(sql,text.getTitle(),text.getTextMain(),text.getTime(),text.getTextId());
        return rows;
    }

    @Override
    public int getMaxPicId() {
        String sql="SELECT MAX(text_id) picId FROM TEXT";
        int maxId=jdbcTemplate.queryForObject(sql,int.class);
        return maxId;
    }
}
