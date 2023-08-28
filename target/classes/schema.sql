CREATE TABLE IF NOT EXISTS base_station (
   id VARCHAR(50) PRIMARY KEY,
   detection_radius_in_meters FLOAT NOT NULL,
   name VARCHAR(255) NOT NULL,
   location_x FLOAT NOT NULL,
   location_y FLOAT NOT NULL
);

CREATE TABLE IF NOT EXISTS mobile_station (
  id VARCHAR(50) PRIMARY KEY,
  last_known_x FLOAT NOT NULL,
  last_known_y FLOAT NOT NULL
);