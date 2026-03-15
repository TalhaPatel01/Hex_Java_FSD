/*
Procedures are categorized in 2 types
1. Anonymous block
2. Stored Procedure

MYSQL= no AS in procedure, no DBMS_OUTPUT(select)
Oracle = U Use AS in procedure, DBMS_OUTPUT works
*/

/* Simple stored procedure*/
DELIMITER $$
create procedure get_all_customers()
BEGIN
	select * from customer;
END
$$
CALL get_all_customers();

/* Procedures can take inputs and give outputs */
DELIMITER $$
create procedure get_customers_by_city(IN p_city varchar(255))
BEGIN
	-- validation
    if TRIM(p_city)="" OR p_city is null then
		signal sqlstate "45000"
        set message_text = "city value cannot be null";
	end if;
    
	select * from customer
    where city=p_city
    order by city;
END
$$
drop procedure get_customers_by_city;
CALL get_customers_by_city("Bharuch");
CALL get_customers_by_city("  ");
CALL get_customers_by_city(NULL);

/* procedure to find customer based on given id
   validation: ensure that id is not 0 or negative
*/
DELIMITER $$
create procedure get_customer_by_id(IN p_id INT)
BEGIN
	if p_id<=0 then
		signal sqlstate "45000"
        set message_text = "Id cannot be 0 or negative";
	end if;
    
    select * from customer
    where id=p_id;
END
$$
CALL get_customer_by_id(1);
CALL get_customer_by_id(0);
CALL get_customer_by_id(-1);

/* create a proc for giving total number of customers as output based on given city.  */
DELIMITER $$
create procedure count_customers(IN p_city varchar(255), OUT total_customers int)
BEGIN
	-- validation
    if TRIM(p_city)="" OR p_city is null then
		signal sqlstate "45000"
        set message_text = "city value cannot be null";
	end if;
    
    select count(id)
    from customer
    where city = p_city;
END
$$
CALL count_customers("Bharuch", @total_cust);
select total_cust;

/* Views , hides critical information from database, virtual table */
create view customer_view as
select id,name,city
from customer;

/* IN OUT : variable acts as both IN and OUT, both parameters should be of same data type*/
-- update email and return updated value using INOUT paramater
DELIMITER $$
create procedure update_email(IN p_id int, inout p_email varchar(255))
BEGIN
	update customer
    set email = p_email
    where id = p_id;
END
$$
SET @email_val = "talha@outlook.com";
CALL update_email(1,@email_val);
select @email_val;
select * from customer;

DELIMITER $$
create procedure get_all_products()
BEGIN
	select * from products;
END
$$

-- triggers Triggers are programs like procedures that run automatically based on given condition 
use hex_java_fsd;
create table products(
id INT primary key auto_increment, 
name varchar(255),
price DECIMAL, 
stock INT );

create table transactions(
id INT primary key auto_increment,
product_id INT,
quantity INT,
sale_date Date); 

-- insert some sample data 
insert into products (name,price,stock) values ('Apple Macbook M1', 67000, 4), ("Lenovo Thinkpad", 89000, 3);
select * from products;

/*
	NEW.product_id = 1
    NEW.quantity = 2 
    
    OLD:- points to records which are already present in DB 
    NEW:- points to records which are waiting to be inserted in DB 
*/

/* first trigger to check if there is enough stock */
DELIMITER $$
create trigger check_stock
before insert on transactions
for each row
BEGIN
	DECLARE v_stock int;
    
    select stock into v_stock
    from products
    where id = NEW.product_id;
    
    if NEW.quantity>v_stock then
    signal sqlstate "45000"
    set message_text = "Not enough stock available";
    end if;
END 
$$
insert into transactions (product_id, quantity, sale_date) values (1,2,now());
select * from transactions;

/* 2nd trigger to update stock in product table after successful transaction */
DELIMITER $$
create trigger update_stock
after insert on transactions
for each row
BEGIN
	update products 
    set stock = stock - NEW.quantity
    where id = NEW.product_id;
END
$$
insert into transactions (product_id, quantity, sale_date) values (1,2,now());
select * from products;


/* cursor 
1. Declare the cursor 
2. OPEN it 
3. FETCH the rows 
4. CLOSE it 
*/

DELIMITER $$
create procedure product_name_cursor()
BEGIN
	DECLARE v_name varchar(255);
    DECLARE done boolean DEFAULT FALSE;
    
    -- declaring cursor
    DECLARE customer_name_cursor cursor for
		select name from products;
        
	-- declaring termination variable
    DECLARE continue handler for not found set done = true; -- keep looping, and when there is nothing to loop, make done=TRUE
    
    OPEN customer_name_cursor;
    
    cursor_loop: LOOP
    FETCH customer_name_cursor into v_name;
    
    if done then 
		leave cursor_loop;
	end if;
    
    select v_name;
    end loop;
    
    close customer_name_cursor;
END
$$
CALL product_name_cursor();