-- create db
DROP DATABASE IF EXISTS ifridge;
CREATE DATABASE ifridge DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

USE ifridge;

-- create tables
CREATE TABLE RECIPE (
    id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT,
    food_category_id INT,
    recipe_name VARCHAR(255),
    recipe_description TEXT NOT NULL,
    prep_time TIME,
    kcal INT
);

CREATE TABLE COURSE (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE FOOD_CATEGORY (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE INGREDIENT (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE QUANTITY (
    id INT AUTO_INCREMENT PRIMARY KEY,
    recipe_id INT NOT NULL,
    ingredient_id INT NOT NULL,
    ingredient_uom_id INT NOT NULL,
    ingredient_quantity float NOT NULL
);

CREATE TABLE UNIT_OF_MEASUREMENT (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE RECIPE_STEP (
    id INT AUTO_INCREMENT PRIMARY KEY,
    recipe_id INT NOT NULL,
    step_number INT NOT NULL,
    step_description TEXT NOT NULL
);

-- foreign keys

ALTER TABLE RECIPE ADD FOREIGN KEY (course_id) REFERENCES COURSE(id);
ALTER TABLE RECIPE ADD FOREIGN KEY (food_category_id) REFERENCES FOOD_CATEGORY(id);

ALTER TABLE QUANTITY ADD FOREIGN KEY (recipe_id) REFERENCES RECIPE(id);
ALTER TABLE QUANTITY ADD FOREIGN KEY (ingredient_id) REFERENCES INGREDIENT(id);
ALTER TABLE QUANTITY ADD FOREIGN KEY (ingredient_uom_id) REFERENCES UNIT_OF_MEASUREMENT(id);

ALTER TABLE RECIPE_STEP ADD FOREIGN KEY (recipe_id) REFERENCES RECIPE(id);

-- first inserts
INSERT INTO course(name) VALUES
    ('Obiad'),
    ('Śniadanie'),
    ('Kolacja');

INSERT INTO food_category (name) VALUES
    ('Wegetariańskie'),
    ('Mięsne'),
    ('Jarskie');

INSERT INTO ingredient (name) VALUES
    ('Jajka'),
    ('Szynka'),
    ('Masło'),
    ('Kurczak'),
    ('Bazylia'),
    ('Bułka grahamka'),
    ('Mleko'),
    ('Cebula biała'),
    ('Papryka czerwona'),
    ('Pomidory z puszki'),
    ('Oliwa z oliwek'),
    ('Hummus'),
    ('Ogórek');


INSERT INTO unit_of_measurement (name) VALUES
    ('g'),
    ('ml'),
    ('kg'),
    ('szt');

--Jajecznica

INSERT INTO recipe (course_id, food_category_id, recipe_name, recipe_description, prep_time, kcal) VALUES
    (2, 2,'Jajecznica na szynce', 'Tradycyjna jajecznica na szynce, dobra na pożywne i wartościowe śniadanie.', '15', 250 );

INSERT INTO recipe_step (recipe_id, step_number, step_description) VALUES
    (1, 1, 'Rozbić jajka do miski i rozmieszać widelcem'),
    (1, 2, 'Dolać mleka do jajek i ponownie mieszać do uzyskania jednolitej konsystencji'),
    (1, 3, 'Rozgrzać masło na patelni'),
    (1, 4, 'Pokroić szynkę w kostkę i wrzucić na patelnię'),
    (1, 5, 'Zalać szynkę jajkami i mieszać do ścięcia jajecznicy według uznania');


INSERT INTO quantity (recipe_id, ingredient_id, ingredient_uom_id, ingredient_quantity) VALUES
    (1, 1, 4, 2),
    (1, 2, 1, 5),
    (1, 3, 1, 5),
    (1, 7, 2, 1);

--Szakszuka

INSERT INTO recipe (course_id, food_category_id, recipe_name, recipe_description, prep_time, kcal) VALUES
    (2, 1,'Szakszuka', 'Pyszne danie z Bliskiego Wschodu idealne na śniadanie.', '20', 400  );

INSERT INTO recipe_step (recipe_id, step_number, step_description) VALUES
    (2,1, 'Na patelni rozgrzać oliwę'),
    (2,2, 'Cebulę posiekać w kosteczkę i wrzucić na patelnię'),
    (2,3, 'Paprykę pokroić na paski i na pół, i wrzucić na patelnię. Chwilę dusić'),
    (2,4, 'Wlać pomidory z puszki i przyprawić, wymieszać'),
    (2,5, 'Z warzyw na patelni uformować pierścień, do dziury na środku wbić 3 jajka'),
    (2,6, 'Patelnię przykryć i dusić aż do ścięcia jajka');

INSERT INTO quantity (recipe_id, ingredient_id, ingredient_uom_id, ingredient_quantity) VALUES
    (2, 8, 1, 60),
    (2, 9, 1, 80),
    (2, 10, 1, 200),
    (2, 11, 1, 10),
    (2, 1, 4, 3);

--Bułki z hummusem

INSERT INTO recipe (course_id, food_category_id, recipe_name, recipe_description, prep_time, kcal) VALUES
    (2,1, 'Bułki z hummusem', 'Lekkie i pożywne śniadanie dla fascynatów pieczywa z pastą', '5', 300);

INSERT INTO recipe_step (recipe_id, step_number, step_description) VALUES 
    (3,1, 'Bułki przekroić wzdłuż na pół'),
    (3,2, 'W każdej powstałej parze jedną z przekrojonych części od wewnętrznej strony posmarować hummusem'),
    (3,3, 'Ogórek obrać i pokroić w talarki, a następnie ułożyć na hummusie'),
    (3,4 'Bułkę złożyc w całość');

INSERT INTO quantity (recipe_id, ingredient_id, ingredient_uom_id, ingredient_quantity) VALUES
    (3, 6, 4 ,2),
    (3, 12, 1, 90),
    (3, 13, 1, 70);

