package com.alpca.sjkim;

import com.alpca.sjkim.dto.CityList;
import com.alpca.sjkim.entity.Cityinfo;
import com.alpca.sjkim.entity.History;
import com.alpca.sjkim.repository.HistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
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


}
