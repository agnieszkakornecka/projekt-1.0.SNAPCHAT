USE crm;

SET IDENTITY_INSERT crm.app.meal_type on;

INSERT INTO APP.MEAL_TYPE
	(id, type) values (1,'meat');

insert into  APP.MEAL_TYPE
	(id, type) values (2,'vegan');

insert into  APP.MEAL_TYPE
	(id, type) values (3, 'vegetarian');

insert into  APP.MEAL_TYPE
	(id, type) values (4, 'fish');

insert into  APP.MEAL_TYPE
	(id, type) values (5, 'other');