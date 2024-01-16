CREATE TABLE product(
    id serial PRIMARY KEY,
    name VARCHAR(50),
    price NUMERIC(10, 2) NOT NULL
);

INSERT INTO product(name, price) VALUES ('tv', 100.12), ('iphone', 600.41), ('ipad', 600.12);