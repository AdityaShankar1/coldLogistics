package com.coldchain.domain.model;

import com.coldchain.domain.exception.InvalidColdChainException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * Domain entity representing a batch of vaccines.
 * This class holds the business logic for viability and storage validation.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineBatch {
    private String batchNumber;
    private String vaccineType;
    private LocalDate expiryDate;
    private StorageUnit storageUnit;

    /**
     * Assigns the batch to a storage unit, validating cold-chain requirements.
     * GRASP Information Expert: The batch knows its own constraints and
     * validates itself against the storage unit's current state.
     *
     * @param unit The storage unit to assign the batch to.
     * @throws InvalidColdChainException if temperature requirements are not met.
     */
    public void assignToStorage(StorageUnit unit) {
        if (unit.getCurrentTemp() < unit.getMinTemp() || unit.getCurrentTemp() > unit.getMaxTemp()) {
            throw new InvalidColdChainException(
                "Temperature breach detected! Unit " + unit.getUnitId() + 
                " is at " + unit.getCurrentTemp() + "°C."
            );
        }
        this.storageUnit = unit;
    }
}
