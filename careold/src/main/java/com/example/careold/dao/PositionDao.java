package com.example.careold.dao;

import com.example.careold.domain.PositionDto;

import java.util.List;

public interface PositionDao {
    List<PositionDto> getPosition(String name);

    public List<PositionDto> getPositionFamily(int oldId);
}
