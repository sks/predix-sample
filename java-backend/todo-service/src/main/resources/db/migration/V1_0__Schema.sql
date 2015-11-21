create SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;
create table todos (id serial not null, title varchar(255),completed boolean, primary key (id));