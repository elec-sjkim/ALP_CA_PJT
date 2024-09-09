package com.alpca.sjkim.dto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityRankDto {
    private String cityName;
    private Double totalVisit;
}
