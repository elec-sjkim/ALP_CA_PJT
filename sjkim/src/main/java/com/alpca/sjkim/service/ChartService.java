package com.alpca.sjkim.service;

import com.alpca.sjkim.dto.ChartAvgDto;
import com.alpca.sjkim.dto.CityRankDto;
import com.alpca.sjkim.dto.DistrictRankDto;
import com.alpca.sjkim.entity.Cityinfo;
import com.alpca.sjkim.entity.History;
import com.alpca.sjkim.repository.CityinfoRepository;
import com.alpca.sjkim.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartService {
    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private CityinfoRepository cityinfoRepository;


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

    public List<DistrictRankDto> getRankDistrict(int year) {
        List<History> results = historyRepository.findByDateYmdBetween(LocalDate.of(year, 1, 1), LocalDate.of(year, 12, 31));

        List<DistrictRankDto> rankByDistrict = results.stream()
                .collect(Collectors.groupingBy(
                        History::getCityinfo, // key를 Cityinfo로 설정
                        Collectors.summingDouble(History::getTotNum)
                ))
                // 합계 기준으로 내림차순 정렬
                .entrySet().stream()
                .sorted((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()))
                // 상위 10개만 추출
                .limit(10)
                // DTO 리스트로 변환
                .map(entry -> {
                    Cityinfo cityInfo = entry.getKey();
                    return new DistrictRankDto(
                            cityInfo.getCityCode(),
                            cityInfo.getCityName(),
                            cityInfo.getDistrictName(),
                            entry.getValue()
                    );
                }).collect(Collectors.toList()); // 리스트로 변환
        return rankByDistrict;
    }

    public List<CityRankDto> getRankCity(int year) {
        List<History> results = historyRepository.findByDateYmdBetween(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31));

        List<CityRankDto> rankByCity = results.stream()
                .collect(Collectors.groupingBy(
                        (history -> history.getCityinfo().getCityName()), // key를 Cityinfo로 설정
                        Collectors.summingDouble(History::getTotNum)
                ))
                // 합계 기준으로 내림차순 정렬
                .entrySet().stream()
                .sorted((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()))
                // DTO 리스트로 변환
                .map(entry -> {
                    String cityName = entry.getKey();
                    Double totalVisit = entry.getValue();

                    // cityName으로 Cityinfo를 조회하여 cityCode 가져옴
                    List<Cityinfo> cityInfos = cityinfoRepository.findByCityName(cityName);
                    Cityinfo cityInfo = cityInfos.get(0);

                    return new CityRankDto(
                            cityInfo.getCityCode(), // cityCode 가져옴
                            cityName,
                            totalVisit
                    );
                })
                .collect(Collectors.toList()); // 리스트로 변환
        return rankByCity;
    }





}
