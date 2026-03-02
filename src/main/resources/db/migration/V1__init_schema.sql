CREATE TABLE storage_units (
    unit_id VARCHAR(255) PRIMARY KEY,
    min_temp DECIMAL(4,2),
    max_temp DECIMAL(4,2),
    current_temp DECIMAL(4,2)
);

CREATE TABLE vaccine_batches (
    batch_number VARCHAR(255) PRIMARY KEY,
    vaccine_type VARCHAR(255),
    expiry_date DATE,
    storage_unit_id VARCHAR(255),
    CONSTRAINT fk_storage_unit FOREIGN KEY (storage_unit_id) REFERENCES storage_units(unit_id)
);
