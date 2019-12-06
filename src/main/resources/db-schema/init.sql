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
('Kolacja'),
('Deser')
;

INSERT INTO FOOD_CATEGORY (name) VALUES
('Wegetariańskie'),
('Mięsne'),
('Jarskie'),
('Słodkie');

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
('Mąka'),
('Pietruszka'),-- 30
('Cukier'),
('Miód'),
('Kostka do pieczenia'),
('Proszek do pieczenia'),
('Orzechy włoskie'),
('Suszone mięso wołowe'), -- 36
('Majonez'),
('Margaryna')
;


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

-- zapiekanka
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

-- ciasto
INSERT INTO RECIPE (course_id, food_category_id, recipe_name, recipe_description, prep_time, kcal) VALUES
(3, 2,'Miodowiec z orzechami', 'Ciasto na święta', '45', 700 );

INSERT INTO RECIPE_STEP (recipe_id, step_number, step_description) VALUES
(8, 1, 'Rozgrzej piekarnik do 170°C. Przygotuj dwie miski. W pierwszej wymieszaj mąkę z proszkiem do pieczenia i szczyptą soli. W drugiej ubij Kasię z cukrem na puszystą masę, a następnie, nie przerywając ubijania, dodaj miód, pojedynczo jajka, i na koniec mieszaninę mąki. Miksuj, aż masa będzie gładka.'),
(8, 2, 'Ciasto przełóż do kwadratowej formy o boku ok. 30 cm wyłożonej papierem do pieczenia i wstaw do piekarnika rozgrzanego do 170°C . Piecz ok. 40 minut, po tym czasie wbij w ciasto drewniany patyczek, np. do szaszłyków. Jeśli patyczek będzie mokry, piecz kolejne 5 minut i ponownie sprawdź ciasto patyczkiem. Jeśli po wyjęciu patyczek jest suchy, ciasto jest gotowe. Wyjmij je z pieca i ostudź.'),
(8, 3, 'Podgrzej w rondelku połowę mleka z cukrem i esencją waniliową, w pozostałej połowie mleka rozprowadź mąki. Gdy mleko zacznie wrzeć, wlej roztwór z mąką i zagotuj budyń. Odstaw do wystudzenia.'),
(8, 4, 'Ubij mikserem Kasię, a następnie, nie przerywając ubijania, łyżkami zacznij dodawać budyń.'),
(8, 5, 'Orzechy lekko posiekaj, oddziel od pyłu, przełóż do rondelka, dodaj pozostałe jej składniki i zagotuj. Polewa jest gotowa, gdy zgęstnieje.'),
(8, 6, 'Ciasto pokrój ostrym nożem na 2 lub 3 cienkie blaty (ich liczba zależy od wyrośnięcia ciasta i Twoich zdolności do jego przecięcia). Przełóż warstwy biszkoptowe kremem, lekko dociśnij i usuń nadmiar kremu wystający po bokach ciasta.'),
(8, 7, 'Następnie na wierzchu ciasta rozsmaruj polewę orzechową i udekoruj orzechami. Przechowuj w lodówce 2-3 dni.');

INSERT INTO QUANTITY (recipe_id, ingredient_id, ingredient_uom_id, ingredient_quantity) VALUES
(8, 1, 4, 4),
(8, 31, 1, 150),
(8, 32, 2, 250),
(8, 29, 1, 300),
(8, 33, 1, 250),
(8, 34, 1, 45),
(8, 7, 2, 500),
(8, 36, 1, 300);

-- chleb
INSERT INTO RECIPE (course_id, food_category_id, recipe_name, recipe_description, prep_time, kcal) VALUES
(2, 1,'Afrykański chleb z suszonym mięsem', 'Oryginalny pyszny chleb', '90', 500 );

INSERT INTO RECIPE_STEP (recipe_id, step_number, step_description) VALUES
(9, 1, 'W misce wymieszaj mąkę z proszkiem do pieczenia.'),
(9, 2, 'Dodaj majonez, posiekaną natkę pietruszki, posiekaną paprykę, mleko, jajko i bardzo drobno pokrojone suszone mięso.'),
(9, 3, 'Wymieszaj krótko składniki, aż się połączą.'),
(9, 4, 'Ciasto przełóż do natłuszczonej formy keksowej i wygładź delikatnie po wierzchu'),
(9, 5, 'Ciasto piecz w temperaturze 180 °C przez 45 minut. Po tym czasie sprawdź drewnianą wykałaczką, czy ciasto jest wystarczająco wypieczone i suche w środku.'),
(9, 6, 'Po upieczeniu odstaw, aby lekko ostygło, następnie delikatnie wyjmij z formy. Podawaj wystudzone ze świeżym masłem jako codzienne pieczywo.');

INSERT INTO QUANTITY (recipe_id, ingredient_id, ingredient_uom_id, ingredient_quantity) VALUES
(9, 29, 1, 350),
(9, 36, 1, 100),
(9, 34, 1, 15),
(9, 30, 1, 20),
(9, 38, 1, 200),
(9, 7, 2, 250),
(9, 1, 4, 1),
(9, 38, 1, 35);


INSERT INTO RECIPE (course_id, food_category_id, recipe_name, recipe_description, prep_time, kcal) VALUES
(2, 1,'Omlet z brokułem', 'Pożywne danie na początek dnia', '15', 600 );

INSERT INTO RECIPE_STEP (recipe_id, step_number, step_description) VALUES
(10, 1, 'Brokół pokrój w drobną kostkę.'),
(10, 2, 'Mleko rozmieszaj z jajkami.'),
(10, 3, 'Na oleju zeszklij posiekaną cebulę, dodaj szpinak.'),
(10, 4, 'Dodaj masę jajeczną, smaż pod przykryciem około 5 minut.');

INSERT INTO QUANTITY (recipe_id, ingredient_id, ingredient_uom_id, ingredient_quantity) VALUES
(10, 26, 1, 150),
(10, 1, 4, 4),
(10, 7, 2, 50),
(10, 23, 2, 20),
(10, 18, 4, 1);

-- Naleśniki
INSERT INTO RECIPE (course_id, food_category_id, recipe_name, recipe_description, prep_time, kcal) VALUES
(2, 1,'Naleśniki', 'Uniwersalne danie nabierające różnych obliczy w zależności od zastosowanych dodatków', '25', 800 );

INSERT INTO RECIPE_STEP (recipe_id, step_number, step_description) VALUES
(11, 1, 'Rozmieszaj mleko z jajkami.'),
(11, 2, 'Otrzymaną masę zmieszaj z mąką i opcjonalnie cukrem.'),
(11, 3, 'Ciasto rozlej równomiernie na patelni.'),
(11, 4, 'Smaż około 2 minuty, po czym obróć ciasto na drugę stronę.'),
(11, 5, 'Po około 1 minucie można zdjąć naleśnik z patelni.');

INSERT INTO QUANTITY (recipe_id, ingredient_id, ingredient_uom_id, ingredient_quantity) VALUES
(11, 1, 4, 4),
(11, 7, 2, 200),
(11, 29, 1, 200);

-- Zupa brokułowa
INSERT INTO RECIPE (course_id, food_category_id, recipe_name, recipe_description, prep_time, kcal) VALUES
(1, 1,'Zupa brokułowa', 'Bardzo prosta zupa może zostać przyrządzona z wykorzystaniem dowolnych warzyw - cukinii, kalafiora czy selera', '30', 400 );

INSERT INTO RECIPE_STEP (recipe_id, step_number, step_description) VALUES
(12, 1, 'Podziel brokół na mniejsze różyczki. Warzywa pokrój w kostkę.'),
(12, 2, 'W dużym garnku rozgrzej łyżkę masła. Dodaj cebulę. Smaż przez około 5 minut wszystko mieszając.'),
(12, 3, 'Dodaj litr wody i kostkę bulionu. Doprowadź do wrzenia, zmniejsz ogień, a następnie gotuj na małym ogniu przez około 15 minut lub do momentu zmięknięcia warzyw. Zupę zmiksuj za pomocą blendera.');

INSERT INTO QUANTITY (recipe_id, ingredient_id, ingredient_uom_id, ingredient_quantity) VALUES
(12, 26, 1, 1),
(12, 8, 2, 200);