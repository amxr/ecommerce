create sequence  IF NOT EXISTS category_sequence START with 1 INCREMENT BY 1;

create sequence  IF NOT EXISTS product_sequence START with 1 INCREMENT BY 1;

create sequence  IF NOT EXISTS user_sequence START with 1 INCREMENT BY 1;

create TABLE categories (
  id BIGINT NOT NULL,
   name VARCHAR(255),
   description VARCHAR(255),
   image_url VARCHAR(255),
   CONSTRAINT pk_categories PRIMARY KEY (id)
);

create TABLE products (
  id BIGINT NOT NULL,
   name VARCHAR(255),
   imageurl VARCHAR(255),
   price DOUBLE PRECISION,
   description VARCHAR(255),
   category_id BIGINT NOT NULL,
   CONSTRAINT pk_products PRIMARY KEY (id)
);

create TABLE users (
  id BIGINT NOT NULL,
   first_name VARCHAR(255),
   last_name VARCHAR(255),
   email VARCHAR(255),
   password VARCHAR(255),
   role INTEGER,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

alter table products add CONSTRAINT FK_PRODUCTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);