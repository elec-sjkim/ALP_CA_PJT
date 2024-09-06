package com.alpca.sjkim;

import com.alpca.sjkim.dto.ChartAvgDto;
import com.alpca.sjkim.dto.CityList;
import com.alpca.sjkim.entity.Cityinfo;
import com.alpca.sjkim.entity.History;
import com.alpca.sjkim.repository.HistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SpringBootTest
class SjkimApplicationTests {

	@Autowired
	private HistoryRepository historyRepository;


	@Test
	void getHistory() {
		List<History> historyList = historyRepository.findAll();
		System.out.println(historyList);
	}

	@Test
	void getCityList(){
		List<History> historyList = historyRepository.findAll();
		List<String> cityList = historyList.stream().map(History::getCityinfo)
				.map(Cityinfo::getCityName)
				.distinct()
				.collect(Collectors.toList());

		System.out.println(cityList);
	}

	@Test
	void getDistrictName() {
		List<History> historyList = historyRepository.findByCityinfo_CityName("서울특별시");
		List<String> districtList = historyList.stream().map(History::getCityinfo)
				.map(Cityinfo::getDistrictName)
				.distinct()
				.collect(Collectors.toList());
		System.out.println(districtList);
	}

	@Test
	void getAverageVisit(){

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
		System.out.println(chartAvgDtos);
	}

}
