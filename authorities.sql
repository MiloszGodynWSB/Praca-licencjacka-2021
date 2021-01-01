create table authorities
(
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint authorities_users_username_fk
		foreign key (username) references users (username)
);

create unique index ix_auth_username
	on authorities (username, authority);
