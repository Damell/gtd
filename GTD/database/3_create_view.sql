--aktivity
create or replace view activities_v as
select a.id,a.id_person,p.login,p.fname,p.sname,a.name,a.description,a.id_type,tp.code,tp.name as type_name
from activities a
join PERSONS p on (a.id_person=p.id)
join types tp on (a.id_type=tp.id)
;
--ukoly
create or replace view tasks_v as
select t.id,t.ID_CREATOR,t.id_owner,p.login,p.fname,p.sname,t.name,t.description,t.ID_TYPE,tp.name as type_name,i.date_from,i.date_to,t.ID_CONTEXT,c.NAME as context_name,
       t.id_project,pr.name as project_name,pr.description as project_description
from tasks t
join PERSONS p on (t.id_owner=p.id)
join TYPES tp on (t.id_type=tp.id)
left join projects pr on (t.id_project=pr.id)
left join INTERVALS i on (t.id=i.id_task and tp.code='K'/*V kalendari*/)
left join CONTEXTS c on (t.ID_CONTEXT=c.id and t.id_owner = c.ID_PERSON)
where t.status=0--aktivni
;
--projekty
create or replace view projects_v as
select p.id,p.id_person,p.name,p.description,p.ID_TYPE,t.NAME as type_name,p.ID_PROJECT_PARENT
from projects p
join types t on (p.id_type=t.ID)
where p.status=0--aktivni
;
--skupiny osob projektu
create or replace view members_v as
select m.ID_PROJECT,m.ID_PERSON,p.login,p.fname,p.sname,p.ID_TYPE,t.code,t.name as type_name
from members m
join PERSONS p on (m.id_person=p.id)
join types t on (p.id_type=t.id)
where t.code = 'A'
;
--osoby
create or replace view persons_v as
select p.id,p.login,p.fname,p.sname
from persons p join types t on (p.id_type=t.id)
where t.code='A'--aktivni
;
