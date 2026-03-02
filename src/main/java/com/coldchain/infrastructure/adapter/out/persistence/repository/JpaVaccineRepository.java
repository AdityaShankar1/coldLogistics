package com.coldchain.infrastructure.adapter.out.persistence.repository;

import com.coldchain.infrastructure.adapter.out.persistence.entity.VaccineBatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaVaccineRepository extends JpaRepository<VaccineBatchEntity, String> {
}
