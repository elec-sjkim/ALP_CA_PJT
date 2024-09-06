package com.alpca.sjkim.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CityList {
    private String cityCode;
    private String cityName;
    private String districtName;

    @Builder
    public CityList(String cityCode, String cityName, String districtName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.districtName = districtName;
    }
}
