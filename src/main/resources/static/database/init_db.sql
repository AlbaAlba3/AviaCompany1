create sequence hibernate_sequence start 1 increment 1;
create table cart
(
    id int8 not null,
    total_items int4,
    count_prise int4,
    user_id int8,
    primary key (id)
);

create table cart_item
(
    id int8 not null,
    amount int4,
    cart_id int8 not null,
    product_id int8 not null,
    primary key (id)
);



create table order_table
(
    id int8 not null,
    status          varchar(255),
    products_prise int4,
    user_id int8,
    primary key (id)
);

create table ordered_product
(
    id int8 not null,
    amount int4,
    order_id int8 not null,
    product_id int8 not null,
    primary key (id)
);

create table product
(

	id bigserial not null,
    description varchar(8096) not null,
    name        varchar(255)  not null,
    flyin       varchar(255)  not null,
    flyout       varchar(255),
    places      int4,
    price       int4,
    primary key (id)
);


create table user_role
(
    user_id int8 not null,
    roles varchar(255)
);

create table usr
(
    id int8 not null,
    active          boolean,
    username        varchar(50),
    password        varchar(50),
    balance         int4 ,
    primary key (id)
);


alter table if exists cart add constraint cart_user foreign key (user_id) references usr;
alter table if exists cart_item add constraint cart_item_cart foreign key (cart_id) references cart;
alter table if exists cart_item add constraint cart_item_product foreign key (product_id) references product;
alter table if exists order_table add constraint order_user foreign key (user_id) references usr;
alter table if exists ordered_product add constraint ordered_product_order_table foreign key (order_id) references order_table;
alter table if exists ordered_product add constraint ordered_product_product foreign key (product_id) references product;
alter table if exists user_role add constraint user_role_usr foreign key (user_id) references usr;