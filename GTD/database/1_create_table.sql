-- Vytvoreni databaze
SET echo ON
--1 Osoby
CREATE TABLE persons
  (
    id    INTEGER NOT NULL ,
    login VARCHAR2 (20 CHAR) NOT NULL ,
    fname VARCHAR2 (20 CHAR) NOT NULL ,
    sname VARCHAR2 (20 CHAR) NOT NULL ,
    --pass_hash INTEGER  NOT NULL , MPA ostraneno 10. 5. 2014
    id_type INTEGER NOT NULL
  ) ;
COMMENT ON TABLE persons
IS
  'Tabulka osob';
  COMMENT ON column persons.id
IS
  'ID osoby';
  COMMENT ON column persons.login
IS
  'Přihlašovaci jméno';
  COMMENT ON column persons.fname
IS
  'Jméno osoby';
  COMMENT ON column persons.sname
IS
  'Příjmení osoby';
  --comment on column persons.pass_hash   is 'Heslo (Uložena hodnota funkce ora_hash)';
  COMMENT ON column persons.id_type
IS
  'ID typu záznamu (Aktivni/Neaktivni)';
  ALTER TABLE persons ADD CONSTRAINT "persons id PK" PRIMARY KEY ( id ) ;
  ALTER TABLE persons ADD CONSTRAINT "persons login UK" UNIQUE ( login ) ;
  --2 uživatelský číselník kontextů
  CREATE TABLE contexts
    (
      id        INTEGER NOT NULL ,
      id_person INTEGER NOT NULL ,
      name      VARCHAR2 (100) NOT NULL
    ) ;
  COMMENT ON TABLE contexts
IS
  'Uživatelský číselník kontextů';
  COMMENT ON column contexts.id
IS
  'ID kontextu';
  COMMENT ON column contexts.id_person
IS
  'ID osoby, ktere patri kontext';
  COMMENT ON column contexts.name
IS
  'Název kontextu';
  ALTER TABLE contexts ADD CONSTRAINT "contexts person PK" PRIMARY KEY ( id,id_person ) ;
  ALTER TABLE contexts ADD CONSTRAINT "contexts name UK" UNIQUE ( id_person, name ) ;
  ALTER TABLE contexts ADD CONSTRAINT "contexts person FK" FOREIGN KEY (id_person) REFERENCES persons (id);
  --3 Cinnosti
  CREATE TABLE activities
    (
      id          INTEGER NOT NULL ,
      id_person   INTEGER NOT NULL ,
      name        VARCHAR2 (100) NOT NULL ,
      description VARCHAR2 (1000) ,
      id_type     INTEGER NOT NULL
    ) ;
  COMMENT ON TABLE activities
IS
  'Tabulka činností';
  COMMENT ON column activities.id
IS
  'ID činnosti';
  COMMENT ON column activities.id_person
IS
  'ID osoby, která činnost vytvořila';
  COMMENT ON column activities.name
IS
  'Název činnosti';
  COMMENT ON column activities.description
IS
  'Popis činnosti';
  COMMENT ON column activities.id_type
IS
  'ID typu záznamu (Ke zpracování/Zahozená/...)';
  ALTER TABLE activities ADD CONSTRAINT "activities id PK" PRIMARY KEY ( id ) ;
  ALTER TABLE activities ADD CONSTRAINT "activities person FK" FOREIGN KEY (id_person) REFERENCES persons (id);
  --4 kontakty
  CREATE TABLE contacts
    (
      id        INTEGER NOT NULL ,
      id_person INTEGER NOT NULL ,
      value     VARCHAR2 (100) NOT NULL ,
      id_type   INTEGER NOT NULL
    ) ;
  COMMENT ON TABLE contacts
IS
  'Tabulka kontaktů';
  COMMENT ON column contacts.id
IS
  'ID kontaktu';
  COMMENT ON column contacts.id_person
IS
  'ID osoby, které patří kontakt';
  COMMENT ON column contacts.value
IS
  'Hodnota kontaktu';
  COMMENT ON column contacts.id_type
IS
  'ID typu záznamu (Telefon/E-mail/...)';
  ALTER TABLE contacts ADD CONSTRAINT "contacts id PK" PRIMARY KEY (id) ;
  ALTER TABLE contacts ADD CONSTRAINT "contacts type UK" UNIQUE (id_person,id_type) ;
  ALTER TABLE contacts ADD CONSTRAINT "contacts person FK" FOREIGN KEY (id_person) REFERENCES persons (id);
  --5 Projects
  CREATE TABLE projects
    (
      id                INTEGER NOT NULL ,
      name              VARCHAR2 (100) NOT NULL ,
      description       VARCHAR2 (1000) ,
      id_person         INTEGER NOT NULL ,
      id_type           INTEGER NOT NULL ,
      id_project_parent INTEGER, --hlavní/jedinný project má id_project_parent null
      status            NUMBER DEFAULT 0 NOT NULL
    ) ;
  COMMENT ON TABLE projects
IS
  'Tabulka projektů';
  COMMENT ON column projects.id
IS
  'ID projektu';
  COMMENT ON column projects.name
IS
  'Název projektu';
  COMMENT ON column projects.description
IS
  'Popis projektu';
  COMMENT ON column projects.id_person
IS
  'ID osoby, které patří projekt';
  COMMENT ON column projects.id_project_parent
IS
  'ID nadřazeného projektu (Prázdné pro hlavní projekt. Root projekt odkazuje na hlavní projekt.)';
  COMMENT ON column projects.id_type
IS
  'ID typu projektu (Aktivni/Dokončený)';
  COMMENT ON column projects.status
IS
  'Stav položky 0=aktivní/1=Smazáno';
  ALTER TABLE projects ADD CONSTRAINT "projects id PK" PRIMARY KEY ( id ) ;
  ALTER TABLE projects ADD CONSTRAINT "projects person FK" FOREIGN KEY (id_person) REFERENCES persons (id);
  ALTER TABLE projects ADD CONSTRAINT "projects parent FK" FOREIGN KEY (id_project_parent) REFERENCES projects (id);
  --6 members
  CREATE TABLE members
    (
      id_project INTEGER NOT NULL ,
      id_person  INTEGER NOT NULL
    ) ;
  COMMENT ON TABLE members
IS
  'Tabulka členů root projektu';
  COMMENT ON column members.id_project
IS
  'ID projektu';
  COMMENT ON column members.id_person
IS
  'ID osoby';
  ALTER TABLE members ADD CONSTRAINT "members PK" PRIMARY KEY ( id_project, id_person ) ;
  ALTER TABLE members ADD CONSTRAINT "members person FK" FOREIGN KEY (id_person) REFERENCES persons (id) ;
  ALTER TABLE members ADD CONSTRAINT "members project FK" FOREIGN KEY (id_project) REFERENCES projects (id);
  --7 tasks
  CREATE TABLE tasks
    (
      id          INTEGER NOT NULL ,
      id_creator  INTEGER NOT NULL ,
      id_owner    INTEGER NOT NULL ,
      id_context  INTEGER ,
      id_project  INTEGER ,--NOT NULL , MPA zruseno 10. 5. 2014
      name        VARCHAR2 (100) NOT NULL ,
      description VARCHAR2 (1000) ,
      id_type     INTEGER NOT NULL,
      status      NUMBER DEFAULT 0 NOT NULL
    ) ;
  COMMENT ON TABLE tasks
IS
  'Tabulka ukolů';
  COMMENT ON column tasks.id
IS
  'ID ukolu';
  COMMENT ON column tasks.id_creator
IS
  'ID osoby, která úkol vytvořila';
  COMMENT ON column tasks.id_owner
IS
  'ID osoby, ktere patří úkol (vlastník)';
  COMMENT ON column tasks.id_context
IS
  'ID contextu z vlastníka';
  COMMENT ON column tasks.id_project
IS
  'ID projektu, do kterého patří úkol';
  COMMENT ON column tasks.name
IS
  'Název úkolu';
  COMMENT ON column tasks.description
IS
  'Popis úkolu';
  COMMENT ON column tasks.id_type
IS
  'ID typu záznamu úkolu (Vytvořený/Aktivní/V kalendáři/Hotový)';
  COMMENT ON column tasks.status
IS
  'Stav položky 0=aktivní/1=Smazáno';
  ALTER TABLE tasks ADD CONSTRAINT "tasks id PK" PRIMARY KEY ( id ) ;
  ALTER TABLE tasks ADD CONSTRAINT "task creator FK" FOREIGN KEY (id_creator) REFERENCES persons (id);
  ALTER TABLE tasks ADD CONSTRAINT "task owner FK" FOREIGN KEY (id_owner) REFERENCES persons (id);
  ALTER TABLE tasks ADD CONSTRAINT "tasks project FK" FOREIGN KEY (id_project) NULL REFERENCES projects (id);
  ALTER TABLE tasks ADD CONSTRAINT "tasks context FK" FOREIGN KEY (id_context,id_owner) REFERENCES contexts (id,id_person) ;
  --8 intervals
  CREATE TABLE intervals
    (
      id_task   INTEGER NOT NULL ,
      date_from DATE NOT NULL ,
      date_to   DATE NOT NULL
    ) ;
  COMMENT ON TABLE intervals
IS
  'Tabulka intevalů úkolu';
  COMMENT ON column intervals.id_task
IS
  'ID úkolu (úkol má pouze jeden interval)';
  COMMENT ON column intervals.date_from
IS
  'Datum od';
  COMMENT ON column intervals.date_to
IS
  'Datum do';
  ALTER TABLE intervals ADD CONSTRAINT "intervals task UK" UNIQUE ( id_task ) ;
  ALTER TABLE intervals ADD CONSTRAINT "intervals task FK" FOREIGN KEY (id_task) REFERENCES tasks (id);
  --9 tabulka typu zaznamu jednotlivych tabulek
  CREATE TABLE types
    (
      id          INTEGER NOT NULL ,
      table_name  VARCHAR2 (50) NOT NULL,
      code        VARCHAR2 (2) NOT NULL ,
      name        VARCHAR2 (20) NOT NULL ,
      description VARCHAR2 (200)
    ) ;
  COMMENT ON TABLE types
IS
  'Tabulka s čiselníkem typů zaznamů v tabulkách';
  COMMENT ON column types.id
IS
  'ID typu';
  COMMENT ON column types.table_name
IS
  'Název tabulky ve které se typ vyskytuje';
  COMMENT ON column types.code
IS
  'Dvouznaková zkratka typu';
  COMMENT ON column types.name
IS
  'Krátký název typu';
  COMMENT ON column types.description
IS
  'Dlouhý popis typu';
  ALTER TABLE types ADD CONSTRAINT "id_types PK" PRIMARY KEY ( id ) ;
  ALTER TABLE types ADD CONSTRAINT "id_types name UK" UNIQUE ( table_name, name ) ;--v jedne tabulce jedna unikatni definice pro typ
  ALTER TABLE types ADD CONSTRAINT "id_types code UK" UNIQUE ( table_name, code ) ;--v jedne tabulce jedna unikatni definice pro typ
  --reference z tabulek pouzivajici id_type
  --vazby do vsech tabulek. Vyber Typ(table_name), bude ostren aplikaci/procedurou
  ALTER TABLE persons ADD CONSTRAINT "persons id_types FK" FOREIGN KEY (id_type) REFERENCES types (id);
  ALTER TABLE activities ADD CONSTRAINT "activities id_types FK" FOREIGN KEY (id_type) REFERENCES types (id);
  ALTER TABLE contacts ADD CONSTRAINT "contacts id_types FK" FOREIGN KEY (id_type) REFERENCES types (id);
  ALTER TABLE tasks ADD CONSTRAINT "tasks id_types FK" FOREIGN KEY (id_type) REFERENCES types (id);
  ALTER TABLE projects ADD CONSTRAINT "projects id_types FK" FOREIGN KEY (id_type) REFERENCES types (id);
  --Spolecna sekvence pro vsechna id
CREATE SEQUENCE insert_seq MINVALUE 1 START WITH 1 INCREMENT BY 1 NOCACHE;
