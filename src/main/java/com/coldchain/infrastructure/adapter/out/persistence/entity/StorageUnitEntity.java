package com.coldchain.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "storage_units")
@Data
public class StorageUnitEntity {
    @Id
    private String unitId;
    private double minTemp;
    private double maxTemp;
    private double currentTemp;
}
