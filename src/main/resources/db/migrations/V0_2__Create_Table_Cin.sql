CREATE TABLE IF NOT EXISTS "CIN" (
    id SERIAL PRIMARY KEY,
    number INTEGER,
    delivery_date DATE,
    delivery_location VARCHAR
);