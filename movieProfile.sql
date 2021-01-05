create table moviesProfiles
(
    userID         int           not null,
    Action         int default 0 not null,
    Adventure      int default 0 not null,
    Animation      int default 0 not null,
    Comedy         int default 0 not null,
    Crime          int default 0 not null,
    Documentary    int default 0 not null,
    Drama          int default 0 not null,
    Family         int default 0 not null,
    Fantasy        int default 0 not null,
    History        int default 0 not null,
    Horror         int default 0 not null,
    Music          int default 0 not null,
    Mystery        int default 0 not null,
    Romance        int default 0 not null,
    ScienceFiction int default 0 not null,
    TVMovie        int default 0 not null,
    Thriller       int default 0 not null,
    War            int default 0 not null,
    Western        int default 0 not null,
    index uID (userID),
    foreign key (userID)
    references users(ID)
    on delete cascade
);


