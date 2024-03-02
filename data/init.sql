CREATE
EXTENSION IF NOT EXISTS citext;

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

CREATE TABLE "admins"
(
    "id"         uuid PRIMARY KEY NOT NULL,
    "first_name" citext           NOT NULL,
    "last_name"  citext           NOT NULL,
    "email"      citext           NOT NULL,
    "password"   varchar          NOT NULL,
    "created_at" timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "job_advertisements"
(
    "id"          uuid PRIMARY KEY NOT NULL,
    "status"      varchar(30) NOT NULL DEFAULT 'REVIEW',
    "user_id"     uuid             NOT NULL,
    "reviewer_id" uuid              NOT NULL,
    "title"       citext           NOT NULL,
    "description" varchar          NOT NULL,
    "created_at"  timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at"  timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "deleted_at"  timestamp
);

ALTER TABLE "job_advertisements"
    ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "job_advertisements"
    ADD FOREIGN KEY ("reviewer_id") REFERENCES "admins" ("id");

-- Add unique constraint to email
ALTER TABLE "users"
    ADD CONSTRAINT "unique_user_email" UNIQUE ("email");

-- Add unique constraint to email
ALTER TABLE "admins"
    ADD CONSTRAINT "unique_admin_email" UNIQUE ("email");

-- Make job advertisement title unique
ALTER TABLE "job_advertisements"
    ADD CONSTRAINT "unique_title" UNIQUE ("title");