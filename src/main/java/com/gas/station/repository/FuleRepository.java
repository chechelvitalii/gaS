package com.gas.station.repository;

import com.gas.station.model.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuleRepository extends JpaRepository<Fuel,Integer>{
}
