select * 
from products 
where title like 'ACADEMY D%';

select * 
from products 
where price = 9.99 and category = 8 
order by category, price;

select * 
from products
where category in(8, 15);

select * 
from products
where price between 10 and 20;

select * 
from orders
where orderdate between to_date('2004-01-05', 'yyyy-mm-dd') and to_date('2004-02-05', 'yyyy-mm-dd');

select customerid, 
count(*) as "quantity of customers"
from orders 
group by customerid
order by customerid, 
count(*);

select orderdate, 
customerid, 
sum(totalamount) as "total sum" 
from orders
group by orderdate, 
customerid 
having sum(totalamount) > 100
order by orderdate, 
customerid, 
sum(totalamount);

select orders.orderdate,
products.title,
customers.firstname, 
customers.lastname
from  orderlines 
inner join orders on orderlines.orderid = orders.orderid
inner join customers on orders.customerid = customers.customerid
inner join products on orderlines.prod_id = products.prod_id
where orderlines.orderdate < to_date('2004-01-07', 'yyyy-mm-dd')
order by orders.orderdate,
products.title,
customers.firstname, 
customers.lastname;

