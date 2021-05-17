package com.airportsystem;

import com.airportsystem.models.CargoEntity;
import com.airportsystem.models.FlightEntity;
import com.airportsystem.service.FlightEntityService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class AirportSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirportSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(FlightEntityService flightEntityService){
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            ObjectMapper mapper2 = new ObjectMapper();
            TypeReference<List<FlightEntity>> typeReference = new TypeReference<>(){};
            TypeReference<List<CargoEntity>> typeReference2 = new TypeReference<>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/flightEntity.json");
            InputStream inputStream2 = TypeReference.class.getResourceAsStream("/json/cargoEntity.json");
            try {
                List<FlightEntity> flightEntityList = mapper.readValue(inputStream,typeReference);
                List<CargoEntity> cargoEntityList = mapper2.readValue(inputStream2,typeReference2);

                for(int i = 0; i < flightEntityList.size(); i ++){
                    flightEntityList.get(i).setCargoEntity(cargoEntityList.get(i));
                }
                flightEntityService.save(flightEntityList);
                System.out.println("Users Saved!");
            } catch (IOException e){
                System.out.println("Unable to save users: " + e.getMessage());
            }
        };
    }

}
