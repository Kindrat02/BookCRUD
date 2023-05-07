create table book(
    id serial,
    title varchar,
    author varchar,
    publish_year int,
    genre varchar,
    constraint pk_book primary key (id)
);