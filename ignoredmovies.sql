create table ignoredmovies
(
	userID int not null,
	movieID int not null,
    index uID (userID),
                                   foreign key (userID)
                                        references users(ID)
                                        on delete cascade
);