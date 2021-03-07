create table products
(
    id int primary key auto_increment,
    name varchar(100) not null,
    description varchar(500),
    quantity int not null,
    price decimal(5,2)   not null,
    color varchar(50) not null,
    star_rating decimal(5,2),
    created_on date default current_timestamp(),
    updated_on date
);



