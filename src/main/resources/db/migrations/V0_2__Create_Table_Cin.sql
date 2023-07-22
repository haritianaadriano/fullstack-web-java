CREATE TABLE IF NOT EXISTS cin (
    id SERIAL PRIMARY KEY,
    number INTEGER,
    delivery_date DATE,
    delivery_location VARCHAR
);