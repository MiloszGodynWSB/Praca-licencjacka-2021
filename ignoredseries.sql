-- auto-generated definition
create table ignoredseries
(
    ID int auto_increment,
    userID  int not null,
    movieID int not null,
    index uID (ID)
);

alter table ignoredseries
    add primary key (ID);

