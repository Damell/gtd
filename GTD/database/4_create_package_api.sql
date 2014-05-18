CREATE OR REPLACE PACKAGE API
AS
  /*
  * API rozhraní pro aplikaci GTD
  */
  /*
  * Nastaveni prava k potrebnym tabulkam, view a package pro všechny aktivní osoby
  */
  PROCEDURE NASTAV_PRAVA_TABULEK;
  /*
  * Vložení/oprava aktivity
  *
  *    @param inp_id          id činnosti (pokud je uvedeno id provede se změna tohoto id)
  *    @param inp_id_person   id osoby
  *    @param inp_name        název činnosti
  *    @param inp_description popis činnosti
  *    @param inp_id_type     stav  činnosti
  */
  PROCEDURE activities_iu(
      inp_id          IN activities.id%type DEFAULT NULL ,
      inp_id_person   IN activities.id_person%type DEFAULT NULL ,
      inp_name        IN activities.name%type DEFAULT NULL ,
      inp_description IN activities.description%type DEFAULT NULL ,
      inp_id_type     IN activities.id_type%type DEFAULT NULL);
  /*
  * Smazání aktivity
  *
  *    @param inp_id          id činnosti ke smazání
  */
  PROCEDURE activities_del(
      inp_id IN activities.id%type DEFAULT NULL);
  /*
  * Vložení/oprava osoby
  *
  *    @param inp_id          id osoby (pokud je uvedeno id provede se změna tohoto id)
  *    @param inp_login       login osoby
  *    @param inp_fname       jméno
  *    @param inp_sname popis přijmení
  */
  PROCEDURE persons_iu(
      inp_id    IN persons.id%type DEFAULT NULL ,
      inp_login IN persons.login%type DEFAULT NULL ,
      inp_fname IN persons.fname%type DEFAULT NULL ,
      inp_sname IN persons.sname%type DEFAULT NULL);
  /*
  * Deaktivace osoby
  *
  *    @param inp_id          id osoby k deaktivaci
  */
  PROCEDURE persons_del(
      inp_id IN persons.id%type DEFAULT NULL);
  /*
  * Vložení/oprava projektu
  *
  *    @param inp_id          id projektu (pokud je uvedeno id provede se změna tohoto id)
  *    @param inp_id_person   id osoby vlastníka
  *    @param inp_name        název projektu
  *    @param inp_description popis projektu
  *    @param inp_id_project_parent nadřazený projekt (root=null)
  *    @param inp_id_type     stav projektu
  *    @param out_id          výstupní parametr id vytvořeného projektu
  */
  PROCEDURE projects_iu(
      inp_id                IN projects.id%type DEFAULT NULL ,
      inp_id_person         IN projects.id_person%type DEFAULT NULL ,
      inp_name              IN projects.name%type DEFAULT NULL ,
      inp_description       IN projects.description%type DEFAULT NULL ,
      inp_id_project_parent IN projects.id_project_parent%type DEFAULT NULL ,
      inp_id_type           IN projects.id_type%type DEFAULT NULL ,
      out_id OUT projects.id%type);
  /*
  * Deaktivace projektu
  *
  *    @param inp_id          id projektu k deaktivaci
  */
  PROCEDURE projects_del(
      inp_id IN projects.id%type DEFAULT NULL);
  /*
  * Vložení/oprava úkolu
  *
  *    @param inp_id          id úkolu (pokud je uvedeno id provede se změna tohoto id)
  *    @param inp_id_creator  id osoby , která úkol vytvořila
  *    @param inp_id_owner    id osoby vlastníka
  *    @param inp_name        název úkolu
  *    @param inp_description popis úkolu
  *    @param inp_id_project  id projektu úkolu
  *    @param inp_id_type     stav úkolu
  *    @param inp_date_from   Kalendář - datum od
  *    @param inp_date_to     Kalendář - datum do
  */
  PROCEDURE tasks_iu(
      inp_id          IN tasks.id%type DEFAULT NULL ,
      inp_id_creator  IN tasks.id_creator%type DEFAULT NULL ,
      inp_id_owner    IN tasks.id_owner%type DEFAULT NULL ,
      inp_name        IN tasks.name%type DEFAULT NULL ,
      inp_description IN tasks.description%type DEFAULT NULL ,
      inp_id_project  IN tasks.id_project%type DEFAULT NULL ,
      inp_id_type     IN tasks.id_type%type DEFAULT NULL ,
      inp_date_from   IN VARCHAR2 DEFAULT NULL ,
      inp_date_to     IN VARCHAR2 DEFAULT NULL);
  /*
  * Deaktivace úkolu
  *
  *    @param inp_id          id úkolu k deaktivaci
  */
  PROCEDURE tasks_del(
      inp_id IN tasks.id%type DEFAULT NULL);
  /*
  * Vložení/oprava kontextu
  *
  *    @param inp_id          id kontextu (pokud je uvedeno id provede se změna tohoto id)
  *    @param inp_name        název kontextu
  *    @param inp_id_person   id osoby, které patří kontext
  */
  PROCEDURE contexts_iu(
      inp_id        IN contexts.id%type DEFAULT NULL ,
      inp_name      IN contexts.name%type DEFAULT NULL ,
      inp_id_person IN contexts.id_person%type DEFAULT NULL);
  /*
  * Smazání kontextu
  *
  *    @param inp_id          id kontextu k deaktivaci
  */
  PROCEDURE contexts_del(
      inp_id IN contexts.id%type DEFAULT NULL);
  /*
  * Vložení člena projektu
  *
  *    @param inp_id_project       id projektu
  *    @param inp_id_person        id osoby
  */
  PROCEDURE members_iu(
      inp_id_project IN members.id_project%type DEFAULT NULL ,
      inp_id_person  IN members.id_person%type DEFAULT NULL);
  /*
  * Smazání člena projektu
  *
  *    @param inp_id_project       id projektu
  *    @param inp_id_person        id osoby
  */
  PROCEDURE members_del(
      inp_id_project IN members.id_project%type DEFAULT NULL ,
      inp_id_person  IN members.id_person%type DEFAULT NULL);
END API;
/
CREATE OR REPLACE PACKAGE BODY API
AS
PROCEDURE NASTAV_PRAVA_TABULEK
IS
BEGIN
  FOR i IN
  (SELECT login FROM persons_v
  )
  LOOP
    BEGIN
      --table
      EXECUTE IMMEDIATE 'grant select, insert, update, delete on ACTIVITIES to '||upper(i.login);
      EXECUTE IMMEDIATE 'grant select, insert, update, delete on CONTACTS to '||upper(i.login);
      EXECUTE IMMEDIATE 'grant select, insert, update, delete on CONTEXTS to '||upper(i.login);
      EXECUTE IMMEDIATE 'grant select, insert, update, delete on INTERVALS to '||upper(i.login);
      EXECUTE IMMEDIATE 'grant select, insert, update, delete on MEMBERS to '||upper(i.login);
      EXECUTE IMMEDIATE 'grant select                         on PERSONS to '||upper(i.login);
      EXECUTE IMMEDIATE 'grant select, insert, update, delete on PROJECTS to '||upper(i.login);
      EXECUTE IMMEDIATE 'grant select, insert, update, delete on TASKS to '||upper(i.login);
      EXECUTE IMMEDIATE 'grant select                         on TYPES to '||upper(i.login);
      --view
      EXECUTE IMMEDIATE 'grant select on ACTIVITIES_V to '||upper(i.login);
      EXECUTE IMMEDIATE 'grant select on MEMBERS_V to '||upper(i.login);
      EXECUTE IMMEDIATE 'grant select on PERSONS_V to '||upper(i.login);
      EXECUTE IMMEDIATE 'grant select on PROJECTS_V to '||upper(i.login);
      EXECUTE IMMEDIATE 'grant select on TASKS_V to '||upper(i.login);
      --package
      EXECUTE IMMEDIATE ' GRANT EXECUTE ON API to '||upper(i.login);
    EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('Uzivateli '||i.login||' nebylo nastaveno opravneni');
      --raise_application_error(-20000,'Uzivatel '||i.login||' nema ucet v oracle databazi');
    END;
  END LOOP;
END NASTAV_PRAVA_TABULEK;
PROCEDURE activities_iu(
    inp_id          IN activities.id%type DEFAULT NULL ,
    inp_id_person   IN activities.id_person%type DEFAULT NULL ,
    inp_name        IN activities.name%type DEFAULT NULL ,
    inp_description IN activities.description%type DEFAULT NULL ,
    inp_id_type     IN activities.id_type%type DEFAULT NULL )
AS
  inp_id_row INTEGER;
BEGIN
  IF (inp_id > 0) THEN
    --update
    UPDATE activities a
    SET id_person=inp_id_person ,
      name       =inp_name ,
      description=inp_description ,
      id_type    =inp_id_type
    WHERE a.id   =inp_id;
  ELSE
    -- insert
    SELECT insert_seq.nextval INTO inp_id_row FROM dual;
    INSERT
    INTO activities
      (
        id ,
        id_person ,
        name ,
        description ,
        id_type
      )
      VALUES
      (
        inp_id_row ,
        inp_id_person ,
        inp_name ,
        inp_description ,
        inp_id_type
      );
  END IF;
  COMMIT;
END activities_iu;
PROCEDURE activities_del
  (
    inp_id IN activities.id%type DEFAULT NULL
  )
AS
BEGIN
  DELETE FROM activities WHERE id = inp_id;
  COMMIT;
END activities_del;
PROCEDURE persons_iu(
    inp_id    IN persons.id%type DEFAULT NULL ,
    inp_login IN persons.login%type DEFAULT NULL ,
    inp_fname IN persons.fname%type DEFAULT NULL ,
    inp_sname IN persons.sname%type DEFAULT NULL )
AS
  inp_id_row INTEGER;
BEGIN
  IF (inp_id > 0) THEN
    --update
    UPDATE persons a
    SET login  =inp_login ,
      fname    =inp_fname ,
      sname    =inp_sname
    WHERE a.id =inp_id;
  ELSE
    -- insert
    SELECT insert_seq.nextval INTO inp_id_row FROM dual;
    INSERT
    INTO persons
      (
        id ,
        login ,
        fname ,
        sname ,
        id_type
      )
      VALUES
      (
        inp_id_row ,
        inp_login ,
        inp_fname ,
        inp_sname,
        (SELECT id FROM types WHERE table_name = 'persons' AND code='A'
        )--aktivni
      );
  END IF;
  --nastav prava
  NASTAV_PRAVA_TABULEK();
  COMMIT;
END persons_iu;
PROCEDURE persons_del
  (
    inp_id IN persons.id%type DEFAULT NULL
  )
AS
BEGIN
  UPDATE persons
  SET id_type =
    (SELECT id FROM types WHERE table_name='persons' AND code='N'
    )
  WHERE id = inp_id;
  COMMIT;
END persons_del;
PROCEDURE projects_iu(
    inp_id                IN projects.id%type DEFAULT NULL ,
    inp_id_person         IN projects.id_person%type DEFAULT NULL ,
    inp_name              IN projects.name%type DEFAULT NULL ,
    inp_description       IN projects.description%type DEFAULT NULL ,
    inp_id_project_parent IN projects.id_project_parent%type DEFAULT NULL ,
    inp_id_type           IN projects.id_type%type DEFAULT NULL ,
    out_id OUT projects.id%type )
AS
  l_cnt INTEGER;
BEGIN
  IF (inp_id > 0) THEN
    --kontrola zmeny na dokonceno
    SELECT COUNT(*)
    INTO l_cnt
    FROM types
    WHERE table_name='projects'
    AND code        ='D'
    AND id          =inp_id_type;
    IF (l_cnt       =1) THEN
      --zda jsou vsechnu podukoly a podprojekty dokoncene
      SELECT COUNT(*)
      INTO l_cnt
      FROM tasks_v t
      JOIN types tp
      ON (t.id_type      =tp.id)
      WHERE t.id_project = inp_id
      AND tp.code       <> 'H'; --hotovy
      IF (l_cnt         <>0) THEN
        raise_application_error(-20021,rpad('Projekt obsahuje nedokončené úkoly!',100,chr(10)));
      END IF;
      --zda jsou vsechnu podukoly a podprojekty dokoncene
      SELECT COUNT(*)
      INTO l_cnt
      FROM projects_v t
      JOIN types tp
      ON (t.id_type           =tp.id)
      WHERE id_project_parent = inp_id
      AND tp.code            <> 'D'; --Dokonceny
      IF (l_cnt              <>0) THEN
        raise_application_error(-20021,rpad('Projekt obsahuje nedokončené projekty!',100,chr(10)));
      END IF;
    END IF;
    --kontrola Projekt může změnit pouze vlastník!
    SELECT COUNT(*)
    INTO l_cnt
    FROM persons p,
      projects_v v
    WHERE upper(p.login)=SYS_CONTEXT ('USERENV', 'SESSION_USER')
    AND v.id            =inp_id
    AND p.id            =v.id_person;
    IF (l_cnt           =0) THEN
      raise_application_error(-20021,rpad('Projekt může změnit pouze vlastník!',100,chr(10)));
    END IF;
    UPDATE projects a
    SET id_person       =inp_id_person ,
      name              =inp_name ,
      description       =inp_description ,
      id_type           =inp_id_type,
      id_project_parent = inp_id_project_parent
    WHERE a.id          =inp_id;
    --smaz vsechny cleny skupiny. Java metoda vlozi nove
    DELETE
    FROM members
    WHERE id_project=inp_id;
  ELSE
    -- insert
    SELECT insert_seq.nextval INTO out_id FROM dual;
    INSERT
    INTO projects
      (
        id ,
        id_person ,
        name ,
        description ,
        id_project_parent,
        id_type
      )
      VALUES
      (
        out_id ,
        inp_id_person ,
        inp_name ,
        inp_description ,
        inp_id_project_parent,
        inp_id_type
      );
  END IF;
  COMMIT;
END projects_iu;
PROCEDURE projects_del
  (
    inp_id IN projects.id%type DEFAULT NULL
  )
AS
  l_cnt NUMBER;
BEGIN
  --kontrola Projekt může smazat pouze vlastník!
  SELECT COUNT(*)
  INTO l_cnt
  FROM persons p,
    projects_v v
  WHERE upper(p.login)=SYS_CONTEXT ('USERENV', 'SESSION_USER')
  AND v.id            =inp_id
  AND p.id            =v.id_person;
  IF (l_cnt           =0) THEN
    raise_application_error(-20021,rpad('Projekt může smazat pouze vlastník!',100,chr(10)));
  END IF;
  --Projekt obsahuje aktivní podprojekt!
  SELECT COUNT(*)
  INTO l_cnt
  FROM projects_v v
  WHERE v.ID_PROJECT_PARENT =inp_id;
  IF (l_cnt                <> 0) THEN
    raise_application_error(-20021, rpad('Projekt obsahuje aktivní podprojekt!',100,chr(10)));
  END IF;
  --Zároveň se provede deaktivace všech ůkolů v projektu.
  FOR i IN
  (SELECT id FROM tasks_v WHERE id_project = inp_id
  )
  LOOP
    tasks_del(inp_id);
  END LOOP;
  --deaktivace projektu
  UPDATE projects SET status=1 WHERE id = inp_id;
  COMMIT;
END projects_del;
PROCEDURE tasks_iu(
    inp_id          IN tasks.id%type DEFAULT NULL ,
    inp_id_creator  IN tasks.id_creator%type DEFAULT NULL ,
    inp_id_owner    IN tasks.id_owner%type DEFAULT NULL ,
    inp_name        IN tasks.name%type DEFAULT NULL ,
    inp_description IN tasks.description%type DEFAULT NULL ,
    inp_id_project  IN tasks.id_project%type DEFAULT NULL ,
    inp_id_type     IN tasks.id_type%type DEFAULT NULL ,
    inp_date_from   IN VARCHAR2 DEFAULT NULL ,
    inp_date_to     IN VARCHAR2 DEFAULT NULL )
AS
  inp_id_row INTEGER;
  l_cnt      INTEGER;
  l_cnt2     INTEGER;
BEGIN
  IF (inp_id > 0) THEN
    SELECT COUNT(*)
    INTO l_cnt
    FROM persons p,
      tasks_v v
    WHERE upper(p.login)=SYS_CONTEXT ('USERENV', 'SESSION_USER')
    AND v.id            =inp_id
    AND p.id            =v.id_owner;
    SELECT COUNT(*)
    INTO l_cnt2
    FROM persons p,
      tasks_v v
    JOIN projects_v pr
    ON (v.ID_PROJECT                =pr.id)
    WHERE upper(p.login)            =SYS_CONTEXT ('USERENV', 'SESSION_USER')
    AND v.id                        =inp_id
    AND p.id                        =pr.id_person;
    IF (NVL(l_cnt,0)          +NVL(l_cnt2,0) =0) THEN
      raise_application_error(-20021,rpad('Úkol může upravit pouze vlastník úkolu nebo projektu!',100,chr(10)));
    END IF;
    --END IF;
    --update
    UPDATE tasks a
    SET id_owner  =inp_id_owner,
      name        =inp_name ,
      description =inp_description ,
      id_project  =inp_id_project ,
      id_type     =inp_id_type
    WHERE a.id    =inp_id;
    --interval vznika az po zalozeni ukolu
    IF ( inp_date_from != 'null' AND inp_date_to != 'null' ) THEN
      DELETE FROM intervals WHERE id_task = inp_id;
      INSERT
      INTO intervals
        (
          id_task,
          date_from,
          date_to
        )
        VALUES
        (
          inp_id,
          to_date(inp_date_from,'DD.MM.YYYY'),
          to_date(inp_date_to,'DD.MM.YYYY')
        );
    END IF;
  ELSE
    -- insert
    SELECT insert_seq.nextval INTO inp_id_row FROM dual;
    INSERT
    INTO tasks
      (
        id ,
        id_creator ,
        id_owner ,
        name ,
        description ,
        id_project,
        id_type
      )
      VALUES
      (
        inp_id_row ,
        inp_id_creator ,
        inp_id_owner,
        inp_name ,
        inp_description ,
        CASE
          WHEN (inp_id_project = -1)
          THEN NULL
          ELSE inp_id_project
        END,
        inp_id_type
      );
  END IF;
  COMMIT;
END tasks_iu;
PROCEDURE tasks_del
  (
    inp_id IN tasks.id%type DEFAULT NULL
  )
AS
  l_cnt  INTEGER;
  l_cnt2 INTEGER;
BEGIN
  --DELETE FROM tasks WHERE id = inp_id;
  --kontrola Projekt může smazat pouze vlastník!
  SELECT COUNT(*)
  INTO l_cnt
  FROM persons p,
    tasks_v v
  WHERE upper(p.login)=SYS_CONTEXT ('USERENV', 'SESSION_USER')
  AND v.id            =inp_id
  AND p.id            =v.id_owner;
  SELECT COUNT(*)
  INTO l_cnt2
  FROM persons p,
    tasks_v v
  JOIN projects_v pr
  ON (v.ID_PROJECT                =pr.id)
  WHERE upper(p.login)            =SYS_CONTEXT ('USERENV', 'SESSION_USER')
  AND v.id                        =inp_id
  AND p.id                        =pr.id_person;
  IF (NVL(l_cnt,0)          +NVL(l_cnt2,0) =0) THEN
    raise_application_error(-20021,rpad('Úkol může smazat pouze vlastník úkolu nebo projektu!',100,chr(10)));
  END IF;
  UPDATE tasks SET status=1 WHERE id = inp_id;
  COMMIT;
END tasks_del;
PROCEDURE contexts_iu(
    inp_id        IN contexts.id%type DEFAULT NULL ,
    inp_name      IN contexts.name%type DEFAULT NULL ,
    inp_id_person IN contexts.id_person%type DEFAULT NULL )
AS
  inp_id_row INTEGER;
BEGIN
  IF (inp_id > 0) THEN
    --update
    UPDATE contexts a
    SET name       =inp_name
    WHERE a.id     =inp_id
    AND a.ID_PERSON=inp_id_person;
  ELSE
    -- insert
    SELECT insert_seq.nextval INTO inp_id_row FROM dual;
    INSERT
    INTO contexts
      (
        id ,
        id_person ,
        name
      )
      VALUES
      (
        inp_id_row ,
        inp_id_person ,
        inp_name
      );
  END IF;
  COMMIT;
END contexts_iu;
PROCEDURE contexts_del
  (
    inp_id IN contexts.id%type DEFAULT NULL
  )
AS
BEGIN
  DELETE FROM contexts WHERE id = inp_id;
  COMMIT;
END contexts_del;
PROCEDURE members_iu(
    inp_id_project IN members.id_project%type DEFAULT NULL ,
    inp_id_person  IN members.id_person%type DEFAULT NULL )
AS
  inp_id_row INTEGER;
  l_pocet    INTEGER;
BEGIN
  -- insert, kontrola zda jiz existuje
  SELECT COUNT(*)
  INTO l_pocet
  FROM members
  WHERE id_project=inp_id_project
  AND id_person   =inp_id_person;
  IF ( l_pocet    = 0) THEN
    SELECT insert_seq.nextval INTO inp_id_row FROM dual;
    INSERT
    INTO members
      (
        id_project ,
        id_person
      )
      VALUES
      (
        inp_id_project ,
        inp_id_person
      );
  END IF;
  COMMIT;
END members_iu;
PROCEDURE members_del
  (
    inp_id_project IN members.id_project%type DEFAULT NULL ,
    inp_id_person  IN members.id_person%type DEFAULT NULL
  )
AS
BEGIN
  DELETE
  FROM members
  WHERE id_person = inp_id_person
  AND id_project  = inp_id_project;
  COMMIT;
END members_del;
END API;
/
