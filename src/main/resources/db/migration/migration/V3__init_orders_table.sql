create table orders
(
    id int primary key auto_increment,
    user_id int not null,
    foreign key (user_id) references users(id)
);
