package com.alpca.sjkim.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChartAvgDto {
    private String cityCode;
    private String cityName;
    private String districtName;
    private Double avgTotNum;

}
