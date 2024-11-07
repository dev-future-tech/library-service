create table books (
    book_id bigint not null generated always as identity,
    title varchar(120),
    author varchar(120),
    genre varchar(55),
    publisher varchar(40),
    primary key (book_id)
);

create table borrowed_books (
    user_id bigint not null,
    book_id bigint not null,
    primary key (user_id, book_id)
);