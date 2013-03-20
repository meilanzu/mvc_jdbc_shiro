
create table type (
	id integer NOT NULL UNIQUE,
	name varchar(100) NOT NULL,
	authorId integer NOT NULL,
	raw varchar(4000) NOT NULL
);

alter table type add primary key (id);