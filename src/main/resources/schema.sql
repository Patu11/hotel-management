CREATE TABLE user (email VARCHAR(100) PRIMARY KEY,  password varchar(1000) NOT NULL, role varchar(100) NOT NULL);

-- used sql
CREATE TABLE users(
                      email varchar(500) NOT NULL,
                      password varchar(1000) NOT NULL,
                      regular BOOLEAN default false,
                      PRIMARY KEY(email)
);

CREATE TABLE persons(
                        person_id INT NOT NULL AUTO_INCREMENT,
                        name VARCHAR(100) NOT NULL,
                        surname VARCHAR(100) NOT NULL,
                        phone_nr INT NOT NULL,
                        birthdate DATE NOT NULL,
                        address VARCHAR(1000),
                        email VARCHAR(500) NOT NULL,
                        PRIMARY KEY (person_id)
);

CREATE TABLE roles(
                      role_id INT NOT NULL AUTO_INCREMENT,
                      email varchar(500) NOT NULL,
                      name VARCHAR(100) NOT NULL,
                      PRIMARY KEY (role_id)
);

CREATE TABLE regular_clients(
                                email varchar(500) NOT NULL,
                                number_of_reservations INTEGER NOT NULL,
                                regular_customer_threshold INTEGER NOT NULL,
                                discount float NOT NULL
);

CREATE TABLE reservations (
                              reservation_id INTEGER NOT NULL AUTO_INCREMENT,
                              email varchar(500) NOT NULL,
                              start_date DATE NOT NULL,
                              end_date DATE NOT NULL,
                              price double NOT NULL,
                              PRIMARY KEY (reservation_id)
);
ALTER TABLE roles
    ADD CONSTRAINT fk_email FOREIGN KEY (email) REFERENCES users(email);

ALTER TABLE persons
    ADD CONSTRAINT fk_email_persons_users FOREIGN KEY (email) REFERENCES users(email);

ALTER TABLE regular_clients
    ADD CONSTRAINT fk_email_regular_clients_users FOREIGN KEY (email) REFERENCES users(email);

ALTER TABLE reservations
    ADD CONSTRAINT fk_email_reservations_users FOREIGN KEY (email) REFERENCES users(email);