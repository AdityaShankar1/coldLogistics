package com.coldchain.infrastructure.adapter.out.persistence;

import com.coldchain.application.port.out.IVaccineRepository;
import com.coldchain.domain.model.VaccineBatch;
import com.coldchain.infrastructure.adapter.out.persistence.entity.StorageUnitEntity;
import com.coldchain.infrastructure.adapter.out.persistence.entity.VaccineBatchEntity;
import com.coldchain.infrastructure.adapter.out.persistence.repository.JpaVaccineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class VaccinePersistenceAdapter implements IVaccineRepository {

    private final JpaVaccineRepository jpaVaccineRepository;

    @Override
    public VaccineBatch save(VaccineBatch batch) {
        VaccineBatchEntity entity = mapToEntity(batch);
        VaccineBatchEntity savedEntity = jpaVaccineRepository.save(entity);
        return mapToDomain(savedEntity);
    }

    @Override
    public Optional<VaccineBatch> findByBatchNumber(String batchNumber) {
        return jpaVaccineRepository.findById(batchNumber).map(this::mapToDomain);
    }

    @Override
    public List<VaccineBatch> findAll() {
        return StreamSupport.stream(jpaVaccineRepository.findAll().spliterator(), false)
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    private VaccineBatchEntity mapToEntity(VaccineBatch domain) {
        VaccineBatchEntity entity = new VaccineBatchEntity();
        entity.setBatchNumber(domain.getBatchNumber());
        entity.setVaccineType(domain.getVaccineType());
        entity.setExpiryDate(domain.getExpiryDate());

        if (domain.getStorageUnit() != null) {
            StorageUnitEntity storageEntity = new StorageUnitEntity();
            storageEntity.setUnitId(domain.getStorageUnit().getUnitId());
            entity.setStorageUnit(storageEntity);
        }

        return entity;
    }

    private VaccineBatch mapToDomain(VaccineBatchEntity entity) {
        // Mapping storage unit back needs care based on domain model
        return new VaccineBatch(
                entity.getBatchNumber(),
                entity.getVaccineType(),
                entity.getExpiryDate(),
                null
        );
    }
}