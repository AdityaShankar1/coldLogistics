package com.coldchain.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "vaccine_batches")
@Data
public class VaccineBatchEntity {
    @Id
    private String batchNumber;
    private String vaccineType;
    private LocalDate expiryDate;

    @ManyToOne
    @JoinColumn(name = "storage_unit_id")
    private StorageUnitEntity storageUnit;
}
