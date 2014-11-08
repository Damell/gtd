INSERT INTO `type` (`type`, `id`, `kod`, `nazev`, `popis`) VALUES
('ActivityState', 1, 'K', 'Ke zpracování', 'Činnost, která čeká na zpracování.'),
('ActivityState', 2, 'H', 'Zahozena', 'S provedením činnosti se v budoucnu nepočítá, uživatel nechce uchovat informace.'),
('ActivityState', 3, 'A', 'Archivována', 'Činnost je vyhodnocena jako nerealizovatelná a v budoucnu se s její realizací nepočítá. Zůstává přístup k informacím o činnosti.'),
('ActivityState', 4, 'O', 'Odložena', 'Činnost bude možná v budoucnu provedena, ale v tuto chvíli je vyhodnocena jako Nerealizovatelná.'),
('ActivityState', 5, 'Z', 'Zpracována', 'Činnost je zpracována.'),
('PersonState', 6, 'A', 'Aktivní', 'Aktivní účet'),
('PersonState', 7, 'N', 'Neaktivní', 'Zablokovaný účet'),
('ContactType', 8, 'E', 'Email', 'E-mailová adresa'),
('ContactType', 9, 'T', 'Telefon', 'Telefonní číslo'),
('ProjectState', 10, 'A','Aktivní','Projekt je aktivní'),
('ProjectState', 11, 'D','Dokončený','Projek nemůže být dokončen pokud je pod ním neukončený projekt nebo úkol.'),
('TaskState', 12, 'V','Vytvořený','Nově vytvořený úkol, kterému bude přidělen nový stav na základě dalšího zpracování'),
('TaskState', 13, 'A','Aktivní','Aktuálně zpracovávaný úkol bez časového rámce'),
('TaskState', 14, 'K','V kalendáři','Aktuálně zpracovávaný úkol s časovým rámcem'),
('TaskState', 15, 'H','Hotový','Kompletně zpracovaný úkol');

