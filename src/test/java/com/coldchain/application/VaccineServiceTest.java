package com.coldchain.application;

import com.coldchain.application.port.out.IVaccineRepository;
import com.coldchain.application.port.out.IStorageRepository;
import com.coldchain.domain.model.VaccineBatch;
import com.coldchain.domain.service.VaccineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VaccineServiceTest {

    private IVaccineRepository vaccineRepository;
    private IStorageRepository storageRepository;
    private VaccineService vaccineService;

    @BeforeEach
    void setUp() {
        vaccineRepository = Mockito.mock(IVaccineRepository.class);
        storageRepository = Mockito.mock(IStorageRepository.class);
        vaccineService = new VaccineService(vaccineRepository, storageRepository);
    }

    @Test
    void shouldSaveBatchWhenRegistered() {
        // Arrange
        when(vaccineRepository.save(any(VaccineBatch.class))).thenReturn(new VaccineBatch());

        // Act
        vaccineService.registerBatch("B1", "mRNA", "2026-12-31");

        // Assert
        verify(vaccineRepository).save(any(VaccineBatch.class));
    }
}
