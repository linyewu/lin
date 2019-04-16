package com.example.careold.dao;

import com.example.careold.domain.StepDto;

import java.util.List;

public interface StepDao {
    List<StepDto> getStepSleep(String name, String timeFirst, String timeLast);

    List<StepDto> getStepSleepDetail(String name);
}
