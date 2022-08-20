CREATE TABLE lab3_ko_users (
    id serial4 NOT NULL,
    username varchar(20) NOT NULL,
    email varchar(50) NOT NULL,
    "password" varchar(255) NOT NULL,
    permissions bool NOT NULL DEFAULT false,
    CONSTRAINT lab3_ko_users_email_unique UNIQUE (email),
    CONSTRAINT lab3_ko_users_pkey PRIMARY KEY (id)
);

CREATE TABLE lab3_ko_categories (
    id serial4 NOT NULL,
    parent_id int4 NULL,
    title varchar(50) NOT NULL,
    CONSTRAINT lab3_ko_categories_pkey PRIMARY KEY (id),
    CONSTRAINT lab3_ko_categories_title_unique UNIQUE (title),
    CONSTRAINT lab3_ko_categories_parent_category_id_fkey FOREIGN KEY (parent_id) REFERENCES lab3_ko_categories(id)
);

CREATE TABLE lab3_ko_products (
    id serial4 NOT NULL,
    category_id int4 NOT NULL,
    title varchar(50) NOT NULL,
    price numeric(11, 2) NOT NULL,
    amount int4 NOT NULL,
    description varchar(255) NOT NULL,
    img_path varchar(255) NOT NULL,
    CONSTRAINT lab3_ko_products_pkey PRIMARY KEY (id),
    CONSTRAINT lab3_ko_products_category_id_fkey FOREIGN KEY (category_id) REFERENCES lab3_ko_categories(id)
);

CREATE TABLE lab3_ko_comments (
    id serial4 NOT NULL,
    user_id int4 NOT NULL,
    title varchar(50) NULL DEFAULT NULL::character varying,
    "comment" varchar(255) NOT NULL,
    rating int2 NOT NULL,
    product_id int4 NOT NULL,
    CONSTRAINT lab3_ko_comments_pkey PRIMARY KEY (id),
    CONSTRAINT lab3_ko_comments_fk FOREIGN KEY (product_id) REFERENCES lab3_ko_products(id),
    CONSTRAINT lab3_ko_comments_user_id_fkey FOREIGN KEY (user_id) REFERENCES lab3_ko_users(id)
);

CREATE TABLE lab3_ko_orders (
    id serial4 NOT NULL,
    user_id int4 NOT NULL,
    product_id int4 NOT NULL,
    CONSTRAINT lab3_ko_order_lists_pkey PRIMARY KEY (id),
    CONSTRAINT lab3_ko_order_lists_product_id_fkey FOREIGN KEY (product_id) REFERENCES lab3_ko_products(id),
    CONSTRAINT lab3_ko_order_lists_user_id_fkey FOREIGN KEY (user_id) REFERENCES lab3_ko_users(id)
);

INSERT INTO lab3_ko_categories (parent_category_id,title) VALUES
    (NULL,'Фрукты'),
    (NULL,'Овощи'),
    (1,'Яблоки'),
    (1,'Апельсины'),
    (2,'Помидоры'),
    (2,'Огурцы');

INSERT INTO public.lab3_ko_products (category_id,title,price,amount,description,img_path) VALUES
    (4,'Апельсины такие',40.00,45,'Вкусные апельсины','https://i.imgur.com/eMYWTYk.png'),
    (4,'Апельсины сякие',45.00,40,'Очень вкусные апельсины','https://i.imgur.com/NEAqPf2.png'),
    (3,'яблоки сякие',35.00,10,'Очень вкусные яблочки','https://i.imgur.com/ETHzCHy.png'),
    (3,'яблоки такие',30.00,20,'Вкусные яблочки','https://i.imgur.com/WiZH5rN.png');


INSERT INTO lab3_ko_users (username,email,"password",permissions) VALUES
    ('ALexus','test@gmail.com','test12',true);

INSERT INTO lab3_ko_orders (user_id,product_id) VALUES
   (1,1),
   (1,2);

INSERT INTO lab3_ko_comments (user_id,title,"comment",rating,product_id) VALUES
    (1,NULL,'seq test',5,1),
    (1,NULL,'seq test',5,1);

