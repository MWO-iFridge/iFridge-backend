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
    ('Ogórek'),
    ('Makaron'),
    ('Pesto bazyliowe'),
    ('Tofu naturalne'),
    ('Parmezan'),
    ('Cebula czerwona'),
    ('Fasola czerwona z puszki'),
    ('Kukurydza z puszki'),
    ('Ryż');


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

--Spaghetti z tofu bazyliowym
INSERT INTO recipe (course_id, food_category_id, recipe_name, recipe_description, prep_time, kcal) VALUES
    (1, 1, 'Spaghetti z tofu bazyliowym', 'Odświeżona osłona włoskiego specjału z dodatkiem tofu.', '30', 550);

INSERT INTO recipe_step (recipe_id, step_number, step_description) VALUES
    (4,1, 'Ugotować makaron według zaleceń na opakowaniu'),
    (4,2, 'Pokroić tofu w drobną kostkę, wymieszać z pesto bazyliowym i odstawić na kilka minut'),
    (4,3, 'Rozgrzać oliwę na patelni'),
    (4,4, 'Drobno posiekać cebulę, dodać na patelnię i podsmażyć'),
    (4,5, 'Dodać tofu na patelnię i smażyć razem z cebulą'),
    (4,6, 'Wlać na patelnię pomidory z puszki, całość przyprawić i wymieszać'),
    (4,7, 'Poddusić aż do zgęstnienia pomidorowego sosu'),
    (4,8, 'Makaron i zawartosć patelni wyłożyć na talerz i dodać potarty parmezan');

INSERT INTO quantity (recipe_id, ingredient_id, ingredient_uom_id, ingredient_quantity) VALUES
    (4, 14, 1, 100),
    (4, 15, 1, 30),
    (4, 16, 1, 100),
    (4, 17, 1, 30),
    (4, 8, 1, 60),
    (4, 10, 1, 200),
    (4, 11, 1, 10);

--Weganskie burrito

INSERT INTO recipe (course_id, food_category_id, recipe_name, recipe_description, prep_time, kcal) VALUES
    (1,1, 'Wegańskie burrito', 'Popularne meksykańskie burrito w odsłonie bezmięsnej', '30', 570);

INSERT INTO recipe_step (recipe_id, ingredient_id, ingredient_uom_id, ingredient_quantity) VALUES
    (5, 1, 'Ryż ugotować według zaleceń na opakowaniu'),
    (5, 2, 'Rozgrzać oliwę na patelni'),
    (5, 3, 'Drobno posiekać cebulę i dodać na patelnię'),
    (5, 4, 'Pokroić paprykę w małe paski i dodać na patelnię. Chwilę podsmażyć'),
    (5, 5, 'Wlać na patelnię pomidory z puszki'),
    (5, 6, 'Poddusić do zgęstnienia'),
    (5, 7, 'Dodać na patelnię fasolę oraz kukurydzę, przyprawić i wymieszać'),
    (5, 8, 'Ryż i zawartość patelni wyłożyć na talerz');

INSERT INTO quantity (recipe_id, ingredient_id, ingredient_uom_id, ingredient_quantity) VALUES
    (5, 21, 1, 100),
    (5, 18, 1, 60),
    (5, 19, 1, 200),
    (5, 20, 1, 60),
    (5, 9, 1 , 100),
    (5, 10, 1, 200),
    (5, 11, 1, 10);