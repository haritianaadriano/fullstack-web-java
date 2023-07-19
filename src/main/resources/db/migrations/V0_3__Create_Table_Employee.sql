CREATE TABLE IF NOT EXISTS "employee" (
    id SERIAL PRIMARY KEY,
    ref VARCHAR,
    firstname VARCHAR,
    lastname VARCHAR,
    sexe sexe,
    adress VARCHAR,
    job VARCHAR,
    csp csp,
    CNAPS VARCHAR,
    children INTEGER,
    begin_date DATE,
    finish_date DATE,
    email_pro VARCHAR,
    email_perso VARCHAR,
    CIN_id INTEGER,
    birthdate DATE,
    profile_id INTEGER
);