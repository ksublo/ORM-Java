CREATE TABLE Host (
                      hostID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                      firstName VARCHAR2(20) NOT NULL,
                      lastName VARCHAR2(20) NOT NULL,
                      address VARCHAR2(100) NOT NULL,
                      phone VARCHAR2(20) NOT NULL,
                      email VARCHAR2(20)
);

CREATE TABLE Pet (
                     petID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                     name VARCHAR2(20) NOT NULL,
                     species VARCHAR2(20) NOT NULL,
                     breed VARCHAR2(20),
                     age INT,
                     sex CHAR(1) CHECK (sex IN ('M', 'F')),
                     weight FLOAT,
                     hostID INT NOT NULL,
                     CONSTRAINT fk_host FOREIGN KEY (hostID) REFERENCES Host(hostID)
);
