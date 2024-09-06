package com.alpca.sjkim.repository;

import com.alpca.sjkim.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {
    List<History> findByCityinfo_CityName(String cityName);
}
