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
    manufacturer_id INT REFERENCES Manufacturer(id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
);


INSERT INTO Manufacturer (name, origin) VALUES ('Toyota', 'Japan');
INSERT INTO Manufacturer (name, origin) VALUES ('BMW', 'Germany');

INSERT INTO Car (model, production_year, color, manufacturer_id) 
VALUES ('Camry', 2022, 'White', (SELECT id FROM Manufacturer WHERE name = 'Toyota'));

INSERT INTO Car (model, production_year, color, manufacturer_id) 
VALUES ('X5', 2023, 'Black', (SELECT id FROM Manufacturer WHERE name = 'BMW'));
