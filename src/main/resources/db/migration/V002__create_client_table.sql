create table client(
    id bigint not null auto_increment,
    name varchar(50) not null,
    email varchar(50) not null,
    primary key(id)
);

insert into client values(1, 'Elis', 'elis@gmail.com');
