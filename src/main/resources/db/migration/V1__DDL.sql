-- SEQUENCE: public.roles_id_seq

-- DROP SEQUENCE public.roles_id_seq;

CREATE SEQUENCE public.roles_id_seq
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 2147483647
CACHE 1;

ALTER SEQUENCE public.roles_id_seq
OWNER TO postgres;

-- SEQUENCE: public.users_id_seq

-- DROP SEQUENCE public.users_id_seq;

CREATE SEQUENCE public.users_id_seq
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;

ALTER SEQUENCE public.users_id_seq
OWNER TO postgres;


-- Table: public.roles

-- DROP TABLE public.roles;

CREATE TABLE public.roles
(
    id integer NOT NULL DEFAULT nextval('roles_id_seq'::regclass),
name character varying(20) COLLATE pg_catalog."default",
CONSTRAINT roles_pkey PRIMARY KEY (id)
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.roles
OWNER to postgres;

-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
    id bigint NOT NULL DEFAULT nextval('users_id_seq'::regclass),
email character varying(50) COLLATE pg_catalog."default",
password character varying(120) COLLATE pg_catalog."default",
username character varying(20) COLLATE pg_catalog."default",
CONSTRAINT users_pkey PRIMARY KEY (id),
CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email)
--CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username)
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.users
OWNER to postgres;


-- Table: public.user_roles

-- DROP TABLE public.user_roles;

CREATE TABLE public.user_roles
(
    user_id bigint NOT NULL,
role_id integer NOT NULL,
CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id)
REFERENCES public.roles (id) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id)
REFERENCES public.users (id) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.user_roles
OWNER to postgres;
