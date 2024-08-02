CREATE OR REPLACE PROCEDURE InsertHostAndPet(
    p_firstName VARCHAR2(20),
    p_lastName VARCHAR2(20),
    p_address VARCHAR2(100),
    p_phone VARCHAR2(20),
    p_email VARCHAR2(20),
    p_petName VARCHAR2(20),
    p_species VARCHAR2(20),
    p_breed VARCHAR2(20),
    p_age INT,
    p_sex CHAR(1),
    p_weight FLOAT
) AS
    v_hostID INT;
BEGIN

    INSERT INTO Host (firstName, lastName, address, phone, email)
    VALUES (p_firstName, p_lastName, p_address, p_phone, p_email)
    RETURNING hostID INTO v_hostID;

    INSERT INTO Pet (name, species, breed, age, sex, weight, hostID)
    VALUES (p_petName, p_species, p_breed, p_age, p_sex, p_weight, v_hostID);

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END;