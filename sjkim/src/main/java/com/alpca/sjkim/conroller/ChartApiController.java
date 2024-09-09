package com.alpca.sjkim.conroller;

import com.alpca.sjkim.dto.ChartAvgDto;
import com.alpca.sjkim.dto.CityRankDto;
import com.alpca.sjkim.dto.DistrictRankDto;
import com.alpca.sjkim.dto.visitHistoryDto;
import com.alpca.sjkim.entity.Cityinfo;
import com.alpca.sjkim.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chart")
public class ChartApiController {
    @Autowired
    private ChartService chartService;

    @CrossOrigin()
    @GetMapping("/getAvgVisit")
    public List<ChartAvgDto> getAverageVisit() {
        return chartService.getAvgVisit();
    }

    @CrossOrigin()
    @GetMapping("/getDistrictRank")
    public List<DistrictRankDto> getDistrictRank(@RequestParam("year") int year) {
        return chartService.getRankDistrict(year);
    }

    @CrossOrigin()
    @GetMapping("/getCityRank")
    public List<CityRankDto> getCityRank(@RequestParam("year") int year) {
        return chartService.getRankCity(year);
    }

    @CrossOrigin()
    @GetMapping("/getVisitHistory")
    public List<visitHistoryDto> getVisitHistory(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam("cityName") String cityName, @RequestParam("districtName") String districtName) {
        return chartService.getVisitHistory(cityName, districtName, startDate, endDate);
    }
}
