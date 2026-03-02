package com.coldchain.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageUnit {
    private String unitId;
    private double minTemp;
    private double maxTemp;
    private double currentTemp;
}