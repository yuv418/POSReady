use posready;
create table pos_items (name VARCHAR(50), descr VARCHAR(300), id INT, price DECIMAL(11,2));
create table pos_rebates (name VARCHAR(50), descr VARCHAR(300), id INT, price_less DECIMAL(11,2));
create table pos_clearance (name VARCHAR(50), descr VARCHAR(300), id_item INT, price_less DECIMAL(11,2));


create table pos_rebates_exceptions (id_rebate INT, id_item INT);
/*
create tables for items (not on sale) pos_items, coupons (rebates on items) pos_rebates, and items on sale pos_clearance
*/