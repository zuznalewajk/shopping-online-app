create table users
(
    id int primary key auto_increment,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    email varchar(100) not null,
    password varchar(100) not null,
    role enum('USER', 'ADMIN') not null
);
