CREATE TABLE IF NOT EXISTS "profile" (
    id SERIAL PRIMARY KEY,
    employee_id INTEGER REFERENCES "employee"(id),
    img BYTEA
);