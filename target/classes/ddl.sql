CREATE DATABASE crm;

use crm;

CREATE SCHEMA APP;

DROP TABLE  APP.DAY;
DROP TABLE  APP.WEEK;
DROP TABLE  APP.MEAL;
DROP TABLE  APP.MEAL_TYPE;

DROP TABLE  APP.USERS;


   CREATE TABLE APP.USERS
   (
   ID bigint IDENTITY primary key,
   USERNAME varchar(50) unique not null,
   PASSWORD varchar(255) not null,
   IS_ADMIN bit
   )



   CREATE TABLE APP.MEAL_TYPE
   (
   ID bigint IDENTITY primary key,
   TYPE varchar(20) not null,
   )


   CREATE TABLE APP.MEAL
   (
   ID bigint IDENTITY primary key,
   TYPE_ID bigint,
   NAME varchar(100) unique not null,
   MEAL_POSITION varchar(20) not null,
   foreign key (TYPE_ID) references APP.MEAL_TYPE (ID),
   )

   CREATE TABLE APP.WEEK
   (
   ID bigint IDENTITY primary key,
   WEEK_NUMBER int  not null,
   DATE_FROM Date not null,
   DATE_TO Date not null,
   )

   CREATE TABLE APP.DAY
   (
   ID bigint IDENTITY primary key,
   FIRST varchar(100) not null,
   SECOND varchar(100) not null,
   DESSERT varchar(100) not null,
   WEEK_ID bigint not null,
   foreign key (WEEK_ID) references APP.WEEK (ID)
   )