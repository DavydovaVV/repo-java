select * 
from products 
where title like 'ACADEMY D%';

select * 
from products 
where price = 9.99 and category = 8 
order by category, price;

select * 
from products
where category = 8 or category = 15;

select * 
from products
where price between 10 and 20;

select * 
from orders
where orderdate between '2004-01-05' and '2004-02-05';

select customerid, 
count(*)
from orders 
group by customerid
order by 1, 2;

select orderdate, 
customerid, 
sum(totalamount) 
from orders
group by orderdate, 
customerid 
having sum(totalamount) > 100
order by 1, 2, 3;

select orders.orderdate,
products.title,
customers.firstname, 
customers.lastname
from  orderlines 
inner join orders on orderlines.orderid = orders.orderid
inner join customers on orders.customerid = customers.customerid
inner join products on orderlines.prod_id = products.prod_id
where orderlines.orderdate < '2004-01-07'
order by 1, 2, 3, 4;

