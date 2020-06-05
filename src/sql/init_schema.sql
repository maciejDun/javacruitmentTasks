create database interview;

\connect interview

create sequence user_id_seq start 1;

create table users (
	id int8 primary key,
	username varchar(40) unique not null,
	email varchar(80) unique not null,
	password varchar(255) not null
);