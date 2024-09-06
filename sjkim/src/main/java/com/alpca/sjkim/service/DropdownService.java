package com.alpca.sjkim.service;

import com.alpca.sjkim.entity.Cityinfo;
import com.alpca.sjkim.entity.History;
import com.alpca.sjkim.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DropdownService {
    @Autowired
    private HistoryRepository historyRepository;

    public List<String> getCityNameList() {
        List<History> historyList = historyRepository.findAll();
        List<String> cityList = historyList.stream().map(History::getCityinfo)
                .map(Cityinfo::getCityName)
                .distinct()
                .collect(Collectors.toList());
        return cityList;
    }


}
