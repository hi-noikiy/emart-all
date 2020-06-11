
DROP DATABASE IF EXISTS emart_product;
CREATE DATABASE emart_product;
USE emart_product;

CREATE TABLE product (
	id                   bigint(20)     NOT NULL AUTO_INCREMENT PRIMARY KEY,
	code                 VARCHAR(50)    unique NOT NULL,
	name                 VARCHAR(50)    NOT NULL,
	description          VARCHAR(255),
	price                DECIMAL(10,2)  DEFAULT 0,
	Stock                INT            DEFAULT 0,
	discount             DECIMAL(5,2)   DEFAULT 0,
	sales_volume         INT            DEFAULT 0,
	active               VARCHAR(1)     DEFAULT '0' COMMENT '0:Sell, 1:Sell Up',
	category_code        VARCHAR(3)     DEFAULT '0',
	manufacturer_code    VARCHAR(3)     DEFAULT '0',
	image_url            VARCHAR(100)   DEFAULT NULL,
	create_time          timestamp,
	update_time          timestamp
);

CREATE TABLE category (
    id                   bigint(20)   NOT NULL AUTO_INCREMENT PRIMARY KEY,
    category_code        VARCHAR(3)   unique NOT NULL,
	category_name        VARCHAR(20)  NOT NULL
);


CREATE TABLE manufacturer (
    id                   bigint(20)   NOT NULL AUTO_INCREMENT PRIMARY KEY,
    manufacturer_code    VARCHAR(3)   unique NOT NULL,
	manufacturer_name    VARCHAR(20)  NOT NULL
);

CREATE TABLE cart (
	id                   bigint(20)     NOT NULL AUTO_INCREMENT PRIMARY KEY,
	product_code         VARCHAR(50)    unique NOT NULL,
	product_name         VARCHAR(50)    NOT NULL,
	price                DECIMAL(10,2)  DEFAULT 0,
	quantity             INT            DEFAULT 0,
	stock                INT            DEFAULT 0,
	discount             DECIMAL(5,2)   DEFAULT 0,
	image_url            VARCHAR(100)   DEFAULT NULL,
	user_id              int,
	create_time          timestamp,
	update_time          timestamp
);

CREATE TABLE purchase_history (
    id                   bigint(20)   NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_code         VARCHAR(50),
    product_name         VARCHAR(50),
	price                DECIMAL(10,2)  DEFAULT 0,
	quantity             INT            DEFAULT 0,
	purchase_date        VARCHAR(10),
	image_url            VARCHAR(100)   DEFAULT NULL,
	user_id              int,
	create_time          timestamp,
	update_time          timestamp
);

CREATE TABLE evaluate (
    id                   bigint(20)   NOT NULL AUTO_INCREMENT PRIMARY KEY,
	product_code         VARCHAR(50),
	product_name         VARCHAR(50),
	comment_content      VARCHAR(500),
	image_url            VARCHAR(100)   DEFAULT NULL,
	create_time          timestamp,
	update_time          timestamp
);


INSERT INTO `category`(`id`, `category_code`, `category_name`) VALUES (1, '0', 'Books');
INSERT INTO `category`(`id`, `category_code`, `category_name`) VALUES (2, '1', 'Food');
INSERT INTO `category`(`id`, `category_code`, `category_name`) VALUES (3, '2', 'Clothes');
INSERT INTO `category`(`id`, `category_code`, `category_name`) VALUES (4, '3', 'Drink');
INSERT INTO `category`(`id`, `category_code`, `category_name`) VALUES (5, '4', 'Shoes');
INSERT INTO `category`(`id`, `category_code`, `category_name`) VALUES (6, '5', 'Phone');
INSERT INTO `category`(`id`, `category_code`, `category_name`) VALUES (7, '6', 'TV');


INSERT INTO `manufacturer`(`id`, `manufacturer_code`, `manufacturer_name`) VALUES (1, '0', 'SONY');
INSERT INTO `manufacturer`(`id`, `manufacturer_code`, `manufacturer_name`) VALUES (2, '1', 'TCL');
INSERT INTO `manufacturer`(`id`, `manufacturer_code`, `manufacturer_name`) VALUES (3, '2', 'Apple');
INSERT INTO `manufacturer`(`id`, `manufacturer_code`, `manufacturer_name`) VALUES (4, '3', 'Decathlon');
INSERT INTO `manufacturer`(`id`, `manufacturer_code`, `manufacturer_name`) VALUES (5, '4', 'Casio');
INSERT INTO `manufacturer`(`id`, `manufacturer_code`, `manufacturer_name`) VALUES (6, '5', 'SK-II');
INSERT INTO `manufacturer`(`id`, `manufacturer_code`, `manufacturer_name`) VALUES (7, '6', 'Loreal');
INSERT INTO `manufacturer`(`id`, `manufacturer_code`, `manufacturer_name`) VALUES (8, '7', 'Jack');
INSERT INTO `manufacturer`(`id`, `manufacturer_code`, `manufacturer_name`) VALUES (9, '8', 'VR');
INSERT INTO `manufacturer`(`id`, `manufacturer_code`, `manufacturer_name`) VALUES (10, '9', 'DR');













