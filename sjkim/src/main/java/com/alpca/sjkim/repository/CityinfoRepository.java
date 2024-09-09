package com.alpca.sjkim.repository;

import com.alpca.sjkim.entity.Cityinfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityinfoRepository extends JpaRepository<Cityinfo, String> {
    List<Cityinfo> findByCityName(String cityName);
}
