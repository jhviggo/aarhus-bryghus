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
GO

CREATE VIEW productSales
AS
SELECT p.productName, p.productGroup, (
        SELECT COUNT(DISTINCT ol.orderId)
        FROM OrderLine ol
        WHERE ol.product = p.id
    ) AS orderCount
FROM Product p, OrderLine ol
GROUP BY p.productName, p.productGroup, p.id

GO

SELECT * FROM productSales

--Opgave 4a
GO

CREATE PROCEDURE getPricelist
@priceList VARCHAR(100)
AS
SELECT p.productName, pip.price*pip.rebate
FROM Product p, ProductInPriceList pip
WHERE pip.priceList = @priceList
AND p.id = pip.product

GO

EXECUTE getPricelist 'Fredagsbar'

--Opgave 4b
GO

CREATE PROCEDURE changeRebate
@priceList VARCHAR(100),
@rebate FLOAT
AS
UPDATE ProductInPriceList
SET rebate = @rebate
WHERE priceList = @priceList

GO

EXECUTE changeRebate 'Fredagsbar', 0.8

SELECT * 
FROM ProductInPriceList
WHERE priceList = 'Fredagsbar'

--Opgave 5
GO

CREATE TRIGGER deleteProductGroup on Product
AFTER DELETE
AS
DECLARE @productGroup VARCHAR(64) = 
(
    SELECT d.ProductGroup FROM deleted d, ProductGroup pg where pg.productType = d.productGroup
)
BEGIN
    IF NOT EXISTS
    (
        SELECT *
        FROM Product p
        WHERE p.productGroup = @productGroup
    )
    BEGIN
        DELETE FROM ProductGroup
        WHERE productType = @productGroup
    END
END

GO

INSERT INTO ProductGroup VALUES
    ('Test', 0)

INSERT INTO Product VALUES
    ('Test1', 'Test'),
    ('Test2', 'Test')

SELECT * 
FROM Product p
WHERE p.productGroup = 'Test'

DELETE
FROM Product
WHERE productName = 'Test1'

DELETE
FROM Product
WHERE productName = 'Test2'

SELECT *
FROM ProductGroup


--Opgave 6a


--Opgave 6b







