CREATE TABLE IF NOT EXISTS cin (
    id SERIAL PRIMARY KEY,
    number INTEGER UNIQUE,
    delivery_date DATE,
    delivery_location VARCHAR
);