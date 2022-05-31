create table if not exists ingredients (
    id varchar(4) primary key not null,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists tacos (
    id identity,
    name varchar(50) not null,
    created_at timestamp not null
);

create table if not exists tacos_ingredients (
    taco_id bigint not null,
    ingredient_id varchar(4) not null
);

alter table tacos_ingredients
    add foreign key (taco_id) references tacos(id);

alter table tacos_ingredients
    add foreign key (ingredient_id) references ingredients(id);

create table if not exists orders (
    id identity,
    delivery_name varchar(50) not null,
    delivery_street varchar(50) not null,
    delivery_city varchar(50) not null,
    delivery_state varchar(2) not null,
    delivery_zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiry varchar(5) not null,
    cc_verify varchar(3) not null,
    placed_at timestamp not null
);

create table if not exists orders_tacos (
    order_id bigint not null,
    taco_id bigint not null
);

alter table orders_tacos
    add foreign key (order_id) references orders(id);

alter table orders_tacos
    add foreign key (taco_id) references tacos(id);