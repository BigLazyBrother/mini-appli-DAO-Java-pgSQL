--------------------------------------------------------------------------------
--
-- FICHIER DE RÉINITIALISATION COMPLÈTE DE LA BASE DE DONNÉES.
--
-- user : userLambda
-- pswd : datenbankschlussel

DROP FUNCTION IF EXISTS ajouterStatistiquePatissier();
DROP FUNCTION IF EXISTS ajouterStatistiquePatisserie();
DROP TRIGGER  IF EXISTS evenementModifiePatissier ON patissier;
DROP FUNCTION IF EXISTS journaliserModifierPatissier();
DROP TRIGGER  IF EXISTS evenementAjoutePatissier ON patissier;
DROP FUNCTION IF EXISTS journaliserAjouterPatissier();
DROP TRIGGER  IF EXISTS evenementModifiePatisserie ON patisserie;
DROP FUNCTION IF EXISTS journaliserModifierPatisserie();
DROP TRIGGER  IF EXISTS evenementAjoutePatisserie ON patisserie;
DROP FUNCTION IF EXISTS journaliserAjouterPatisserie();
DROP TABLE    IF EXISTS public.journal;
DROP TABLE    IF EXISTS public.patissier;
DROP TABLE    IF EXISTS public.patisserie;

--------------------------------------------------------------------------------
-- RECRÉÉ LA TABLE DES PATISSERIES

-- Table: public.patisserie

CREATE TABLE public.patisserie (
    id        serial NOT NULL
    , nom     text
    , couleur text
    , poids   text
    , prix    text
    , CONSTRAINT patisserie_pkey PRIMARY KEY (id)
    )
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

-- ALTER TABLE public.patisserie
--     OWNER to userLambda;

--------------------------------------------------------------------------------
-- RECRÉÉ LA TABLE DES PATISSIERS. CONTRAINTE DE CLÉ ÉTRANGÈRE POUR LIER LES
-- PATISSIERS À DES PATISSERIES. EN CAS DE SUPPRÉSSION DE PATISSERIE :
-- SUPPRÉSSION DES PATISSIERS LIÉS (ON DELETE CASCADE).

-- Table: public.patissier

CREATE TABLE public.patissier (
    id              serial NOT NULL
    , nom           text
    , courriel      text
    , age           text
    , ville         text
    , id_patisserie integer NOT NULL --DEFAULT nextval('patissier_id_patisserie_seq'::regclass),
    , CONSTRAINT patissier_pkey PRIMARY KEY (id)
    , CONSTRAINT id_patisserie FOREIGN KEY (id_patisserie)
        REFERENCES public.patisserie (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE -- permet de supprimer automatiquement les patissiers liés à une patisserie lors de sa suppression
    )
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

-- ALTER TABLE public.patissier
--     OWNER to userLambda;

--------------------------------------------------------------------------------
-- RECRÉE LA NOUVELLE TABLE "JOURNAL" NÉCESSAIRE À LA TENUE
-- D'UN JOURNAL DES MODIFICATIONS DES ENTRÉES DE LA BASE DE DONNÉES.
-- CETTE TALBE N'EST PAS ACCESSIBLE PAR L'UTILISATEUR STANDARD

-- Table: public.journal

CREATE TABLE public.journal (
    id            serial NOT NULL
    , moment      timestamp with time zone
    , operation   text
    , objet       text
    , description text
    , CONSTRAINT journal_pkey PRIMARY KEY (id)
    )
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

-- ALTER TABLE public.journal
--     OWNER to postgres;

--------------------------------------------------------------------------------
-- REDÉFINIT LES PROCESSUS AUTOMATISÉS LORS DE L'AJOUT DE PATISSERIE DANS LA BDD

CREATE TRIGGER evenementAjoutePatisserie
    BEFORE INSERT ON patisserie
    FOR EACH ROW EXECUTE PROCEDURE
    journaliserAjouterPatisserie();

create or replace FUNCTION journaliserAjouterPatisserie()
    returns trigger
    language 'plpgsql'

as $$
declare

    description text;

begin

    description := new.nom || ' (' || new.couleur || ')';

    insert into journal(moment, operation, objet, description) values (now(), 'ajout', 'patisserie', description);

    return new;	-- on return new dans le cas d'un trigger interne dans la bdd.

end
$$

;

--------------------------------------------------------------------------------
-- DÉFINIT LES PROCESSUS AUTOMATISÉS LORS DE LA MODIFICATION DE PATISSERIE DANS LA BDD

create or replace FUNCTION journaliserModifierPatisserie()
    returns trigger
    language 'plpgsql'

as $$
declare

    description text;

begin

    description := new.nom || ' (' || new.couleur || ')';

    insert into journal(moment, operation, objet, description) values (now(), 'modification', 'patisserie', description);

    return new;	-- on return new dans le cas d'un trigger interne dans la bdd.

end
$$

;

CREATE TRIGGER evenementModifiePatisserie
    BEFORE UPDATE ON patisserie
    FOR EACH ROW EXECUTE PROCEDURE
    journaliserModifierPatisserie();

--------------------------------------------------------------------------------
-- DÉFINIT LES PROCESSUS AUTOMATISÉS LORS DE L'AJOUT DE PATISSIER DANS LA BDD

create or replace FUNCTION journaliserAjouterPatissier()
    returns trigger
    language 'plpgsql'

as $$
declare

    description text;

begin

    description := new.nom ;

    insert into journal(moment, operation, objet, description) values (now(), 'ajout', 'patissier', description);

    return new;	-- on return new dans le cas d'un trigger interne dans la bdd.

end
$$

;

CREATE TRIGGER evenementAjoutePatissier
    BEFORE INSERT ON patissier
    FOR EACH ROW EXECUTE PROCEDURE
    journaliserAjouterPatissier();

--------------------------------------------------------------------------------
-- DÉFINIT LES PROCESSUS AUTOMATISÉS LORS DE LA MODIFICATION DE PATISSIER DANS LA BDD

create or replace FUNCTION journaliserModifierPatissier()
    returns trigger
    language 'plpgsql'

as $$
declare

    description text;

begin

    description := new.nom ;

    insert into journal(moment, operation, objet, description) values (now(), 'modification', 'patissier', description);

    return new;	-- on return new dans le cas d'un trigger interne dans la bdd.

end
$$

;

CREATE TRIGGER evenementModifiePatissier
    BEFORE UPDATE ON patissier
    FOR EACH ROW EXECUTE PROCEDURE
    journaliserModifierPatissier();

--------------------------------------------------------------------------------
-- TEST

-- INSERT into patisserie(nom, couleur, poids, prix) VALUES('patte d`ours','brun','120','3');

--------------------------------------------------------------------------------
-- DÉFINIT LES FONCTIONS À UTILISER POUR ÉTABLIR DE NOUVELLES STATISTIQUES

CREATE OR REPLACE FUNCTION ajouterStatistiquePatisserie()
    returns trigger
    language 'plpgsql'

AS $$
declare

    val_nb_entrees integer
    val_prix_moyen integer
    val_poids_moyen integer

BEGIN

    val_nb_entrees := SELECT COUNT(id) FROM patisserie;
    val_prix_moyen := SELECT AVG( CAST(prix AS INTEGER) ) FROM patisserie;
    val_poids_moyen := SELECT AVG( CAST(poids AS INTEGER) ) FROM patisserie;
    -- les colonnes prix et poids stockent du type TEXT, on doit donc cast.

    INSERT INTO journal(moment, nb_entrees, prix_moyen, poids_moyen) VALUES (now(), val_nb_entrees, val_prix_moyen, val_poids_moyen);

    -- return new; -- on return new dans le cas d'un trigger interne dans la bdd.

END
$$

;

CREATE OR REPLACE FUNCTION ajouterStatistiquePatissier()
    returns trigger
    language 'plpgsql'

AS $$
declare

    val_nb_entrees integer
    val_age_moyen integer

BEGIN

    val_nb_entrees := SELECT COUNT(id) FROM patissier;
    val_age_moyen := SELECT AVG( CAST(age AS INTEGER) ) FROM patissier;
    -- la colonne age stocke du type TEXT, on doit donc cast.

    INSERT INTO journal(moment, nb_entrees, age_moyen) VALUES (now(), val_nb_entrees, val_age_moyen);

    -- return new; -- on return new dans le cas d'un trigger interne dans la bdd.

END
$$

;