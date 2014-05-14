--Insert data
set echo on
create or replace 
PROCEDURE ZAPNI_CIZI_KLICE AS 
BEGIN
  FOR cur IN (select CONSTRAINT_NAME, TABLE_NAME
             from USER_CONSTRAINTS
       where CONSTRAINT_TYPE = 'R' ) 
  LOOP
   execute immediate 'ALTER TABLE '||cur.table_name||' MODIFY CONSTRAINT "'||cur.constraint_name||'" ENABLE ';
  END LOOP;
END ZAPNI_CIZI_KLICE;
/
create or replace 
PROCEDURE VYPNI_CIZI_KLICE AS 
BEGIN
  for CUR in (select CONSTRAINT_NAME , TABLE_NAME 
       from USER_CONSTRAINTS
       where CONSTRAINT_TYPE = 'R' ) 
   LOOP
    execute immediate 'ALTER TABLE '||cur.table_name||' MODIFY CONSTRAINT "'||cur.constraint_name||'" DISABLE ';
   END LOOP;
END VYPNI_CIZI_KLICE;
/
create or replace 
PROCEDURE VYMAZ_DATA_VSECH_TABULEK IS
BEGIN
  VYPNI_CIZI_KLICE;
  FOR V_REC IN (SELECT DISTINCT(TABLE_NAME) FROM USER_TABLES)
      LOOP
      EXECUTE IMMEDIATE 'truncate table '||V_REC.TABLE_NAME||' drop storage';
      END LOOP;
  ZAPNI_CIZI_KLICE;
END VYMAZ_DATA_VSECH_TABULEK;
/
create or replace 
PROCEDURE NASTAV_PRAVA_TABULEK IS
BEGIN
FOR t in (select table_name from user_tables) loop
  FOR i IN (SELECT login FROM persons) loop
    BEGIN
    EXECUTE IMMEDIATE 'grant select, insert, update, delete on '||t.table_name||' to '||upper(i.login);
    exception
    WHEN others THEN
      dbms_output.put_line('Uzivateli '||i.login||' nebylo nastaveno opravneni');--raise_application_error(-20000,'Uzivatel '||i.login||' nema ucet v oracle databazi');
    END;
  END loop;
END loop;
END NASTAV_PRAVA_TABULEK;
/

set echo on;
set autocommit on;

execute VYMAZ_DATA_VSECH_TABULEK();

set autocommit on;

SET ECHO ON;
SET DEFINE OFF;


--typy zaznamu
--Osoba
Insert into types (id,table_name,code,name,description) values (insert_seq.nextval,'persons','A','Aktivní','Aktivní účet');
Insert into types (id,table_name,code,name,description) values (insert_seq.nextval,'persons','N','Neaktivní','Zablokovaný účet');
--Cinnost
Insert into types (id,table_name,code,name,description) values (insert_seq.nextval,'activities','K','Ke zpracování','Činnost, která čeká na zpracování.');
Insert into types (id,table_name,code,name,description) values (insert_seq.nextval,'activities','H','Zahozena ','S provedením činnosti se v budoucnu nepočítá, uživatel nechce uchovat informace.');
Insert into types (id,table_name,code,name,description) values (insert_seq.nextval,'activities','A','Archivována','Činnost je vyhodnocena jako nerealizovatelná a v budoucnu se s její realizací nepočítá. Zůstává přístup k informacím o činnosti.');
Insert into types (id,table_name,code,name,description) values (insert_seq.nextval,'activities','O','Odložena','Činnost bude možná v budoucnu provedena, ale v tuto chvíli je vyhodnocena jako Nerealizovatelná.');
Insert into types (id,table_name,code,name,description) values (insert_seq.nextval,'activities','Z','Zpracovaná','Činnost je zpracována.');
--Kontakt
Insert into types (id,table_name,code,name,description) values (insert_seq.nextval,'contacts','E','E-mail','Elektronická adresa');
Insert into types (id,table_name,code,name,description) values (insert_seq.nextval,'contacts','T','Telefon','Telefonní číslo');
--Projekt
Insert into types (id,table_name,code,name,description) values (insert_seq.nextval,'projects','A','Aktivní','Projekt je aktivní');
Insert into types (id,table_name,code,name,description) values (insert_seq.nextval,'projects','D','Dokončený','Projek nemůže být dokončen pokud je pod ním neukončený projekt nebo úkol.');
--Ukol 
Insert into types (id,table_name,code,name,description) values (insert_seq.nextval,'tasks','V','Vytvořený','Nově vytvořený úkol, kterému bude přidělen nový stav na základě dalšího zpracování');
Insert into types (id,table_name,code,name,description) values (insert_seq.nextval,'tasks','A','Aktivní','Aktuálně zpracovávaný úkol bez časového rámce');
Insert into types (id,table_name,code,name,description) values (insert_seq.nextval,'tasks','K','V kalendáři','Aktuálně zpracovávaný úkol s časovým rámcem');
Insert into types (id,table_name,code,name,description) values (insert_seq.nextval,'tasks','H','Hotový','Kompletně zpracovaný úkol');

--Persons
Insert into persons (id,login,fname,sname,id_type) values (insert_seq.nextval,'pavlim33','Michal','Pavlina',(select id from types where table_name='persons' and code='A'));
Insert into persons (id,login,fname,sname,id_type) values (insert_seq.nextval,'chabrdan','Daniel','Chabr',(select id from types where table_name='persons' and code='A'));
Insert into persons (id,login,fname,sname,id_type) values (insert_seq.nextval,'steklsim','Šimon','Steklík',(select id from types where table_name='persons' and code='A'));
Insert into persons (id,login,fname,sname,id_type) values (insert_seq.nextval,'skvarjur','Juraj','Škvarla',(select id from types where table_name='persons' and code='A'));
Insert into persons (id,login,fname,sname,id_type) values (insert_seq.nextval,'andelja4','Jan','Anděl',(select id from types where table_name='persons' and code='A'));
Insert into persons (id,login,fname,sname,id_type) values (insert_seq.nextval,'slamamic','Michal','Sláma',(select id from types where table_name='persons' and code='A'));

--kontexty
Insert into contexts (id,id_person,name) values (insert_seq.nextval,(select id from persons where login='pavlim33'),'ctxpavlina1');
Insert into contexts (id,id_person,name) values (insert_seq.nextval,(select id from persons where login='pavlim33'),'ctxpavlina2');
Insert into contexts (id,id_person,name) values (insert_seq.nextval,(select id from persons where login='andelja4'),'ctxandel1');

--activies
Insert into activities (id,id_person,name,description	,id_type) values (insert_seq.nextval,(select id from persons where login='pavlim33'),'Připravit DB model','Vytvořit databázové schéma pro projekt GTD.',(select id from types where table_name='activities' and code='K'));
Insert into activities (id,id_person,name,description	,id_type) values (insert_seq.nextval,(select id from persons where login='chabrdan'),'Připravit Java aplikaci GTD','Vytvořit uživatelské rozhraní aplikace pro projekt GTD.',(select id from types where table_name='activities' and code='K'));
Insert into activities (id,id_person,name,description	,id_type) values (insert_seq.nextval,(select id from persons where login='andelja4'),'Připravit analůytickou dokumentaci','Připravit analýzu pro projekt GTD.',(select id from types where table_name='activities' and code='K'));

--contacts
Insert into contacts (id,id_person	,value,id_type) values (insert_seq.nextval,(select id from persons where login='pavlim33'),'pavlim33@fit.cvut.cz',(select id from types where table_name='contacts' and code='E'));
Insert into contacts (id,id_person	,value,id_type) values (insert_seq.nextval,(select id from persons where login='andelja4'),'andelja4@fit.cvut.cz',(select id from types where table_name='contacts' and code='E'));
Insert into contacts (id,id_person	,value,id_type) values (insert_seq.nextval,(select id from persons where login='andelja4'),'777 777 777',(select id from types where table_name='contacts' and code='T'));

/*
--projects
Insert into projects (id,name,description,id_person,id_type,id_project_parent) values (insert_seq.nextval,'main','Hlavní projekt. id_project_parent si null',(select id from persons where login='pavlim33'),(select id from types where table_name='projects' and code='A'),null);
Insert into projects (id,name,description,id_person,id_type,id_project_parent) values (insert_seq.nextval,'GTD','GTD – Getting Things Done. Podrobný popis.',(select id from persons where login='slamamic'),(select id from types where table_name='projects' and code='A'),(select id from projects where name='main'));
Insert into projects (id,name,description,id_person,id_type,id_project_parent) values (insert_seq.nextval,'Test','Pokus. Navazuje na GTD.',(select id from persons where login='pavlim33'),(select id from types where table_name='projects' and code='A'),(select id from projects where name='GTD'));
Insert into projects (id,name,description,id_person,id_type,id_project_parent) values (insert_seq.nextval,'Zahrada','Posekat zahradu',(select id from persons where login='steklsim'),(select id from types where table_name='projects' and code='A'),(select id from projects where name='main'));


--members
Insert into members (id_project,id_person) values ((select id from projects where name='GTD'),(select id from persons where login='pavlim33'));
Insert into members (id_project,id_person) values ((select id from projects where name='GTD'),(select id from persons where login='chabrdan'));
Insert into members (id_project,id_person) values ((select id from projects where name='GTD'),(select id from persons where login='steklsim'));
Insert into members (id_project,id_person) values ((select id from projects where name='GTD'),(select id from persons where login='skvarjur'));
Insert into members (id_project,id_person) values ((select id from projects where name='GTD'),(select id from persons where login='andelja4'));
Insert into members (id_project,id_person) values ((select id from projects where name='GTD'),(select id from persons where login='slamamic'));
Insert into members (id_project,id_person) values ((select id from projects where name='Test'),(select id from persons where login='pavlim33'));

--tasks
Insert into tasks (id,id_creator,id_owner,id_context,id_project,name,description,id_type) values (insert_seq.nextval,(select id from persons where login='slamamic'),  (select id from persons where login='slamamic'),  null,(select id from projects where name='GTD'),'Vedení projektu','popis',(select id from types where table_name='tasks' and code='K'));
Insert into tasks (id,id_creator,id_owner,id_context,id_project,name,description,id_type) values (insert_seq.nextval,(select id from persons where login='andelja4'),  (select id from persons where login='andelja4'),  null,(select id from projects where name='GTD'),'Vytvořit analytickou dokumentaci','popis',(select id from types where table_name='tasks' and code='A'));
Insert into tasks (id,id_creator,id_owner,id_context,id_project,name,description,id_type) values (insert_seq.nextval,(select id from persons where login='steklsim'),(select id from persons where login='steklsim'),null,(select id from projects where name='GTD'),'Navrhnout doménový model','popis',(select id from types where table_name='tasks' and code='A'));
Insert into tasks (id,id_creator,id_owner,id_context,id_project,name,description,id_type) values (insert_seq.nextval,(select id from persons where login='andelja4'),(select id from persons where login='pavlim33'),(select id from contexts where name='ctxpavlina1'),(select id from projects where name='Test'),'Test ukolu v podprojektu s kontextem','popis',(select id from types where table_name='tasks' and code='V'));
--test pouziti kontextu jeineho uzivatele
--Insert into tasks (id,id_creator,id_owner,id_context,id_project,name,description,id_type) values (insert_seq.nextval,(select id from persons where login='andelja4'),(select id from persons where login='pavlina'),(select id from contexts where name='ctxandelja41'),(select id from projects where name='Test'),'Test ukolu v podprojektu s kontextem jineho uzivatele','popis',(select id from types where table_name='tasks' and code='V'));

--Intervals
Insert into intervals (id_task,date_from,date_to) values ((select id from tasks where name='Vedení projektu'),to_date('20140301','YYYYMMDD'),to_date('20140601','YYYYMMDD'));
*/
execute NASTAV_PRAVA_TABULEK();

