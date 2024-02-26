CREATE EXTENSION IF NOT EXISTS citext;

CREATE TYPE "statuses" AS ENUM (
  'TODO',
  'IN_PROGRESS',
  'DONE'
);

CREATE TYPE "priorities" AS ENUM (
  'LOW',
  'MEDIUM',
  'HIGH'
);

CREATE TABLE "users" (
                         "id" uuid PRIMARY KEY NOT NULL,
                         "first_name" citext NOT NULL,
                         "last_name" citext NOT NULL,
                         "email" citext NOT NULL,
                         "password" varchar NOT NULL,
                         "created_at" timestamp,
                         "updated_at" timestamp
);

CREATE TABLE "tasks" (
                         "id" uuid PRIMARY KEY NOT NULL,
                         "user_id" uuid NOT NULL,
                         "status" statuses NOT NULL DEFAULT 'TODO',
                         "title" varchar NOT NULL,
                         "description" varchar NOT NULL,
                         "priority" priorities,
                         "deadline" timestamp
);

ALTER TABLE "tasks" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");
