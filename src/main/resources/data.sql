INSERT INTO MOVIE VALUES (1, 'Starzejący się patriarcha dynastii przestępczości zorganizowanej przekazuje kontrolę nad swoim tajnym imperium niechętnemu synowi.', 175, 'Ojciec chrzestny', 1972);

INSERT INTO MEDIA VALUES (1, 'Paramount Pictures', now(), 'COVER', 'godfather/the_godfather_cover.jpg');
INSERT INTO MEDIA VALUES (2, 'Paramount Pictures', now(), 'PICTURE', 'godfather/godfather1.jpg');
INSERT INTO MEDIA VALUES (3, 'Paramount Pictures', now(), 'PICTURE', 'godfather/godfather2.jpg');
INSERT INTO MEDIA VALUES (4, 'Paramount Pictures', now(), 'PICTURE', 'godfather/godfather3.jpg');
INSERT INTO MEDIA VALUES (5, 'Paramount Pictures', now(), 'PICTURE', 'godfather/godfather4.jpg');
INSERT INTO MEDIA VALUES (6, 'Paramount Pictures', now(), 'PICTURE', 'godfather/godfather5.jpg');
INSERT INTO MEDIA VALUES (7, 'YouTube', now(), 'TRAILER', 'https://www.youtube.com/embed/sY1S34973zA');

INSERT INTO MOVIE_MEDIA VALUES (1, 1);
INSERT INTO MOVIE_MEDIA VALUES (2, 1);
INSERT INTO MOVIE_MEDIA VALUES (3, 1);
INSERT INTO MOVIE_MEDIA VALUES (4, 1);
INSERT INTO MOVIE_MEDIA VALUES (5, 1);
INSERT INTO MOVIE_MEDIA VALUES (6, 1);
INSERT INTO MOVIE_MEDIA VALUES (7, 1);

INSERT INTO CATEGORY VALUES (1, 'dramat');
INSERT INTO CATEGORY VALUES (2, 'gangsterski');

INSERT INTO MOVIE_CATEGORY VALUES (1, 1);
INSERT INTO MOVIE_CATEGORY VALUES (2, 1);

INSERT INTO COUNTRY VALUES (1, 'USA');

INSERT INTO MOVIE_COUNTRY VALUES (1, 1);

INSERT INTO PERSON VALUES (1, 'Francis Ford', 'Coppola');
INSERT INTO PERSON VALUES (2, 'Mario', 'Puzo');
INSERT INTO PERSON VALUES (3, 'Marlon', 'Brando');
INSERT INTO PERSON VALUES (4, 'Al', 'Pacino');
INSERT INTO PERSON VALUES (5, 'James', 'Caan');
INSERT INTO PERSON VALUES (6, 'Richard S.', 'Castellano');
INSERT INTO PERSON VALUES (7, 'Robert', 'Duvall');

INSERT INTO MOVIE_PERSON VALUES (1, NULL, 'DIRECTOR', 1, 1);
INSERT INTO MOVIE_PERSON VALUES (2, NULL, 'SCENARIO', 1, 1);
INSERT INTO MOVIE_PERSON VALUES (3, NULL, 'SCENARIO', 1, 2);
INSERT INTO MOVIE_PERSON VALUES (4, 'Don Vito Corleone', 'ACTOR', 1, 3);
INSERT INTO MOVIE_PERSON VALUES (5, 'Michael Corleone', 'ACTOR', 1, 4);
INSERT INTO MOVIE_PERSON VALUES (6, 'Sonny Corleone', 'ACTOR', 1, 5);
INSERT INTO MOVIE_PERSON VALUES (7, 'Peter Clemenza', 'ACTOR', 1, 6);
INSERT INTO MOVIE_PERSON VALUES (8, 'Tom Hagen', 'ACTOR', 1, 7);

INSERT INTO USER VALUES (1, 'rooq37');

INSERT INTO RATING VALUES ('2019-09-17 18:59:15', 10, 1, 1);

INSERT INTO COMMENT VALUES (1, 'Great movie!', '2019-09-17 20:59:10', 4, 1, 1);
INSERT INTO COMMENT VALUES (2, 'Great movie!', '2019-09-17 20:59:10', 4, 1, 1);
INSERT INTO COMMENT VALUES (3, 'Great movie!', '2019-09-17 20:59:10', 4, 1, 1);
INSERT INTO COMMENT VALUES (4, 'Great movie!', '2019-09-17 20:59:10', 4, 1, 1);
INSERT INTO COMMENT VALUES (5, 'Great movie!', '2019-09-17 20:59:10', 4, 1, 1);
INSERT INTO COMMENT VALUES (6, 'Great movie!', '2019-09-17 20:59:10', 4, 1, 1);
INSERT INTO COMMENT VALUES (7, 'Great movie!', '2019-09-17 20:59:10', 4, 1, 1);
INSERT INTO COMMENT VALUES (8, 'Great movie!', '2019-09-17 20:59:10', 4, 1, 1);
INSERT INTO COMMENT VALUES (9, 'Great movie!', '2019-09-17 20:59:10', 4, 1, 1);
INSERT INTO COMMENT VALUES (10, 'Great movie!', '2019-09-17 20:59:10', 4, 1, 1);
INSERT INTO COMMENT VALUES (11, 'Great movie!', '2019-09-17 20:59:10', 4, 1, 1);

------------------------------------------------------------------------------------------------------------------------

INSERT INTO MOVIE VALUES (2, 'Podróż hobbita z Shire i jego ośmiu towarzyszy, której celem jest zniszczenie potężnego pierścienia pożądanego przez Czarnego Władcę - Saurona.', 178, 'Władca Pierścieni: Drużyna Pierścienia', 2001);

INSERT INTO MEDIA VALUES (8, 'New Line Cinema', now(), 'COVER', 'tlotr_1/tlotr_1_cover.jpg');
INSERT INTO MEDIA VALUES (9, 'New Line Cinema', now(), 'PICTURE', 'tlotr_1/tlotr_1_1.jpg');
INSERT INTO MEDIA VALUES (10, 'New Line Cinema', now(), 'PICTURE', 'tlotr_1/tlotr_1_2.jpg');
INSERT INTO MEDIA VALUES (11, 'New Line Cinema', now(), 'PICTURE', 'tlotr_1/tlotr_1_3.jpg');
INSERT INTO MEDIA VALUES (12, 'New Line Cinema', now(), 'PICTURE', 'tlotr_1/tlotr_1_4.jpg');
INSERT INTO MEDIA VALUES (13, 'YouTube', now(), 'TRAILER', 'https://www.youtube.com/embed/K_QJkYUrKn0');

INSERT INTO MOVIE_MEDIA VALUES (8, 2);
INSERT INTO MOVIE_MEDIA VALUES (9, 2);
INSERT INTO MOVIE_MEDIA VALUES (10, 2);
INSERT INTO MOVIE_MEDIA VALUES (11, 2);
INSERT INTO MOVIE_MEDIA VALUES (12, 2);
INSERT INTO MOVIE_MEDIA VALUES (13, 2);

INSERT INTO CATEGORY VALUES (3, 'fantasy');
INSERT INTO CATEGORY VALUES (4, 'przygodowy');

INSERT INTO MOVIE_CATEGORY VALUES (3, 2);
INSERT INTO MOVIE_CATEGORY VALUES (4, 2);

INSERT INTO MOVIE_COUNTRY VALUES (2, 1);

INSERT INTO PERSON VALUES (8, 'Peter', 'Jackson');
INSERT INTO PERSON VALUES (9, 'Fran', 'Walsh');
INSERT INTO PERSON VALUES (10, 'Elijah', 'Wood');
INSERT INTO PERSON VALUES (11, 'Sean', 'Astin');
INSERT INTO PERSON VALUES (12, 'Billy', 'Boyd');
INSERT INTO PERSON VALUES (13, 'Dominic', 'Monaghan');
INSERT INTO PERSON VALUES (14, 'Ian', 'Holm');

INSERT INTO MOVIE_PERSON VALUES (9, NULL, 'DIRECTOR', 2, 8);
INSERT INTO MOVIE_PERSON VALUES (10, NULL, 'SCENARIO', 2, 8);
INSERT INTO MOVIE_PERSON VALUES (11, NULL, 'SCENARIO', 2, 9);
INSERT INTO MOVIE_PERSON VALUES (12, 'Frodo Baggins', 'ACTOR', 2, 10);
INSERT INTO MOVIE_PERSON VALUES (13, 'Samwise "Sam" Gamgee', 'ACTOR', 2, 11);
INSERT INTO MOVIE_PERSON VALUES (14, 'Peregrin "Pippin" Took', 'ACTOR', 2, 12);
INSERT INTO MOVIE_PERSON VALUES (15, 'Meriadok "Merry" Brandybuck', 'ACTOR', 2, 13);
INSERT INTO MOVIE_PERSON VALUES (16, 'Bilbo Baggins', 'ACTOR', 2, 14);

INSERT INTO RATING VALUES ('2019-09-19 17:59:15', 9, 1, 2);

INSERT INTO COMMENT VALUES (12, 'Great movie!', '2019-09-17 20:59:10', 4, 2, 1);
INSERT INTO COMMENT VALUES (13, 'Great movie!', '2019-09-17 20:59:10', 4, 2, 1);
INSERT INTO COMMENT VALUES (14, 'Great movie!', '2019-09-17 20:59:10', 4, 2, 1);
INSERT INTO COMMENT VALUES (15, 'Great movie!', '2019-09-17 20:59:10', 4, 2, 1);
INSERT INTO COMMENT VALUES (16, 'Great movie!', '2019-09-17 20:59:10', 4, 2, 1);
INSERT INTO COMMENT VALUES (17, 'Great movie!', '2019-09-17 20:59:10', 4, 2, 1);
INSERT INTO COMMENT VALUES (18, 'Great movie!', '2019-09-17 20:59:10', 4, 2, 1);
INSERT INTO COMMENT VALUES (19, 'Great movie!', '2019-09-17 20:59:10', 4, 2, 1);
INSERT INTO COMMENT VALUES (20, 'Great movie!', '2019-09-17 20:59:10', 4, 2, 1);
INSERT INTO COMMENT VALUES (21, 'Great movie!', '2019-09-17 20:59:10', 4, 2, 1);
INSERT INTO COMMENT VALUES (22, 'Great movie!', '2019-09-17 20:59:10', 4, 2, 1);

------------------------------------------------------------------------------------------------------------------------

INSERT INTO MOVIE VALUES (3, 'Poruszająca historia jednego z największych bokserów wszech czasów - Muhammada Ali''ego.', 157, 'Ali', 2001);

INSERT INTO MEDIA VALUES (14, 'Columbia Pictures Corporation', now(), 'COVER', 'ali/ali_cover.jpg');
INSERT INTO MEDIA VALUES (15, 'Columbia Pictures Corporation', now(), 'PICTURE', 'ali/ali_1.jpg');
INSERT INTO MEDIA VALUES (16, 'Columbia Pictures Corporation', now(), 'PICTURE', 'ali/ali_2.jpg');
INSERT INTO MEDIA VALUES (17, 'YouTube', now(), 'TRAILER', 'https://www.youtube.com/embed/STuHQ5HpmEE');

INSERT INTO MOVIE_MEDIA VALUES (14, 3);
INSERT INTO MOVIE_MEDIA VALUES (15, 3);
INSERT INTO MOVIE_MEDIA VALUES (16, 3);
INSERT INTO MOVIE_MEDIA VALUES (17, 3);

INSERT INTO CATEGORY VALUES (5, 'biograficzny');
INSERT INTO CATEGORY VALUES (6, 'sportowy');

INSERT INTO MOVIE_CATEGORY VALUES (5, 3);
INSERT INTO MOVIE_CATEGORY VALUES (6, 3);

INSERT INTO MOVIE_COUNTRY VALUES (3, 1);

INSERT INTO PERSON VALUES (15, '	Michael', 'Mann');
INSERT INTO PERSON VALUES (16, 'Stephen J.', 'Rivele');
INSERT INTO PERSON VALUES (17, 'Will', 'Smith');
INSERT INTO PERSON VALUES (18, 'Jamie', 'Foxx');
INSERT INTO PERSON VALUES (19, 'Jon', 'Voight');
INSERT INTO PERSON VALUES (20, 'Mario', 'Van Peebles');
INSERT INTO PERSON VALUES (21, 'Ron', 'Silver');

INSERT INTO MOVIE_PERSON VALUES (17, NULL, 'DIRECTOR', 3, 15);
INSERT INTO MOVIE_PERSON VALUES (18, NULL, 'SCENARIO', 3, 15);
INSERT INTO MOVIE_PERSON VALUES (19, NULL, 'SCENARIO', 3, 16);
INSERT INTO MOVIE_PERSON VALUES (20, 'Muhammad Ali', 'ACTOR', 3, 17);
INSERT INTO MOVIE_PERSON VALUES (21, 'Drew "Bundini" Brown', 'ACTOR', 3, 18);
INSERT INTO MOVIE_PERSON VALUES (22, 'Howard Cosell', 'ACTOR', 3, 19);
INSERT INTO MOVIE_PERSON VALUES (23, 'Malcolm X', 'ACTOR', 3, 20);
INSERT INTO MOVIE_PERSON VALUES (24, 'Angelo Dundee', 'ACTOR', 3, 21);

INSERT INTO RATING VALUES ('2019-09-30 16:01:15', 8, 1, 3);

