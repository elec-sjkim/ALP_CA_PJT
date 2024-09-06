package com.alpca.sjkim.conroller;

import com.alpca.sjkim.service.DropdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
