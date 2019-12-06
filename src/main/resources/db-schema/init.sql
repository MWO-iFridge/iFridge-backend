    -- create db
    DROP DATABASE IF EXISTS ifridge;
    CREATE DATABASE ifridge DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

    USE ifridge;

    -- create tables
    
    CREATE TABLE user (
        id INT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(255),
        password VARCHAR(255),
        name VARCHAR(30),
        surname VARCHAR(40),
        weight FLOAT DEFAULT 0.0,
        height FLOAT DEFAULT 0.0,
        male BOOLEAN DEFAULT false,
        diet TINYINT DEFAULT 2 -- (0 - utrzymanie obecnej wagi, 1 - Zrzucenie sadełka, 2 - Budowa mięśni)
    );
    
    CREATE TABLE RECIPE (
        id INT AUTO_INCREMENT PRIMARY KEY,
        course_id INT,
        food_category_id INT,
        recipe_name VARCHAR(255),
        recipe_description TEXT NOT NULL,
        prep_time INT,
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
    INSERT INTO user (username, password, name, surname, weight, height, male, diet) VALUES
        ('root','$2a$10$JQ9NnLKDlaxq0f5M1alWVOjGf/SxIHXyKQ6x9DJ4MhaxRYDN2iKLi', 'Piotr', 'Test', '85.5', '186.5', true, '2'); -- rootpass
    INSERT INTO user (username,password) VALUES
        ('user','userpass');
        
    INSERT INTO COURSE(name) VALUES
        ('Obiad'),
        ('Śniadanie'),
        ('Kolacja');

    INSERT INTO FOOD_CATEGORY (name) VALUES
        ('Wegetariańskie'),
        ('Mięsne'),
        ('Jarskie');

    INSERT INTO INGREDIENT (name) VALUES
        ('Jajka'),
        ('Szynka'),
        ('Masło'),
        ('Kurczak'),
        ('Bazylia'),
        ('Bułka grahamka'),
        ('Mleko'),
        ('Cebula biała'),
        ('Papryka czerwona'),
        ('Pomidory z puszki'),  -- 10
        ('Oliwa z oliwek'),
        ('Hummus'),
        ('Ogórek'),
        ('Makaron'),
        ('Pesto bazyliowe'),
        ('Tofu naturalne'),
        ('Parmezan'),
        ('Cebula czerwona'),
        ('Fasola czerwona z puszki'),
        ('Kukurydza z puszki'), -- 20
        ('Ryż'),
        ('Ziemniaki'),
        ('Olej rzepakowy'),
        ('Pomidor'),
        ('Jogurt naturalny'),
        ('Brokuł'),
        ('Dynia'),
        ('Mięta'),
        ('Mąka');
        ('Pietruszka'); -- 30


    INSERT INTO UNIT_OF_MEASUREMENT (name) VALUES
        ('g'),
        ('ml'),
        ('kg'),
        ('szt');

    -- Jajecznica

    INSERT INTO RECIPE (course_id, food_category_id, recipe_name, recipe_description, prep_time, kcal) VALUES
        (2, 2,'Jajecznica na szynce', 'Tradycyjna jajecznica na szynce, dobra na pożywne i wartościowe śniadanie.', '15', 250 );

    INSERT INTO RECIPE_STEP (recipe_id, step_number, step_description) VALUES
        (1, 1, 'Rozbić jajka do miski i rozmieszać widelcem'),
        (1, 2, 'Dolać mleka do jajek i ponownie mieszać do uzyskania jednolitej konsystencji'),
        (1, 3, 'Rozgrzać masło na patelni'),
        (1, 4, 'Pokroić szynkę w kostkę i wrzucić na patelnię'),
        (1, 5, 'Zalać szynkę jajkami i mieszać do ścięcia jajecznicy według uznania');


    INSERT INTO QUANTITY (recipe_id, ingredient_id, ingredient_uom_id, ingredient_quantity) VALUES
        (1, 1, 4, 2),
        (1, 2, 1, 5),
        (1, 3, 1, 5),
        (1, 7, 2, 1);

    -- Szakszuka

    INSERT INTO RECIPE (course_id, food_category_id, recipe_name, recipe_description, prep_time, kcal) VALUES
        (2, 1,'Szakszuka', 'Pyszne danie z Bliskiego Wschodu idealne na śniadanie.', '20', 400  );

    INSERT INTO RECIPE_STEP (recipe_id, step_number, step_description) VALUES
        (2,1, 'Na patelni rozgrzać oliwę'),
        (2,2, 'Cebulę posiekać w kosteczkę i wrzucić na patelnię'),
        (2,3, 'Paprykę pokroić na paski i na pół, i wrzucić na patelnię. Chwilę dusić'),
        (2,4, 'Wlać pomidory z puszki i przyprawić, wymieszać'),
        (2,5, 'Z warzyw na patelni uformować pierścień, do dziury na środku wbić 3 jajka'),
        (2,6, 'Patelnię przykryć i dusić aż do ścięcia jajka');

    INSERT INTO QUANTITY (recipe_id, ingredient_id, ingredient_uom_id, ingredient_quantity) VALUES
        (2, 8, 1, 60),
        (2, 9, 1, 80),
        (2, 10, 1, 200),
        (2, 11, 1, 10),
        (2, 1, 4, 3);

    -- Bułki z hummusem

    INSERT INTO RECIPE (course_id, food_category_id, recipe_name, recipe_description, prep_time, kcal) VALUES
        (2,1, 'Bułki z hummusem', 'Lekkie i pożywne śniadanie dla fascynatów pieczywa z pastą', '5', 300);

    INSERT INTO RECIPE_STEP (recipe_id, step_number, step_description) VALUES 
        (3,1, 'Bułki przekroić wzdłuż na pół'),
        (3,2, 'W każdej powstałej parze jedną z przekrojonych części od wewnętrznej strony posmarować hummusem'),
        (3,3, 'Ogórek obrać i pokroić w talarki, a następnie ułożyć na hummusie'),
        (3,4, 'Bułkę złożyc w całość');

    INSERT INTO QUANTITY (recipe_id, ingredient_id, ingredient_uom_id, ingredient_quantity) VALUES
        (3, 6, 4 ,2),
        (3, 12, 1, 90),
        (3, 13, 1, 70);

    -- Spaghetti z tofu bazyliowym
    INSERT INTO RECIPE (course_id, food_category_id, recipe_name, recipe_description, prep_time, kcal) VALUES
        (1, 1, 'Spaghetti z tofu bazyliowym', 'Odświeżona osłona włoskiego specjału z dodatkiem tofu.', '30', 550);

    INSERT INTO RECIPE_STEP (recipe_id, step_number, step_description) VALUES
        (4,1, 'Ugotować makaron według zaleceń na opakowaniu'),
        (4,2, 'Pokroić tofu w drobną kostkę, wymieszać z pesto bazyliowym i odstawić na kilka minut'),
        (4,3, 'Rozgrzać oliwę na patelni'),
        (4,4, 'Drobno posiekać cebulę, dodać na patelnię i podsmażyć'),
        (4,5, 'Dodać tofu na patelnię i smażyć razem z cebulą'),
        (4,6, 'Wlać na patelnię pomidory z puszki, całość przyprawić i wymieszać'),
        (4,7, 'Poddusić aż do zgęstnienia pomidorowego sosu'),
        (4,8, 'Makaron i zawartosć patelni wyłożyć na talerz i dodać potarty parmezan');

    INSERT INTO QUANTITY (recipe_id, ingredient_id, ingredient_uom_id, ingredient_quantity) VALUES
        (4, 14, 1, 100),
        (4, 15, 1, 30),
        (4, 16, 1, 100),
        (4, 17, 1, 30),
        (4, 8, 1, 60),
        (4, 10, 1, 200),
        (4, 11, 1, 10);

    -- Weganskie burrito

    INSERT INTO RECIPE (course_id, food_category_id, recipe_name, recipe_description, prep_time, kcal) VALUES
        (1,1, 'Wegańskie burrito', 'Popularne meksykańskie burrito w odsłonie bezmięsnej', '30', 570);

    INSERT INTO RECIPE_STEP (recipe_id, step_number, step_description) VALUES
        (5, 1, 'Ryż ugotować według zaleceń na opakowaniu'),
        (5, 2, 'Rozgrzać oliwę na patelni'),
        (5, 3, 'Drobno posiekać cebulę i dodać na patelnię'),
        (5, 4, 'Pokroić paprykę w małe paski i dodać na patelnię. Chwilę podsmażyć'),
        (5, 5, 'Wlać na patelnię pomidory z puszki'),
        (5, 6, 'Poddusić do zgęstnienia'),
        (5, 7, 'Dodać na patelnię fasolę oraz kukurydzę, przyprawić i wymieszać'),
        (5, 8, 'Ryż i zawartość patelni wyłożyć na talerz');

    INSERT INTO QUANTITY (recipe_id, ingredient_id, ingredient_uom_id, ingredient_quantity) VALUES
        (5, 21, 1, 100),
        (5, 18, 1, 60),
        (5, 19, 1, 200),
        (5, 20, 1, 60),
        (5, 9, 1 , 100),
        (5, 10, 1, 200),
        (5, 11, 1, 10);

        --zapiekanka
        INSERT INTO RECIPE (course_id, food_category_id, recipe_name, recipe_description, prep_time, kcal) VALUES
        (3, 2,'Zapiekanka ziemniaczana z kurczakiem', 'Szybki obiad dla całej rodziny', '85', 540 );

        INSERT INTO RECIPE_STEP (recipe_id, step_number, step_description) VALUES
        (6, 1, 'Ziemniaki umyj, obierz i ugotuj w wodzie. Całość ostudź i pokrój w grubsze plastry.'),
        (6, 2, 'Mięso umyj, pokrój na grubsze plastry i podsmaż na złoty kolor na oleju rzepakowym.'),
        (6, 3, 'Brokuły umyj i podziel na mniejsze różyczki. Gotuj przez 5 min. na parze lub w wodzie. Pomidora sparz i pokrój w plasterki.'),
        (6, 4, 'Wymieszaj jajka z jogurtem'),
        (6, 5, 'W naczyniu żaroodpornym ułóż ziemniaki, następnie mięso i warzywa. Zalej sosem. Piecz w piekarniku rozgrzanym do temperatury 180 °C przez ok. 25-30 min.');

        INSERT INTO QUANTITY (recipe_id, ingredient_id, ingredient_uom_id, ingredient_quantity) VALUES
        (6, 22, 1, 600),
        (6, 4, 1, 300),
        (6, 23, 2, 20),
        (6, 24, 4, 1),
        (6, 26, 4, 1),
        (6, 1, 2, 1),
        (6, 25, 1, 250);

        -- pklacki
        INSERT INTO RECIPE (course_id, food_category_id, recipe_name, recipe_description, prep_time, kcal) VALUES
        (3, 2,'Placuszki z dyni z sosem miętowo-jogurtowym ', 'Jesienne danie obiadowe', '45', 400 );

        INSERT INTO RECIPE_STEP (recipe_id, step_number, step_description) VALUES
        (7, 1, 'W miseczce rozmieszaj: jajka, startą dynię, cebulę, pietruszkę, mąkę, sól i pieprz.'),
        (7, 2, 'Na patelni rozgrzej Ramę Smaż jak szef kuchni. Poczekaj aż z tłuszczu znikną bąbelki –będzie to znak, że uzyskał odpowiednią temperaturę do smażenia. Smaż placki o średnicy 4 cm przez 3 minuty z każdej strony.'),
        (7, 3, 'W międzyczasie wymieszaj jogurt z miętą, solą i pieprzem. W międzyczasie wymieszaj jogurt z miętą, solą i pieprzem.');

        INSERT INTO QUANTITY (recipe_id, ingredient_id, ingredient_uom_id, ingredient_quantity) VALUES
        (7, 1, 1, 3),
        (7, 27, 1, 800),
        (7, 25, 1, 250),
        (7, 28, 1, 100),
        (7, 8, 4, 1),
        (7, 29, 1, 150),
        (7, 30, 1, 100);