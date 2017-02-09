package com.gas.station.repository;

import com.gas.station.model.GasStation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GasStationRepository extends JpaRepository<GasStation,Integer> {
}
