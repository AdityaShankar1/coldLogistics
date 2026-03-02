package com.coldchain.application;

import com.coldchain.application.port.out.IStorageRepository;
import com.coldchain.application.port.out.IVaccineRepository;
import com.coldchain.domain.model.VaccineBatch;
import com.coldchain.domain.service.VaccineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VaccineServiceTest {

    @Mock
    private IVaccineRepository vaccineRepository;

    @Mock
    private IStorageRepository storageRepository;

    private VaccineService vaccineService;

    @BeforeEach
    void setUp() {
        // Fix: Inject mocks into the constructor
        vaccineService = new VaccineService(vaccineRepository, storageRepository);
    }

    @Test
    void testRegisterBatch() {
        // Fix: Use LocalDate.parse instead of raw String
        LocalDate expiryDate = LocalDate.parse("2026-12-31");

        when(vaccineRepository.save(any(VaccineBatch.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        VaccineBatch batch = vaccineService.registerBatch("BATCH1", "COVID", expiryDate);

        assertNotNull(batch);
    }
}