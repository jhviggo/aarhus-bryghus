USE bryghus

--Opgave 2a
SELECT *
FROM ProductInPriceList
WHERE product = 1

--Opgave 2b
SELECT p.price*(0.8)
FROM ProductInPriceList p
WHERE p.product = 1 AND p.priceList = 'Default'

--Opgave 2c
SELECT SUM(ol.amount*pl.price)
FROM OrderLine ol
INNER JOIN Orders o
ON ol.orderId = o.id
INNER JOIN ProductInPriceList pl
ON pl.product = ol.product AND pl.priceList = ol.priceList
WHERE o.id = 1

--Opgave 2d
SELECT productName, amount
FROM
(
    SELECT p.productName AS productName, SUM(ol.amount) AS amount
    FROM OrderLine ol
    INNER JOIN Orders o
    ON ol.orderId = o.id
    INNER JOIN Product p
    ON p.id = ol.product
    GROUP BY p.productName
) AS productAmount
WHERE amount > 5

--Opgave 2e
SELECT p.productName, p.productGroup
FROM Product p
WHERE p.id NOT IN 
(
    SELECT pl.product 
    FROM ProductInPriceList pl
    WHERE pl.priceList = 'Fredagsbar'
)

--Opgave 2f
SELECT AVG(price)
FROM 
(
    SELECT SUM(ol.amount*pl.price) AS price
    FROM OrderLine ol
    INNER JOIN Orders o
    ON ol.orderId = o.id
    INNER JOIN ProductInPriceList pl
    ON pl.product = ol.product AND pl.priceList = ol.priceList
    WHERE o.id = ol.orderId
    GROUP BY o.id
) AS orders

--Opgave 3


--Opgave 4a

--Opgave 4b

--Opgave 5