CREATE EXTENSION IF NOT EXISTS citext;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "roles"
(
    "id"   uuid PRIMARY KEY NOT NULL,
    "name" varchar(30) NOT NULL
);

CREATE TABLE "users"
(
    "id"         uuid PRIMARY KEY NOT NULL,
    "first_name" citext           NOT NULL,
    "last_name"  citext           NOT NULL,
    "email"      citext           NOT NULL,
    "password"   varchar          NOT NULL,
    "created_at" timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "user_roles"
(
    "user_id" uuid NOT NULL,
    "role_id" uuid NOT NULL,
    PRIMARY KEY ("user_id", "role_id")
);

CREATE TABLE "job_advertisements"
(
    "id"          uuid PRIMARY KEY NOT NULL,
    "status"      varchar(30) NOT NULL DEFAULT 'REVIEW',
    "user_id"     uuid             NOT NULL,
    "reviewer_id" uuid,
    "title"       citext           NOT NULL,
    "description" varchar          NOT NULL,
    "created_at"  timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at"  timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "deleted_at"  timestamp
);

ALTER TABLE "job_advertisements"
    ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "job_advertisements"
    ADD FOREIGN KEY ("reviewer_id") REFERENCES "users" ("id");

-- Add unique constraint to email
ALTER TABLE "users"
    ADD CONSTRAINT "unique_user_email" UNIQUE ("email");

-- Make job advertisement title unique
ALTER TABLE "job_advertisements"
    ADD CONSTRAINT "unique_title" UNIQUE ("title");

-- Make role name unique
ALTER TABLE "roles"
    ADD CONSTRAINT "unique_role_name" UNIQUE ("name");

-- Insert Roles
INSERT INTO "roles" ("id", "name") VALUES (uuid_generate_v4(), 'ADMIN');
INSERT INTO "roles" ("id", "name") VALUES (uuid_generate_v4(), 'USER');

-- Insert Sample Users
INSERT INTO "users" VALUES
(uuid_generate_v4(), 'Jan', 'El', 'admin@gmail.com', '$2a$10$gqHrslMttQWSsDSVRTK1OehkkBiXsJ/a4z2OURU./dizwOQu5Lovu'),
(uuid_generate_v4(), 'Krzysztof', 'Ko', 'test@gmail.com', '$2a$12$TYSPPDsgR1T9vpgMSavOteZoqzjGVLt7rzsqKLrGL4oQdE3rWDNru');

-- Insert User Roles
INSERT INTO "user_roles" VALUES
((SELECT "id" FROM "users" WHERE "email" = 'admin@gmail.com'), (SELECT "id" FROM "roles" WHERE "name" = 'ADMIN')),
((SELECT "id" FROM "users" WHERE "email" = 'test@gmail.com'), (SELECT "id" FROM "roles" WHERE "name" = 'USER'));