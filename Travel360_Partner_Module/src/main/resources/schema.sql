-- Create partners table
CREATE TABLE IF NOT EXISTS partners (
    PartnerID BIGINT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Type VARCHAR(50) NOT NULL,
    ContactEmail VARCHAR(255),
    ContactPhone VARCHAR(20),
    Status VARCHAR(50)
);

-- Create flightinventory table
CREATE TABLE IF NOT EXISTS flightinventory (
    flight_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    partner_id BIGINT NOT NULL,
    flight_number VARCHAR(50) NOT NULL,
    source VARCHAR(100),
    destination VARCHAR(100),
    travel_date DATE,
    departure_time TIME,
    arrival_time TIME,
    total_seats INT,
    available_seats INT,
    price DOUBLE,
    status VARCHAR(50),
    FOREIGN KEY (partner_id) REFERENCES partners(PartnerID)
);

-- Create hotelinventory table
CREATE TABLE IF NOT EXISTS hotelinventory (
    hotel_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    partner_id BIGINT NOT NULL,
    hotel_name VARCHAR(255),
    location VARCHAR(255),
    room_type VARCHAR(50),
    total_rooms INT,
    available_rooms INT,
    price_per_night DOUBLE,
    status VARCHAR(50),
    FOREIGN KEY (partner_id) REFERENCES partners(PartnerID)
);

-- Create cabinventory table
CREATE TABLE IF NOT EXISTS cabinventory (
    cab_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    partner_id BIGINT NOT NULL,
    vehicle_type VARCHAR(50),
    car_number VARCHAR(50) NOT NULL,
    driver_name VARCHAR(255),
    driver_phone VARCHAR(20) NOT NULL,
    source VARCHAR(100),
    destination VARCHAR(100),
    available_units INT,
    price_per_km DOUBLE,
    base_fare DOUBLE,
    status VARCHAR(50),
    FOREIGN KEY (partner_id) REFERENCES partners(PartnerID)
);

