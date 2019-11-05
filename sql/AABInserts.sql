USE bryghus

INSERT INTO ProductGroup VALUES
    ('Bottled beers', 0),
    ('Draught beers', 0),
    ('Spirits', 0),
    ('Kegs', 200),
    ('Carbon dioxide', 1000),
    ('Giftboxes', 0),
    ('Snacks', 0),
    ('Clothes', 0),
    ('Draught beer systems', 0),
    ('Guided tour', 0),
    ('Raw products', 0)

SELECT * FROM ProductGroup

INSERT INTO Product VALUES
    ('Klosterbryg', 'Bottled beers'),
    ('Sweet Georgia Brown', 'Bottled beers'),
    ('Extra Pilsner', 'Bottled beers'),
    ('Celebration', 'Bottled beers'),
    ('Blondie', 'Bottled beers'),
    ('Forårsbryg', 'Bottled beers'),
    ('India Pale Ale', 'Bottled beers'),
    ('Julebryg', 'Bottled beers'),
    ('Juletønden', 'Bottled beers'),
    ('Old Strong Ale', 'Bottled beers'),
    ('Fregatten Jylland', 'Bottled beers'),
    ('Imperial Stout', 'Bottled beers'),
    ('Tribute', 'Bottled beers'),
    ('Black Monster', 'Bottled beers'),

    ('Klosterbryg', 'Draught beers'),
    ('Jazz Classic', 'Draught beers'),
    ('Extra Pilsner', 'Draught beers'),
    ('Celebration', 'Draught beers'),
    ('Blondie', 'Draught beers'),
    ('Forårsbryg', 'Draught beers'),
    ('India Pale Ale', 'Draught beers'),
    ('Julebryg', 'Draught beers'),
    ('Imperial Stout', 'Draught beers'),
    ('Special', 'Draught beers'),
    ('Æblebrus', 'Draught beers'),
    ('Cola', 'Draught beers'),
    ('Nikoline', 'Draught beers'),
    ('7-Up', 'Draught beers'),
    ('Vand', 'Draught beers'),

    ('Spirit of Aarhus', 'Spirits'),
    ('Spirit of Aarhus med splint', 'Spirits'),
    ('Whisky', 'Spirits'),
    ('Liquor of Aarhus', 'Spirits'),

    ('Klosterbryg','Kegs'),
    ('Jazz Classic','Kegs'),
    ('Extra Pilsner','Kegs'),
    ('Celebration','Kegs'),
    ('Blondie','Kegs'),
    ('Forårsbryg','Kegs'),
    ('India Pale Ale','Kegs'),
    ('Julebryg','Kegs'),
    ('Imperial Stout','Kegs'),

    ('6kg','Carbon Dioxide'),
    ('4kg','Carbon Dioxide'),
    ('10kg','Carbon Dioxide'),

    ('25kg malt','Raw products'),

    ('T-Shirt','Clothes'),
    ('Polo','Clothes'),
    ('Cap','Clothes'),

    ('1 Hane','Draught beer systems'),
    ('2 Haner','Draught beer systems'),
    ('Bar med flere haner','Draught beer systems')

SELECT * FROM Product

INSERT INTO Consumable VALUES
    (60, 'cl', 6.0, 'India Pale Lager', 'BOTTLE', 1),
    (60, 'cl', 5.5, 'Brown Ale', 'BOTTLE', 2),
    (60, 'cl', 5.0, 'Pilsner', 'BOTTLE', 3),
    (60, 'cl', 6.5, 'Pale Ale', 'BOTTLE', 4),
    (60, 'cl', 5.0, 'Pale Ale', 'BOTTLE', 5),
    (60, 'cl', 7.0, 'Dortmunder', 'BOTTLE', 6),
    (60, 'cl', 6.0, 'India Pale Ale', 'BOTTLE', 7),
    (60, 'cl', 6.0, 'Spiced / Herbed Beer', 'BOTTLE', 8),
    (60, 'cl', 8.0, 'Winter Ale', 'BOTTLE', 9),
    (60, 'cl', 8.0, 'Old Ale', 'BOTTLE', 10),
    (60, 'cl', 8.0, 'Winter Warmer', 'BOTTLE', 11),
    (60, 'cl', 8.0, 'Imperial Stout', 'BOTTLE', 12),
    (60, 'cl', 9.0, 'Barley Wine', 'BOTTLE', 13),
    (60, 'cl', 10.0, 'Porter', 'BOTTLE', 14)

INSERT INTO Consumable VALUES
    (40, 'cl', 6.0, 'India Pale Lager', 'DRAUGHT', 15),
    (40, 'cl', 5.0, 'English Mild Ale', 'DRAUGHT', 16),
    (40, 'cl', 5.0, 'Pilsner', 'DRAUGHT', 17),
    (40, 'cl', 6.5, 'Pale Ale', 'DRAUGHT', 18),
    (40, 'cl', 5.0, 'Pale Ale', 'DRAUGHT', 19),
    (40, 'cl', 7.0, 'Dortmunder', 'DRAUGHT', 20),
    (40, 'cl', 6.0, 'India Pale Ale', 'DRAUGHT', 21),
    (40, 'cl', 6.0, 'Spiced / Herbed Beer', 'DRAUGHT', 22),
    (40, 'cl', 8.0, 'Imperial Stout', 'DRAUGHT', 23),
    (40, 'cl', 0.0, '??', 'DRAUGHT', 24),
    (40, 'cl', 0.0, 'Alcoholfree Cider', 'DRAUGHT', 25),
    (40, 'cl', 0.0, 'Cola', 'DRAUGHT', 26),
    (40, 'cl', 0.0, 'Squash', 'DRAUGHT', 27),
    (40, 'cl', 0.0, 'Soda', 'DRAUGHT', 28),
    (40, 'cl', 0.0, 'Water', 'DRAUGHT', 29)

INSERT INTO Consumable VALUES
    (50, 'cl', 40.0, 'Whisky', null, 30),
    (50, 'cl', 40.0, 'Whisky', null, 31),
    (50, 'cl', 45.0, 'Whisky', null, 32),
    (35, 'cl', 30.0, 'Liquor', null, 33)

INSERT INTO Consumable VALUES
    (20, 'L', 6.0, 'India Pale Lager', 'KEG', 34),
    (25, 'L', 5.0, 'English Mild Ale', 'KEG', 35),
    (25, 'L', 5.0, 'Pilsner', 'KEG', 36),
    (20, 'L', 6.5, 'Pale Ale', 'KEG', 37),
    (25, 'L', 5.0, 'Pale Ale', 'KEG', 38),
    (20, 'L', 7.0, 'Dortmunder', 'KEG', 39),
    (20, 'L', 6.0, 'India Pale Ale', 'KEG', 40),
    (20, 'L', 6.0, 'Spiced / Herbed Beer', 'KEG', 41),
    (20, 'L', 8.0, 'Imperial Stout', 'KEG', 42),

SELECT *
FROM Product p
INNER JOIN Consumable c
ON p.id = c.productId

INSERT INTO PriceList VALUES
    ('Default'),
    ('Fredagsbar')

INSERT INTO ProductInPriceList VALUES
    (1, 'Default', 36.0),
    (1, 'Fredagsbar', 50.0),
    (2, 'Default', 36.0),
    (2, 'Fredagsbar', 50.0)
    (3, 'Default', 36.0),
    (3, 'Fredagsbar', 50.0),
    (4, 'Default', 46.0),
    (4, 'Fredagsbar', 50.0),
    (5, 'Default', 36.0),
    (5, 'Fredagsbar', 50.0),
    (6, 'Default', 36.0),
    (6, 'Fredagsbar', 50.0),
    (7, 'Default', 36.0),
    (7, 'Fredagsbar', 50.0),
    (8, 'Default', 36.0),
    (8, 'Fredagsbar', 50.0),
    (9, 'Default', 36.0),
    (9, 'Fredagsbar', 50.0),
    (10, 'Default', 36.0),
    (10, 'Fredagsbar', 50.0),
    (11, 'Default', 36.0),
    (11, 'Fredagsbar', 50.0),
    (12, 'Default', 36.0),
    (12, 'Fredagsbar', 50.0),
    (13, 'Default', 36.0),
    (13, 'Fredagsbar', 50.0),
    (14, 'Default', 50.0),
    (14, 'Fredagsbar', 50.0)

INSERT INTO ProductInPriceList VALUES
    (15, 'Fredagsbar', 30.0),
    (16, 'Fredagsbar', 30.0),
    (17, 'Fredagsbar', 30.0),
    (18, 'Fredagsbar', 30.0),
    (19, 'Fredagsbar', 30.0),
    (20, 'Fredagsbar', 30.0),
    (21, 'Fredagsbar', 30.0),
    (22, 'Fredagsbar', 30.0),
    (23, 'Fredagsbar', 30.0),
    (24, 'Fredagsbar', 30.0),
    (25, 'Fredagsbar', 15.0),
    (26, 'Fredagsbar', 15.0),
    (27, 'Fredagsbar', 15.0),
    (28, 'Fredagsbar', 15.0),
    (29, 'Fredagsbar', 10.0)

INSERT INTO Orders VALUES
    ('2019-11-4 15:00:00', '2019-11-4 15:15:00', 'DONE'),
    ('2019-11-5 15:00:00', '2019-11-5 15:15:00', 'DONE')

SELECT * FROM Orders

INSERT INTO OrderLine VALUES
    (2, 1, 1, 'Default'),
    (3, 2, 1, 'Default'),
    (1, 3, 1, 'Default'),
    (1, 4, 1, 'Fredagsbar'),
    (2, 1, 2, 'Fredagsbar')

INSERT INTO OrderLine VALUES
    (5, 4, 1, 'Fredagsbar')

INSERT INTO Orders VALUES
    ('2019-11-6 15:00:00', '2019-11-6 15:15:00', 'DONE')

INSERT INTO OrderLine VALUES
    (1, 4, 3, 'Fredagsbar')

SELECT * 
FROM ProductInPriceList pl
INNER JOIN Product p
ON pl.product = p.id

INSERT INTO ProductGroup VALUES
    ('Test', 0)

INSERT INTO Product VALUES
    ('Test1', 'Test'),
    ('Test2', 'Test')