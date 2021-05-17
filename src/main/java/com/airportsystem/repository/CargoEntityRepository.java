package com.airportsystem.repository;

import com.airportsystem.models.CargoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoEntityRepository extends JpaRepository<CargoEntity,Long> {
}
