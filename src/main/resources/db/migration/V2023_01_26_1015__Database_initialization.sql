create table book(
    id serial,
    title varchar,
    author varchar,
    publish_date date,
    genre varchar,
    constraint pk_book primary key (id)
);