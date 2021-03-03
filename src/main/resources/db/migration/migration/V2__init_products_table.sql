create table products
(
    id int primary key auto_increment,
    name varchar(100) not null,
    description varchar(500),
    quantity int not null,
    price decimal(5,2)   not null,
    color varchar(50) not null,
    starRating decimal(5,2),
    createdOn date default current_timestamp(),
    updatedOn date
);



