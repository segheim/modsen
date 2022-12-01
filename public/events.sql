create table events
(
	id serial
		constraint events_pk
			primary key,
	e_topic varchar(50) not null,
	e_description varchar(100) not null,
	e_organizer varchar(50) not null,
	e_time timestamp not null,
	e_location varchar(50) not null
);

alter table events owner to postgres;

create unique index events_id_uindex
	on events (id);

