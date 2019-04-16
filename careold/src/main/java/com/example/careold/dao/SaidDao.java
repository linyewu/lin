package com.example.careold.dao;

import com.example.careold.domain.Said;

import java.util.List;

public interface SaidDao {
    List<Said> getSaid(int textid);

    int addSaid(Said said);

    int deleteSaid(int saidId);

    int updateSaid(Said said);
}
