package com.alpca.sjkim.service;

import com.alpca.sjkim.dto.ChartAvgDto;
import com.alpca.sjkim.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChartService {
    @Autowired
    private HistoryRepository historyRepository;

    public List<ChartAvgDto> getAvgVisit() {
        List<Object[]> results = historyRepository.findAverageByCityAndDateRangeWithDetails(LocalDate.parse("2024-01-01"), LocalDate.parse("2024-03-01"));
        List<ChartAvgDto> chartAvgDtos = new ArrayList<>();

        for (Object[] result : results) {
            String cityCode = (String) result[0];
            String cityName = (String) result[1];
            String districtName = (String) result[2];
            Double avgTotNum = (Double) result[3];

            ChartAvgDto dto = new ChartAvgDto(cityCode, cityName, districtName, avgTotNum);
            chartAvgDtos.add(dto);
        }

        return chartAvgDtos;
    }
}
