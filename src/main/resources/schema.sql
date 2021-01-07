CREATE TABLE hotel
(
    hotel_id     INT           NOT NULL AUTO_INCREMENT,
    address      VARCHAR(1000) NOT NULL,
    num_of_rooms INT           NOT NULL,
    num_of_stars INTEGER       NOT NULL,
    PRIMARY KEY (hotel_id)
);

CREATE TABLE room
(
    room_id       INTEGER               NOT NULL AUTO_INCREMENT,
    hotel_id      INTEGER               NOT NULL,
    num_of_people INTEGER               NOT NULL,
    price         double                NOT NULL,
    status        BOOLEAN DEFAULT FALSE NOT NULL,
    description   VARCHAR(1000)         NOT NULL,
    PRIMARY KEY (room_id)
);

CREATE TABLE storage
(
    storage_id INT           NOT NULL AUTO_INCREMENT,
    hotel_id   INTEGER       NOT NULL,
    address    VARCHAR(1000) NOT NULL,
    capacity   FLOAT         NOT NULL,
    PRIMARY KEY (storage_id)
);

ALTER TABLE room
    ADD CONSTRAINT fk_hotel_room FOREIGN KEY (hotel_id) REFERENCES hotel (hotel_id);

ALTER TABLE storage
    ADD CONSTRAINT fk_hotel_storage FOREIGN KEY (hotel_id) REFERENCES hotel (hotel_id);

-- used sql
CREATE TABLE users
(
    email    varchar(500)  NOT NULL,
    password varchar(1000) NOT NULL,
    regular  BOOLEAN default false,
    PRIMARY KEY (email)
);

CREATE TABLE persons
(
    person_id INT          NOT NULL AUTO_INCREMENT,
    name      VARCHAR(100) NOT NULL,
    surname   VARCHAR(100) NOT NULL,
    phone_nr  INT          NOT NULL UNIQUE,
    birthdate DATE         NOT NULL,
    address   VARCHAR(1000),
    email     VARCHAR(500) NOT NULL UNIQUE,
    PRIMARY KEY (person_id)
);

CREATE TABLE roles
(
    role_id INT          NOT NULL AUTO_INCREMENT,
    email   varchar(500) NOT NULL UNIQUE,
    name    VARCHAR(100) NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE regular_clients
(
    email                      varchar(500) NOT NULL,
    number_of_reservations     INTEGER      NOT NULL,
    regular_customer_threshold INTEGER      NOT NULL,
    discount                   float        NOT NULL,
    PRIMARY KEY (email)
);

CREATE TABLE reservations
(
    reservation_id INTEGER      NOT NULL AUTO_INCREMENT,
    email          varchar(500) NOT NULL,
    start_date     DATE         NOT NULL,
    end_date       DATE         NOT NULL,
    price          double       NOT NULL,
    PRIMARY KEY (reservation_id)
);
CREATE TABLE employees
(
    id       INTEGER      NOT NULL,
    position varchar(200) NOT NULL,
    salary   double       NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE schedules
(
    id          INTEGER AUTO_INCREMENT,
    employee_id INTEGER NOT NULL,
    start_date  DATETIME    NOT NULL,
    end_Date    DATETIME    NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE roles
    ADD CONSTRAINT fk_email FOREIGN KEY (email) REFERENCES users (email);

ALTER TABLE persons
    ADD CONSTRAINT fk_email_persons_users FOREIGN KEY (email) REFERENCES users (email);

ALTER TABLE regular_clients
    ADD CONSTRAINT fk_email_regular_clients_users FOREIGN KEY (email) REFERENCES users (email);

ALTER TABLE reservations
    ADD CONSTRAINT fk_email_reservations_users FOREIGN KEY (email) REFERENCES users (email);
ALTER TABLE employees
    ADD CONSTRAINT fk_id_person_id FOREIGN KEY (id) REFERENCES persons (person_id);
ALTER TABLE schedules
    ADD CONSTRAINT fk_schedules_id_employee_id FOREIGN KEY (employee_id) REFERENCES employees (id);