package com.alpca.sjkim.conroller;

import com.alpca.sjkim.service.DropdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dropdown")
public class DropdownApiController {

    @Autowired
    private DropdownService dropdownService;

    @GetMapping("/cityUnique")
    public List<String> cityUnique() {
        return dropdownService.getCityNameList();
    }

    @GetMapping("/districtUnique")
    public List<String> districtUnique(@RequestParam(defaultValue = "") String cityName) {
        return dropdownService.getDistrictNameList(cityName);
    }
}
