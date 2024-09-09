package com.alpca.sjkim.repository;

import com.alpca.sjkim.dto.ChartAvgDto;
import com.alpca.sjkim.entity.Cityinfo;
import com.alpca.sjkim.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {
    List<History> findByCityinfo_CityName(String cityName);

    List<History> findByDateYmdBetween(LocalDate startDate, LocalDate endDate);

    List<History> findByDateYmdBetweenAndCityinfo_CityCode(LocalDate startDate, LocalDate endDate, String cityCode);

    @Query("SELECT c.cityCode AS cityCode, c.cityName AS cityName, c.districtName AS districtName, " +
            "ROUND(AVG(h.totNum), 2) AS avgTotNum " +
            "FROM History h " +
            "LEFT JOIN h.cityinfo c " +
            "WHERE h.dateYmd BETWEEN :startDate AND :endDate " +
            "GROUP BY c.cityCode, c.cityName, c.districtName " +
            "ORDER BY AVG(h.totNum) DESC")
    List<Object[]> findAverageByCityAndDateRangeWithDetails(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
