--Insert data

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
commit;