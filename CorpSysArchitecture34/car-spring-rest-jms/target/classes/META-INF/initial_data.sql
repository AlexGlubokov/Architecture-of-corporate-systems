
DROP TABLE IF EXISTS Action_Log;
DROP TABLE IF EXISTS Car;
DROP TABLE IF EXISTS Manufacturer;

CREATE TABLE Manufacturer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    origin VARCHAR(100) NOT NULL
);

CREATE TABLE Car (
    id SERIAL PRIMARY KEY,
    model VARCHAR(100) NOT NULL,
    production_year INT NOT NULL,
    color VARCHAR(50),
    manufacturer_id INT REFERENCES Manufacturer(id) ON DELETE CASCADE
);

CREATE TABLE Action_Log (
    id SERIAL PRIMARY KEY,
    action_type VARCHAR(20),
    entity_type VARCHAR(50),
    entity_id INT,
    details TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO Manufacturer (name, origin) VALUES ('Toyota', 'Japan');
INSERT INTO Car (model, production_year, color, manufacturer_id) VALUES ('Camry', 2022, 'White', 1);
