package com.coldchain.infrastructure.adapter.out.persistence;

import com.coldchain.application.port.out.IStorageRepository;
import com.coldchain.domain.model.StorageUnit;
import com.coldchain.infrastructure.adapter.out.persistence.entity.StorageUnitEntity;
import com.coldchain.infrastructure.adapter.out.persistence.repository.JpaStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StoragePersistenceAdapter implements IStorageRepository {

    private final JpaStorageRepository storageRepository;

    @Override
    public Optional<StorageUnit> findById(String unitId) {
        return storageRepository.findById(unitId).map(this::mapToDomain);
    }

    private StorageUnit mapToDomain(StorageUnitEntity entity) {
        return new StorageUnit(
                entity.getUnitId(),
                entity.getMinTemp(),
                entity.getMaxTemp(),
                entity.getCurrentTemp());
    }
}
