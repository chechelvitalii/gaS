package com.gas.station;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GasStationApplication {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(GasStationApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GasStationApplication.class, args);
	}
}
