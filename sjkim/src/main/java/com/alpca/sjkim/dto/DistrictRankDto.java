package com.alpca.sjkim.dto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DistrictRankDto {
    private String cityCode;
    private String cityName;
    private String districtName;
    private Double totalVisit;
}
