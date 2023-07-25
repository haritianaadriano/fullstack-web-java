CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS "user" (
    id uuid PRIMARY KEY default uuid_generate_v4(),
    username VARCHAR,
    password VARCHAR
);