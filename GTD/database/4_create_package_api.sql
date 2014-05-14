CREATE OR REPLACE PACKAGE API
AS
  /*
  * Nastaveni prav po vlozeni nove osoby
  */
  PROCEDURE NASTAV_PRAVA_TABULEK;
  /*
  * Vlozeni/oprava aktivity
  */
  PROCEDURE activities_iu(
      inp_id          IN activities.id%type DEFAULT NULL ,
      inp_id_person   IN activities.id_person%type DEFAULT NULL ,
      inp_name        IN activities.name%type DEFAULT NULL ,
      inp_description IN activities.description%type DEFAULT NULL ,
      inp_id_type     IN activities.id_type%type DEFAULT NULL ,
      auto_commit     IN BOOLEAN DEFAULT true );
  /*
  * Smazani aktivity
  */
  PROCEDURE activities_del(
      inp_id      IN activities.id%type DEFAULT NULL ,
      auto_commit IN BOOLEAN DEFAULT true );
  /*
  * Vlozeni/oprava osoby
  */
  PROCEDURE persons_iu(
      inp_id      IN persons.id%type DEFAULT NULL ,
      inp_login   IN persons.login%type DEFAULT NULL ,
      inp_fname   IN persons.fname%type DEFAULT NULL ,
      inp_sname   IN persons.sname%type DEFAULT NULL ,
      auto_commit IN BOOLEAN DEFAULT true );
  /*
  * Deaktivace osoby
  */
  PROCEDURE persons_del(
      inp_id      IN persons.id%type DEFAULT NULL ,
      auto_commit IN BOOLEAN DEFAULT true );
  /*
  * Vlozeni/oprava projektu
  */
  PROCEDURE projects_iu(
      inp_id                IN projects.id%type DEFAULT NULL ,
      inp_id_person         IN projects.id_person%type DEFAULT NULL ,
      inp_name              IN projects.name%type DEFAULT NULL ,
      inp_description       IN projects.description%type DEFAULT NULL ,
      inp_id_project_parent IN projects.id_project_parent%type DEFAULT NULL ,
      inp_id_type           IN projects.id_type%type DEFAULT NULL ,
      out_id OUT projects.id%type,
      auto_commit IN BOOLEAN DEFAULT true );
  /*
  * Deaktivace projektu
  */
  PROCEDURE projects_del(
      inp_id      IN projects.id%type DEFAULT NULL ,
      auto_commit IN BOOLEAN DEFAULT true );
  /*
  * Vlozeni/oprava ukolu
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
      inp_date_to     IN VARCHAR2 DEFAULT NULL ,
      auto_commit     IN BOOLEAN DEFAULT true );
  /*
  * Deaktivace ukolu
  */
  PROCEDURE tasks_del(
      inp_id      IN tasks.id%type DEFAULT NULL ,
      auto_commit IN BOOLEAN DEFAULT true );
  /*
  * Vlozeni/oprava kontextu
  */
  PROCEDURE contexts_iu(
      inp_id        IN contexts.id%type DEFAULT NULL ,
      inp_name      IN contexts.name%type DEFAULT NULL ,
      inp_id_person IN contexts.id_person%type DEFAULT NULL ,
      auto_commit   IN BOOLEAN DEFAULT true );
  /*
  * Smazani kontextu
  */
  PROCEDURE contexts_del(
      inp_id      IN contexts.id%type DEFAULT NULL ,
      auto_commit IN BOOLEAN DEFAULT true );
  /*
  * Vlozeni clena projektu
  */
  PROCEDURE members_iu(
      inp_id_project IN members.id_project%type DEFAULT NULL ,
      inp_id_person  IN members.id_person%type DEFAULT NULL ,
      auto_commit    IN BOOLEAN DEFAULT true );
  /*
  * Smazani clena projektu
  */
  PROCEDURE members_del(
      inp_id_project IN members.id_project%type DEFAULT NULL ,
      inp_id_person  IN members.id_person%type DEFAULT NULL ,
      auto_commit    IN BOOLEAN DEFAULT true );
END API;
/
CREATE OR REPLACE PACKAGE BODY API
AS
PROCEDURE NASTAV_PRAVA_TABULEK
IS
BEGIN
  FOR t IN
  (SELECT table_name FROM user_tables
  )
  LOOP
    FOR i IN
    (SELECT login FROM pavlim33.persons_v
    )
    LOOP
      BEGIN
        EXECUTE IMMEDIATE 'grant select, insert, update, delete on '||t.table_name||' to '||upper(i.login);
      EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('Uzivateli '||i.login||' nebylo nastaveno opravneni');--raise_application_error(-20000,'Uzivatel '||i.login||' nema ucet v oracle databazi');
      END;
    END LOOP;
  END LOOP;
END NASTAV_PRAVA_TABULEK;
PROCEDURE activities_iu(
    inp_id          IN activities.id%type DEFAULT NULL ,
    inp_id_person   IN activities.id_person%type DEFAULT NULL ,
    inp_name        IN activities.name%type DEFAULT NULL ,
    inp_description IN activities.description%type DEFAULT NULL ,
    inp_id_type     IN activities.id_type%type DEFAULT NULL ,
    auto_commit     IN BOOLEAN DEFAULT true )
AS
  inp_id_row INTEGER;
BEGIN
  IF (inp_id > 0) THEN
    --update
    UPDATE pavlim33.activities a
    SET id_person=inp_id_person ,
      name       =inp_name ,
      description=inp_description ,
      id_type    =inp_id_type
    WHERE a.id   =inp_id;
  ELSE
    -- insert
    SELECT pavlim33.insert_seq.nextval INTO inp_id_row FROM dual;
    INSERT
    INTO pavlim33.activities
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
  IF auto_commit THEN
    COMMIT;
  END IF;
END activities_iu;
PROCEDURE activities_del
  (
    inp_id      IN activities.id%type DEFAULT NULL ,
    auto_commit IN BOOLEAN DEFAULT true
  )
AS
BEGIN
  DELETE FROM pavlim33.activities WHERE id = inp_id;
  IF auto_commit THEN
    COMMIT;
  END IF;
END activities_del;
PROCEDURE persons_iu(
    inp_id      IN persons.id%type DEFAULT NULL ,
    inp_login   IN persons.login%type DEFAULT NULL ,
    inp_fname   IN persons.fname%type DEFAULT NULL ,
    inp_sname   IN persons.sname%type DEFAULT NULL ,
    auto_commit IN BOOLEAN DEFAULT true )
AS
  inp_id_row INTEGER;
BEGIN
  IF (inp_id > 0) THEN
    --update
    UPDATE pavlim33.persons a
    SET login  =inp_login ,
      fname    =inp_fname ,
      sname    =inp_sname
    WHERE a.id =inp_id;
  ELSE
    -- insert
    SELECT pavlim33.insert_seq.nextval INTO inp_id_row FROM dual;
    INSERT
    INTO pavlim33.persons
      (
        id ,
        login ,
        fname ,
        sname
      )
      VALUES
      (
        inp_id_row ,
        inp_login ,
        inp_fname ,
        inp_sname
      );
  END IF;
  --nastav prava
  NASTAV_PRAVA_TABULEK();
  IF auto_commit THEN
    COMMIT;
  END IF;
END persons_iu;
PROCEDURE persons_del
  (
    inp_id      IN persons.id%type DEFAULT NULL ,
    auto_commit IN BOOLEAN DEFAULT true
  )
AS
BEGIN
  UPDATE pavlim33.persons
  SET id_type =
    (SELECT id FROM pavlim33.types WHERE table_name='persons' AND code='N'
    )
  WHERE id = inp_id;
  IF auto_commit THEN
    COMMIT;
  END IF;
END persons_del;
PROCEDURE projects_iu(
    inp_id                IN projects.id%type DEFAULT NULL ,
    inp_id_person         IN projects.id_person%type DEFAULT NULL ,
    inp_name              IN projects.name%type DEFAULT NULL ,
    inp_description       IN projects.description%type DEFAULT NULL ,
    inp_id_project_parent IN projects.id_project_parent%type DEFAULT NULL ,
    inp_id_type           IN projects.id_type%type DEFAULT NULL ,
    out_id OUT projects.id%type,
    auto_commit IN BOOLEAN DEFAULT true )
AS
  l_cnt INTEGER;
BEGIN
  IF (inp_id > 0) THEN
    --kontrola zmeny na dokonceno
    SELECT COUNT(*)
    INTO l_cnt
    FROM pavlim33.types
    WHERE table_name='projects'
    AND code        ='D'
    AND id          =inp_id_type;
    IF (l_cnt       =1) THEN
      --zda jsou vsechnu podukoly a podprojekty dokoncene
      SELECT COUNT(*)
      INTO l_cnt
      FROM pavlim33.tasks_v t
      JOIN pavlim33.types tp
      ON (t.id_type      =tp.id)
      WHERE t.id_project = inp_id
      AND tp.code       <> 'H'; --hotovy
      IF (l_cnt         <>0) THEN
        raise_application_error(-20021,rpad('Projekt obsahuje nedokončené úkoly!',100,chr(10)));
      END IF;
      --zda jsou vsechnu podukoly a podprojekty dokoncene
      SELECT COUNT(*)
      INTO l_cnt
      FROM pavlim33.projects_v t
      JOIN pavlim33.types tp
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
    FROM pavlim33.persons p,
      pavlim33.projects_v v
    WHERE upper(p.login)=SYS_CONTEXT ('USERENV', 'SESSION_USER')
    AND v.id            =inp_id
    AND p.id            =v.id_person;
    IF (l_cnt           =0) THEN
      raise_application_error(-20021,rpad('Projekt může změnit pouze vlastník!',100,chr(10)));
    END IF;
    UPDATE pavlim33.projects a
    SET id_person       =inp_id_person ,
      name              =inp_name ,
      description       =inp_description ,
      id_type           =inp_id_type,
      id_project_parent = inp_id_project_parent
    WHERE a.id          =inp_id;
    --smaz vsechny cleny skupiny. Java metoda vlozi nove
    DELETE
    FROM pavlim33.members
    WHERE id_project=inp_id;
  ELSE
    -- insert
    SELECT pavlim33.insert_seq.nextval INTO out_id FROM dual;
    INSERT
    INTO pavlim33.projects
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
  IF auto_commit THEN
    COMMIT;
  END IF;
END projects_iu;
PROCEDURE projects_del
  (
    inp_id      IN projects.id%type DEFAULT NULL ,
    auto_commit IN BOOLEAN DEFAULT true
  )
AS
  l_cnt NUMBER;
BEGIN
  --kontrola Projekt může smazat pouze vlastník!
  SELECT COUNT(*)
  INTO l_cnt
  FROM pavlim33.persons p,
    pavlim33.projects_v v
  WHERE upper(p.login)=SYS_CONTEXT ('USERENV', 'SESSION_USER')
  AND v.id            =inp_id
  AND p.id            =v.id_person;
  IF (l_cnt           =0) THEN
    raise_application_error(-20021,rpad('Projekt může smazat pouze vlastník!',100,chr(10)));
  END IF;
  --Projekt obsahuje aktivní podprojekt!
  SELECT COUNT(*)
  INTO l_cnt
  FROM pavlim33.projects_v v
  WHERE v.ID_PROJECT_PARENT =inp_id;
  IF (l_cnt                <> 0) THEN
    raise_application_error(-20021, rpad('Projekt obsahuje aktivní podprojekt!',100,chr(10)));
  END IF;
  --Zároveň se provede deaktivace všech ůkolů v projektu.
  FOR i IN
  (SELECT id FROM pavlim33.tasks_v WHERE id_project = inp_id
  )
  LOOP
    tasks_del(inp_id);
  END LOOP;
  --deaktivace projektu
  UPDATE pavlim33.projects SET status=1 WHERE id = inp_id;
  IF auto_commit THEN
    COMMIT;
  END IF;
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
    inp_date_to     IN VARCHAR2 DEFAULT NULL ,
    auto_commit     IN BOOLEAN DEFAULT true )
AS
  inp_id_row INTEGER;
  l_cnt      INTEGER;
  l_cnt2     INTEGER;
BEGIN
  IF (inp_id > 0) THEN
    SELECT COUNT(*)
    INTO l_cnt
    FROM pavlim33.persons p,
      pavlim33.tasks_v v
    WHERE upper(p.login)=SYS_CONTEXT ('USERENV', 'SESSION_USER')
    AND v.id            =inp_id
    AND p.id            =v.id_owner;
    SELECT COUNT(*)
    INTO l_cnt2
    FROM pavlim33.persons p,
      pavlim33.tasks_v v
    JOIN pavlim33.projects_v pr
    ON (v.ID_PROJECT                =pr.id)
    WHERE upper(p.login)            =SYS_CONTEXT ('USERENV', 'SESSION_USER')
    AND v.id                        =inp_id
    AND p.id                        =pr.id_person;
    IF (NVL(l_cnt,0)          +NVL(l_cnt2,0) =0) THEN
      raise_application_error(-20021,rpad('Úkol může upravit pouze vlastník úkolu nebo projektu!',100,chr(10)));
    END IF;
    --END IF;
    --update
    UPDATE pavlim33.tasks a
    SET id_owner  =inp_id_owner,
      name        =inp_name ,
      description =inp_description ,
      id_project  =inp_id_project ,
      id_type     =inp_id_type
    WHERE a.id    =inp_id;
    --interval vznika az po zalozeni ukolu
    IF ( inp_date_from != 'null' AND inp_date_to != 'null' ) THEN
      DELETE FROM pavlim33.intervals WHERE id_task = inp_id;
      INSERT
      INTO pavlim33.intervals
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
    SELECT pavlim33.insert_seq.nextval INTO inp_id_row FROM dual;
    INSERT
    INTO pavlim33.tasks
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
  IF auto_commit THEN
    COMMIT;
  END IF;
END tasks_iu;
PROCEDURE tasks_del
  (
    inp_id      IN tasks.id%type DEFAULT NULL ,
    auto_commit IN BOOLEAN DEFAULT true
  )
AS
  l_cnt  INTEGER;
  l_cnt2 INTEGER;
BEGIN
  --DELETE FROM pavlim33.tasks WHERE id = inp_id;
  --kontrola Projekt může smazat pouze vlastník!
  SELECT COUNT(*)
  INTO l_cnt
  FROM pavlim33.persons p,
    pavlim33.tasks_v v
  WHERE upper(p.login)=SYS_CONTEXT ('USERENV', 'SESSION_USER')
  AND v.id            =inp_id
  AND p.id            =v.id_owner;
  SELECT COUNT(*)
  INTO l_cnt2
  FROM pavlim33.persons p,
    pavlim33.tasks_v v
  JOIN pavlim33.projects_v pr
  ON (v.ID_PROJECT                =pr.id)
  WHERE upper(p.login)            =SYS_CONTEXT ('USERENV', 'SESSION_USER')
  AND v.id                        =inp_id
  AND p.id                        =pr.id_person;
  IF (NVL(l_cnt,0)          +NVL(l_cnt2,0) =0) THEN
    raise_application_error(-20021,rpad('Úkol může smazat pouze vlastník úkolu nebo projektu!',100,chr(10)));
  END IF;
  UPDATE pavlim33.tasks SET status=1 WHERE id = inp_id;
  IF auto_commit THEN
    COMMIT;
  END IF;
END tasks_del;
PROCEDURE contexts_iu(
    inp_id        IN contexts.id%type DEFAULT NULL ,
    inp_name      IN contexts.name%type DEFAULT NULL ,
    inp_id_person IN contexts.id_person%type DEFAULT NULL ,
    auto_commit   IN BOOLEAN DEFAULT true )
AS
  inp_id_row INTEGER;
BEGIN
  IF (inp_id > 0) THEN
    --update
    UPDATE pavlim33.contexts a
    SET name       =inp_name
    WHERE a.id     =inp_id
    AND a.ID_PERSON=inp_id_person;
  ELSE
    -- insert
    SELECT pavlim33.insert_seq.nextval INTO inp_id_row FROM dual;
    INSERT
    INTO pavlim33.contexts
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
  IF auto_commit THEN
    COMMIT;
  END IF;
END contexts_iu;
PROCEDURE contexts_del
  (
    inp_id      IN contexts.id%type DEFAULT NULL ,
    auto_commit IN BOOLEAN DEFAULT true
  )
AS
BEGIN
  DELETE FROM pavlim33.contexts WHERE id = inp_id;
  IF auto_commit THEN
    COMMIT;
  END IF;
END contexts_del;
PROCEDURE members_iu(
    inp_id_project IN members.id_project%type DEFAULT NULL ,
    inp_id_person  IN members.id_person%type DEFAULT NULL ,
    auto_commit    IN BOOLEAN DEFAULT true )
AS
  inp_id_row INTEGER;
  l_pocet    INTEGER;
BEGIN
  -- insert, kontrola zda jiz existuje
  SELECT COUNT(*)
  INTO l_pocet
  FROM pavlim33.members
  WHERE id_project=inp_id_project
  AND id_person   =inp_id_person;
  IF ( l_pocet    = 0) THEN
    SELECT pavlim33.insert_seq.nextval INTO inp_id_row FROM dual;
    INSERT
    INTO pavlim33.members
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
  IF auto_commit THEN
    COMMIT;
  END IF;
END members_iu;
PROCEDURE members_del
  (
    inp_id_project IN members.id_project%type DEFAULT NULL ,
    inp_id_person  IN members.id_person%type DEFAULT NULL ,
    auto_commit    IN BOOLEAN DEFAULT true
  )
AS
BEGIN
  DELETE
  FROM pavlim33.members
  WHERE id_person = inp_id_person
  AND id_project  = inp_id_project;
  IF auto_commit THEN
    COMMIT;
  END IF;
END members_del;
END API;
/
GRANT EXECUTE ON PAVLIM33.API TO PUBLIC;
