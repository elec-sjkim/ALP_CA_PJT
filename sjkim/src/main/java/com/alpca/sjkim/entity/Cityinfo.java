package com.alpca.sjkim.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
public class Cityinfo {
    @Id
    private String cityCode;
    private String cityName;
    private String districtName;
}
