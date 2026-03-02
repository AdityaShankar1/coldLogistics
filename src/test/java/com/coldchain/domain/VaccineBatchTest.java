package com.coldchain.domain;

import com.coldchain.domain.model.StorageUnit;
import com.coldchain.domain.model.VaccineBatch;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

// VaccineBatchTest.java
class VaccineBatchTest {
    @Test
    void shouldThrowExceptionWhenTemperatureIsTooHigh() {
        // Arrange
        StorageUnit fridge = new StorageUnit("Fridge-01", 2.0, 8.0, 5.0); // min, max, current
        VaccineBatch batch = new VaccineBatch("B123", "mRNA", LocalDate.now().plusMonths(6));

        // Act & Assert
        fridge.setCurrentTemp(10.0); // Breach!
        assertThrows(InvalidColdChainException.class, () -> {
            batch.assignToStorage(fridge);
        });
    }
}