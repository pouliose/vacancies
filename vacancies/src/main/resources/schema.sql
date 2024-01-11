drop table IF EXISTS "vacancies";
drop table IF EXISTS "companies";

--CREATE SEQUENCE companies_id_seq;

create table "companies" (
    "id" bigint default nextval('companies_id_seq') not null,
    "name" text,
    constraint "companies_pkey" primary key ("id")
);


--CREATE SEQUENCE vacancies_id_seq;

create table "vacancies" (
    "id" bigint default nextval('vacancies_id_seq') not null,
    "company" text,
    "title" text,
    "announcedDate" timestamp,
    company_id bigint,
    constraint "vacancies_pkey" primary key ("id"),
    constraint "fk_company" foreign key(company_id)
    references companies(id)
);