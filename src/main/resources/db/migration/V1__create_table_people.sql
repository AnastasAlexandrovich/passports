create table if not exists people
(
    id            bigint not null
        constraint people_pkey
            primary key,
    date_of_birth varchar(255),
    name          varchar(255),
    patronymic    varchar(255),
    sex           varchar(255),
    surname       varchar(255)
);
