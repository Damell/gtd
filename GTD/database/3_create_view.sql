--aktivity
create or replace view pavlim33.activities_v as
select a.id,a.id_person,p.login,p.fname,p.sname,a.name,a.description,a.id_type,tp.code,tp.name as type_name
from pavlim33.activities a
join pavlim33.PERSONS p on (a.id_person=p.id)
join pavlim33.types tp on (a.id_type=tp.id)
;
grant select on pavlim33.activities_v to chabrdan;

--ukoly
create or replace view pavlim33.tasks_v as
select t.id,t.ID_CREATOR,t.id_owner,p.login,p.fname,p.sname,t.name,t.description,t.ID_TYPE,tp.name as type_name,i.date_from,i.date_to,t.ID_CONTEXT,c.NAME as context_name,
       t.id_project,pr.name as project_name,pr.description as project_description
from pavlim33.tasks t
join pavlim33.PERSONS p on (t.id_owner=p.id)
join pavlim33.TYPES tp on (t.id_type=tp.id)
left join pavlim33.projects pr on (t.id_project=pr.id)
left join pavlim33.INTERVALS i on (t.id=i.id_task and tp.code='K'/*V kalendari*/)
left join pavlim33.CONTEXTS c on (t.ID_CONTEXT=c.id and t.id_owner = c.ID_PERSON)
where t.status=0--aktivni
;
grant select on pavlim33.tasks_v to public;

--projekty
create or replace view pavlim33.projects_v as
select p.id,p.id_person,p.name,p.description,p.ID_TYPE,t.NAME as type_name,p.ID_PROJECT_PARENT
from pavlim33.projects p
join pavlim33.types t on (p.id_type=t.ID)
where p.status=0--aktivni
;
grant select on pavlim33.projects_v to public;

--skupiny osob projektu
create or replace view pavlim33.members_v as
select m.ID_PROJECT,m.ID_PERSON,p.login,p.fname,p.sname,p.ID_TYPE,t.code,t.name as type_name
from pavlim33.members m
join pavlim33.PERSONS p on (m.id_person=p.id)
join pavlim33.types t on (p.id_type=t.id)
where t.code = 'A'
;
grant select on pavlim33.members_v to public;

--osoby
create or replace view pavlim33.persons_v as
select p.id,p.login,p.fname,p.sname
from pavlim33.persons p join pavlim33.types t on (p.id_type=t.id)
where t.code='A'--aktivni
;
grant select on pavlim33.persons_v to public;







