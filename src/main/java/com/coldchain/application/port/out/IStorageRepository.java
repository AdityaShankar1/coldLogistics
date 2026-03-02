package com.coldchain.application.port.out;

import com.coldchain.domain.model.StorageUnit;
import java.util.Optional;

public interface IStorageRepository {
    Optional<StorageUnit> findById(String unitId);
}
