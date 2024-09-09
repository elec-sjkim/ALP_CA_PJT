package com.alpca.sjkim;

import com.alpca.sjkim.dto.ChartAvgDto;
import com.alpca.sjkim.dto.CityRankDto;
import com.alpca.sjkim.dto.DistrictRankDto;
import com.alpca.sjkim.dto.visitHistoryDto;
import com.alpca.sjkim.entity.Cityinfo;
import com.alpca.sjkim.entity.History;
import com.alpca.sjkim.repository.CityinfoRepository;
import com.alpca.sjkim.repository.HistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class SjkimApplicationTests {

	@Autowired
	private HistoryRepository historyRepository;
	@Autowired
	private CityinfoRepository cityinfoRepository;


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

	@Test
	void getDistrictRank() {
		List<History> results = historyRepository.findByDateYmdBetween(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31));

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

		System.out.println(rankByDistrict);
	}

	@Test
	void getCityRank() {
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

					return new CityRankDto(
							cityName,
							totalVisit
					);
				})
				.collect(Collectors.toList()); // 리스트로 변환

		System.out.println(rankByCity);
	}

	@Test
	void getCityCode() {
		Cityinfo cityInfo = cityinfoRepository.findByCityNameAndDistrictName("서울특별시", "용산구");
		System.out.println(cityInfo.getCityCode());
	}

	@Test
	void getHistoryByDate() {
		Cityinfo cityinfo = cityinfoRepository.findByCityNameAndDistrictName("서울특별시", "용산구");
		String cityCode = cityinfo.getCityCode();

		List<History> results = historyRepository.findByDateYmdBetweenAndCityinfo_CityCode(LocalDate.parse("2024-01-01"), LocalDate.parse("2024-12-31"), cityCode);

		List<visitHistoryDto> historyDatas = results.stream()
						.collect(Collectors.groupingBy(
								History::getDateYmd,
								Collectors.summingDouble(History::getTotNum)
						))
								.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
								.map(entry -> {
									return new visitHistoryDto(
											entry.getKey(),
											entry.getValue(),
											cityinfo
									);
								}).collect(Collectors.toList());

		System.out.println(historyDatas);
	}




}
