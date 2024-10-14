DROP TABLE IF EXISTS summoners_champions;
DROP TABLE IF EXISTS summoners;
DROP TABLE IF EXISTS champions;

CREATE TABLE champions(
	champion_id serial PRIMARY KEY,
	champion_name TEXT UNIQUE,
	attack int CHECK(attack > 0 AND attack < 10),
	defense int CHECK(defense > 0 AND defense < 10),
	magic int CHECK(magic > 0 AND magic < 10),
	difficulty int CHECK(difficulty > 0 AND difficulty <= 10)
);

CREATE TABLE summoners(
	summoner_id serial PRIMARY KEY,
	summoner_name TEXT UNIQUE,
	summoner_level int CHECK(summoner_level > 0)
);

CREATE TABLE summoners_champions(
	summoner_champions_id serial PRIMARY KEY,
	summoner_id_fk int REFERENCES summoners(summoner_id) ON DELETE CASCADE,
    champion_id_fk int REFERENCES champions(champion_id) ON DELETE CASCADE
);

INSERT INTO champions(champion_name, attack,defense,magic,difficulty) VALUES('Aatrox',8,4,3,4),('Rengar',7,4,2,8),('Viego',6,4,2,5),('Hecarim',8,6,4,6),('Belveth',4,2,7,10);

INSERT INTO summoners(summoner_name, summoner_level) VALUES('Hari', 5),('Krish',1),('Kiddo A', 100);

INSERT INTO summoners_champions(summoner_id_fk, champion_id_fk) VALUES(1,1),(1,2),(1,3),(1,4),(2,1),(2,3),(3,3);

SELECT * FROM champions;

SELECT * FROM summoners;

SELECT * FROM summoners_champions;