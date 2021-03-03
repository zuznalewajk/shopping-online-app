create table order_details
(
    order_id int not null,
    foreign key (order_id) references orders(id),
    product_id int not null,
    foreign key (product_id) references products(id),
    primary key (order_id, product_id),
    quantity int not null
);