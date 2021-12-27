create table if not exists passports
(
    id               bigint not null
        constraint passports_pkey
            primary key,
    authority        varchar(255),
    date_of_expiry   varchar(255),
    date_of_issue    varchar(255),
    document_number  varchar(255),
    personal_number  varchar(255),
    status           varchar(255),
    type_of_passport varchar(255),
    people_id        bigint
        constraint fkl5rffo02v0ck9hasril7q3r9p
            references people
);