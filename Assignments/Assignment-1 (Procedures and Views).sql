use hex_java_fsd;

create table sales(
id int primary key auto_increment,
product_name varchar(255),
category varchar(255),
quantity int,
price int,
date_of_sale DATE
);

INSERT INTO sales (id, product_name, category, quantity, price, date_of_sale) VALUES
(1, 'Laptop', 'Electronics', 2, 75000, '2026-02-10'),
(2, 'Smartphone', 'Electronics', 5, 30000, '2026-02-12'),
(3, 'Office Chair', 'Furniture', 3, 8500, '2026-02-15'),
(4, 'Dining Table', 'Furniture', 1, 25000, '2026-02-18'),
(5, 'Headphones', 'Electronics', 4, 2500, '2026-02-20');

/* 1. WAP get_sales_by_category 
input: category */
DELIMITER $$
create procedure get_sales_by_category(IN s_category varchar(255))
BEGIN
	select * from sales 
    where category=s_category;
END
$$
CALL get_sales_by_category("Electronics");

/* 2. WAP Count Total sales by given category 
input: category 
output: INT total_sales */
DELIMITER $$
create procedure total_sales_by_category(IN s_category varchar(255), OUT total_sales int)
BEGIN
	if TRIM(s_category)="" or s_category is null then
    signal sqlstate "45000"
    set message_text="Category cannot be null";
    end if;
    
    select count(id)
    from sales
    where category = s_category;
END
$$
CALL total_sales_by_category("Electronics",@total_sale);
select total_sale;

/* 3. Create a View to product sales summary 
product_name, cateogry, quantity, price */
create view sales_view as
select product_name, category, quantity, price
from sales;
select * from sales_view;