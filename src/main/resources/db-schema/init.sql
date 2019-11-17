-- create db
DROP DATABASE IF EXISTS ifridge;
CREATE DATABASE ifridge;

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
    ('Mleko');

INSERT INTO recipe (course_id, food_category_id, recipe_name, recipe_description, prep_time, kcal) VALUES
    (2, 2,'Jajecznica na szynce', 'Tradycyjna jajecznica na szynce, dobra na pożywne i wartościowe śniadanie.', '15', 250 );

INSERT INTO recipe_step (recipe_id, step_number, step_description) VALUES
    (1, 1, 'Rozbić jajka do miski i rozmieszać widelcem'),
    (1, 2, 'Dolać mleka do jajek i ponownie mieszać do uzyskania jednolitej konsystencji'),
    (1, 3, 'Rozgrzać masło na patelni'),
    (1, 4, 'Pokroić szynkę w kostkę i wrzucić na patelnię'),
    (1, 5, 'Zalać szynkę jajkami i mieszać do ścięcia jajecznicy według uznania');

INSERT INTO unit_of_measurement (name) VALUES
    ('g'),
    ('ml'),
    ('kg'),
    ('szt');

INSERT INTO quantity (recipe_id, ingredient_id, ingredient_uom_id, ingredient_quantity) VALUES
    (1, 1, 4, 2),
    (1, 2, 1, 5),
    (1, 3, 1, 5),
    (1, 7, 2, 1);
