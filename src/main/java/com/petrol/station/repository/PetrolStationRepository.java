package com.petrol.station.repository;

import com.petrol.station.model.PetrolStation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetrolStationRepository extends JpaRepository<PetrolStation,Integer> {
}
