-- En fait j'avais pas vu ce fichier, j'avais créé la BDD patisserie direct via PostgreSQL

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;


CREATE TABLE patisserie (
    nom text,
    couleur text,
    poids text,
    prix text,
    id integer NOT NULL
);


ALTER TABLE patisserie OWNER TO postgres;

--
-- Name: patisserie_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE patisserie_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE patisserie_id_seq OWNER TO postgres;

--
-- Name: patisserie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE patisserie_id_seq OWNED BY patisserie.id;



ALTER TABLE ONLY patisserie ALTER COLUMN id SET DEFAULT nextval('patisserie_id_seq'::regclass);


--
-- PostgreSQL database dump complete
--

