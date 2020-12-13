CREATE TABLE user (email VARCHAR(100) PRIMARY KEY,  password varchar(1000) NOT NULL, role varchar(100) NOT NULL);

CREATE TABLE hotel(
                    hotel_id INT NOT NULL AUTO_INCREMENT ,
                    address VARCHAR(1000) NOT NULL,
                    num_of_rooms INT NOT NULL,
                    num_of_stars INTEGER NOT NULL,
                    PRIMARY KEY(hotel_id)
);

CREATE TABLE room(
                    room_id INTEGER NOT NULL AUTO_INCREMENT,
                    hotel_id INTEGER NOT NULL,
                    num_of_people INTEGER NOT NULL,
                    price double NOT NULL,
                    status BOOLEAN DEFAULT FALSE NOT NULL,
                    description VARCHAR(1000) NOT NULL,
                    PRIMARY KEY (room_id)
);

CREATE TABLE storage(
                    storage_id INT NOT NULL AUTO_INCREMENT,
                    hotel_id INTEGER NOT NULL,
                    address VARCHAR(1000) NOT NULL,
                    capacity FLOAT NOT NULL,
                    PRIMARY KEY (storage_id)
);

ALTER TABLE room ADD CONSTRAINT fk_hotel_room FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id);

ALTER TABLE storage ADD CONSTRAINT fk_hotel_storage FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id);