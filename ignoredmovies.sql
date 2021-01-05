create table ignoredmovies
(
    ID int auto_increment,
	userID int not null,
	movieID int not null,
    index uID (ID)

);

alter table ignoredmovies
    add primary key (ID);