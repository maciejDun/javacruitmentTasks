CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
	id               UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
	username         VARCHAR(40) UNIQUE NOT NULL,
	email            VARCHAR(80) UNIQUE NOT NULL,
	creation_date    TIMESTAMP NOT NULL DEFAULT current_timestamp
);