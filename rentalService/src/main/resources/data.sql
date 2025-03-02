-- Création de la table 'car'
CREATE TABLE IF NOT EXISTS car (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    plate_number VARCHAR(255) NOT NULL UNIQUE,
    brand VARCHAR(255),
    price INT,
    rent BOOLEAN
);

-- Création de la table 'dates'
CREATE TABLE IF NOT EXISTS dates (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    car_id BIGINT NOT NULL,
    begin DATE NOT NULL,
    end DATE NOT NULL,
    FOREIGN KEY (car_id) REFERENCES car(id) ON DELETE CASCADE
);

-- Vidage des tables
DELETE FROM dates;
DELETE FROM car;

-- Insertion des données dans la table 'car'
INSERT INTO car (plate_number, brand, price, rent) VALUES
   ('ABCD', 'Citroen', 10000, false),
   ('EFGH', 'Renault', 11000, false),
   ('IJKL', 'Peugeot', 12000, false),
   ('MNOP', 'Peugeot', 13000, false),
   ('QRST', 'Citroen', 14000, false),
   ('UVWX', 'Renault', 15000, false);

COMMIT;
