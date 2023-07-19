CREATE TABLE IF NOT EXISTS "phone_number" (
    id SERIAL PRIMARY KEY,
    phone_number VARCHAR,
    employee_id INTEGER REFERENCES "employee"(id)
);