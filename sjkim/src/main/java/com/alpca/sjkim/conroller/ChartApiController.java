package com.alpca.sjkim.conroller;

import com.alpca.sjkim.dto.ChartAvgDto;
import com.alpca.sjkim.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
