USE crm;

SET IDENTITY_INSERT crm.app.users on;
  --- haslo Projekt
INSERT INTO APP.USERS
	(id, username, password, is_admin) values (1, 'Aga', '-847943-110-33-979981-8970-39-1-118-92641',1);