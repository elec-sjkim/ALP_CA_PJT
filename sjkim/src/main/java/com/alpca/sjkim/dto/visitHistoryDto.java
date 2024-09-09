package com.alpca.sjkim.dto;

import com.alpca.sjkim.entity.Cityinfo;
import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class visitHistoryDto {
    private LocalDate dateYmd;
    private double totVisit;
    private Cityinfo cityinfo;

}
