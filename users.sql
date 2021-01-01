create table users
(
    ID       int          not null auto_increment,
    username varchar(255) not null,
    password varchar(255) not null,
    constraint users_ID_uindex
        unique (ID),
    constraint users_username_uindex
        unique (username)
);

alter table users
    add primary key (ID);

alter table users
	add enabled tinyint default 1 not null;
