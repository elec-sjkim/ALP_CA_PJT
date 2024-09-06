package com.alpca.sjkim.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@ToString
@Getter
@Setter
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;
//    private String cityCode;
    private String tourDiv;
    private LocalDate dateYmd;
    private double totNum;

    @ManyToOne
    @JoinColumn(name = "city_code")
    private Cityinfo cityinfo;
}
