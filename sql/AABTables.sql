CREATE DATABASE bryghus

USE bryghus

DROP TABLE ProductGroup
CREATE TABLE ProductGroup(
    productType VARCHAR(64) NOT NULL PRIMARY KEY,
    tax INT
)

DROP TABLE Product
CREATE TABLE Product(
    id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    productName VARCHAR(100) NOT NULL,
    productGroup VARCHAR(64) NOT NULL FOREIGN KEY REFERENCES ProductGroup(productType),
)
DROP TABLE Consumable
CREATE TABLE Consumable(
    size INT NOT NULL,
    unit VARCHAR(10) NOT NULL,
    alcoholPercentage FLOAT NOT NULL,
    consumableType VARCHAR(100) NOT NULL,
    beerType VARCHAR(100) CHECK (
        beerType IN('BOTTLE', 'DRAUGHT', 'KEG')
    ),
    productId INT NOT NULL PRIMARY KEY FOREIGN KEY REFERENCES Product(id)
)
DROP TABLE DraughtBeerSystem
CREATE TABLE DraughtBeerSystem(
    numberOfTaps INT NOT NULL,
    productId INT NOT NULL PRIMARY KEY FOREIGN KEY REFERENCES Product(id)
)
DROP TABLE RawProduct
CREATE TABLE RawProduct(
    productWeight INT NOT NULL,
    productId INT NOT NULL PRIMARY KEY FOREIGN KEY REFERENCES Product(id)
)

DROP TABLE Accessory
CREATE TABLE Accessory(
    size VARCHAR(20) NOT NULL,
    productId INT NOT NULL PRIMARY KEY FOREIGN KEY REFERENCES Product(id)
)

DROP TABLE GiftBox
CREATE TABLE GiftBox(
    amountOfBeers INT NOT NULL,
    amountOfGlasses INT NOT NULL,
    productId INT NOT NULL PRIMARY KEY FOREIGN KEY REFERENCES Product(id)
)

DROP TABLE BeerInGiftBox
CREATE TABLE BeerInGiftBox(
    giftBoxId INT NOT NULL PRIMARY KEY FOREIGN KEY REFERENCES GiftBox(ProductId),
    beerId INT NOT NULL FOREIGN KEY REFERENCES Consumable(productId),
    numberOfBeers INT NOT NULL
)

DROP TABLE GlassInGiftBox
CREATE TABLE GlassInGiftBox(
    giftBoxId INT NOT NULL PRIMARY KEY FOREIGN KEY REFERENCES GiftBox(ProductId),
    glassId INT NOT NULL FOREIGN KEY REFERENCES Accessory(productId),
    numberOfGlasses INT NOT NULL
)

DROP TABLE Orders
CREATE TABLE Orders(
    id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    startTimeStamp DATETIME NOT NULL,
    endTimeStamp DATETIME,
    orderStatus VARCHAR(10) NOT NULL CHECK (
        orderStatus IN('CREATED', 'PROGRESS', 'DONE'))
)

DROP TABLE OrderLine
CREATE TABLE OrderLine(
    id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    amount INT NOT NULL,
    product INT NOT NULL FOREIGN KEY REFERENCES Product(id),
    orderId INT NOT NULL FOREIGN KEY REFERENCES Orders(id),
    priceList VARCHAR(100) FOREIGN KEY REFERENCES PriceList(listName)
)

CREATE TABLE PriceList(
    listName VARCHAR(100) NOT NULL PRIMARY KEY,
)

DROP TABLE ProductInPriceList
CREATE TABLE ProductInPriceList(
    product INT NOT NULL FOREIGN KEY REFERENCES Product(id),
    priceList VARCHAR(100) NOT NULL FOREIGN KEY REFERENCES PriceList(listName),
    price FLOAT NOT NULL,
    rebate FLOAT DEFAULT 1.0
)