create table answer(
    id bigint not null auto_increment,
    message varchar(300) not null,
    creation_date datetime,
    topic_id bigint not null,
    author_id bigint not null,
    solution boolean not null,
    primary key(id),
    foreign key(topic_id) references topic(id),
    foreign key(author_id) references client(id)
);
