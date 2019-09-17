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
--INSERT INTO COMMENT VALUES (2, 'moviemaniac', 'comment1', '2019-09-14 12:15:10', 3);
--INSERT INTO COMMENT VALUES (3, 'destroyer69', 'comment1', '2019-09-14 12:15:10', -5);
--INSERT INTO COMMENT VALUES (4, 'ilovebradpitt', 'comment1', '2019-09-14 12:15:10', 45);
--INSERT INTO COMMENT VALUES (5, 'monica1', 'comment1', '2019-09-14 12:15:10', 13);
--INSERT INTO COMMENT VALUES (6, 'phil100', 'comment1', '2019-09-14 12:15:10', -8);
--INSERT INTO COMMENT VALUES (7, 'wolfie', 'comment1', '2019-09-14 12:15:10', 0);
--INSERT INTO COMMENT VALUES (8, 'doggy', 'comment1', '2019-09-14 12:15:10', 1);
--INSERT INTO COMMENT VALUES (9, 'horsie', 'comment1', '2019-09-14 12:15:10', 7);
--INSERT INTO COMMENT VALUES (10, 'dag', 'comment1', '2019-09-14 12:15:10', 5);
--INSERT INTO COMMENT VALUES (11, '503126', 'comment1', '2019-09-14 12:15:10', 9);
--INSERT INTO COMMENT VALUES (12, 'filmaker', 'comment1', '2019-09-14 12:15:10', 91);
--INSERT INTO COMMENT VALUES (13, 'footballerpro15', 'comment1', '2019-09-14 12:15:10', 45);
--INSERT INTO COMMENT VALUES (14, 'anonymouse', 'comment1', '2019-09-14 12:15:10', 76);
--INSERT INTO COMMENT VALUES (15, 'mrnobody', 'comment1', '2019-09-14 12:15:10', 81);
--INSERT INTO COMMENT VALUES (16, 'paul123', 'comment1', '2019-09-14 12:15:10', 89);
--INSERT INTO COMMENT VALUES (17, 'vin99', 'comment1', '2019-09-14 12:15:10', -56);
--INSERT INTO COMMENT VALUES (18, 'maestro', 'comment1', '2019-09-14 12:15:10', -1);
--INSERT INTO COMMENT VALUES (19, 'viki56', 'comment1', '2019-09-14 12:15:10', 1);
--INSERT INTO COMMENT VALUES (20, 'kalioente', 'comment1', '2019-09-14 12:15:10', 7);
--INSERT INTO COMMENT VALUES (21, 'mafiaboy', 'comment1', '2019-09-14 12:15:10', 34);
--INSERT INTO COMMENT VALUES (22, 'bug31', 'comment1', '2019-09-14 12:15:10', 12);
--INSERT INTO COMMENT VALUES (23, 'pl-contact', 'comment1', '2019-09-14 12:15:10', 76);
--INSERT INTO COMMENT VALUES (24, 'wolfgang39', 'comment1', '2019-09-14 12:15:10', 87);
--INSERT INTO COMMENT VALUES (25, 'qwerty123', 'comment1', '2019-09-14 12:15:10', 6);
--INSERT INTO COMMENT VALUES (26, 'lopez', 'comment1', '2019-09-14 12:15:10', 5);